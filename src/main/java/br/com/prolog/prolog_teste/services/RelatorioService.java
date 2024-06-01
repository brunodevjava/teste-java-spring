package br.com.prolog.prolog_teste.services;

import br.com.prolog.prolog_teste.domain.Colaborador;
import br.com.prolog.prolog_teste.domain.Marcacao;
import br.com.prolog.prolog_teste.domain.MarcacaoTipo;
import br.com.prolog.prolog_teste.dto.relatorio.*;
import br.com.prolog.prolog_teste.repository.MarcacaoRepository;
import br.com.prolog.prolog_teste.repository.MarcacaoTipoRepository;
import br.com.prolog.prolog_teste.repository.MarcacaoVinculoInicioFimRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final MarcacaoRepository marcacaoRepository;

    private final MarcacaoTipoRepository marcacaoTipoRepository;


    private final MarcacaoVinculoInicioFimRepository vinculoRepository;

    private final ColaboradorService colaboradorService;


    public RelatorioService(MarcacaoRepository marcacaoRepository, MarcacaoTipoRepository marcacaoTipoRepository, MarcacaoVinculoInicioFimRepository vinculoRepository, ColaboradorService colaboradorService) {
        this.marcacaoRepository = marcacaoRepository;
        this.marcacaoTipoRepository = marcacaoTipoRepository;
        this.vinculoRepository = vinculoRepository;
        this.colaboradorService = colaboradorService;
    }

    // Método para gerar o relatório
    public RelatorioDTO gerarRelatorio(String cpf, String inicio, String fim) {
        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String inicioFormatado = formatarData(inicio);
        String fimFormatado = formatarData(fim);


        ZonedDateTime fimPeriodoFilter = ZonedDateTime.parse(fim + "T23:59:59Z").withZoneSameInstant(saoPauloZone).plusDays(1);

        // Consultar marcações no período e converter para o timezone correto
        List<Marcacao> marcacoes = marcacaoRepository.findByColaboradorCpfAndDataHoraMarcacaoBeforeOrderByDataHoraMarcacao(cpf, fimPeriodoFilter.toOffsetDateTime()).stream()
                .filter(marcacao -> Marcacao.TipoMarcacao.MARCACAO_INICIO.equals(marcacao.getTipoMarcacaoEnum()))
                .toList();
        Colaborador colaborador = colaboradorService.getColaborador(cpf);


        // Mapa para agrupar eventos por dia de início
        Map<LocalDate, List<EventoDTO>> eventosPorDia = new HashMap<>();
        Map<String, Long> totalSegundosPorTipo = new HashMap<>();
        Map<String, Long> totalSegundosNoturnosPorTipo = new HashMap<>();

        for (Marcacao marcacao : marcacoes) {
            String tipoMarcacao = encontrarTipoMarcacao(marcacao);
            ZonedDateTime marcacaoInicio = marcacao.getDataHoraMarcacao().atZoneSameInstant(saoPauloZone);
            OffsetDateTime marFim = encontrarMarcacaoFimVinculada(marcacao);

            if (marcacaoInicio != null && marFim == null) {
                EventoDTO evento = new EventoDTO(
                        tipoMarcacao,
                        Optional.of(new MarcacoesDeTempoInicioDTO(marcacaoInicio.format(dateFormatter), marcacaoInicio.format(timeFormatter))),
                        null
                );
                eventosPorDia.computeIfAbsent(marcacaoInicio.toLocalDate(), k -> new ArrayList<>()).add(evento);
            } else {
                if (marcacaoInicio == null || marFim == null) {
                    continue;
                } else {
                    EventoDTO evento = new EventoDTO(
                            tipoMarcacao,
                            Optional.of(new MarcacoesDeTempoInicioDTO(marcacaoInicio.format(dateFormatter), marcacaoInicio.format(timeFormatter))),
                            Optional.of(new MarcacoesDeTempoFimDTO(marFim.atZoneSameInstant(saoPauloZone).format(dateFormatter), marFim.atZoneSameInstant(saoPauloZone).format(timeFormatter)))
                    );

                    eventosPorDia.computeIfAbsent(marcacaoInicio.toLocalDate(), k -> new ArrayList<>()).add(evento);

                    if (marcacaoInicio != null && marFim != null) {
                        long segundos = Duration.between(marcacaoInicio, marFim.atZoneSameInstant(saoPauloZone)).getSeconds();
                        totalSegundosPorTipo.merge(tipoMarcacao, segundos, Long::sum);

                        long segundosNoturnos = calcularSegundosNoturnos(marcacaoInicio, marFim.atZoneSameInstant(saoPauloZone));
                        totalSegundosNoturnosPorTipo.merge(tipoMarcacao, segundosNoturnos, Long::sum);
                    }

                }
            }

        }

        // Transforma o mapa em uma lista de DiaDTO
        List<DiaDTO> days = eventosPorDia.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Ordena os dias do mais antigo ao mais recente
                .map(entry -> new DiaDTO(entry.getKey().format(dateFormatter), entry.getValue()))
                .collect(Collectors.toList());

        List<CalculosDTO> calculos = totalSegundosPorTipo.entrySet().stream()
                .map(entry -> new CalculosDTO(
                        entry.getKey(),
                        tipoMarcacaoNome(entry.getKey()),
                        calcularTempoRecomendado(entry.getKey()),
                        formatarSegundos(entry.getValue()),
                        formatarSegundos(totalSegundosNoturnosPorTipo.getOrDefault(entry.getKey(), 0L))
                ))
                .collect(Collectors.toList());

        RelatorioDTO relatorio = new RelatorioDTO(
                "Teste Transportes",
                inicioFormatado + " - " + fimFormatado,
                ZonedDateTime.now(saoPauloZone).format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")),
                colaborador.getNome(),
                days,
                calculos
        );
        return relatorio;
    }

    public static String formatarData(String data) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataObjeto = formatoEntrada.parse(data);
            return formatoSaida.format(dataObjeto);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para encontrar o nome do tipo de marcação
    private String encontrarTipoMarcacao(Marcacao marcacao) {
        MarcacaoTipo tipoMarcacao = marcacaoTipoRepository.findById(marcacao.getTipoMarcacao().getCodigo())
                .orElseThrow(() -> new RuntimeException("Tipo de marcação não encontrado"));
        return switch (tipoMarcacao.getNome()) {
            case "Descanso" -> "4";
            case "Refeição" -> "1";
            case "Jornada" -> "2";
            case "Espera" -> "3";
            default -> throw new IllegalArgumentException("Tipo de marcação desconhecido: " + tipoMarcacao.getNome());
        };
    }

    // Método que chama a consulta para encontrar a marcação de fim
    private OffsetDateTime encontrarMarcacaoFimVinculada(Marcacao marcacaoInicio) {
        return vinculoRepository.findByMarcacaoInicioIn(List.of(marcacaoInicio)).stream()
                .findFirst()
                .map(v -> v.getMarcacaoFim().getDataHoraMarcacao())
                .orElse(null);
    }

    private String tipoMarcacaoNome(String tipoMarcacao) {
        return switch (tipoMarcacao) {
            case "1" -> "Refeição"; // 30 minutos
            case "2" -> "Jornada"; // 12 horas
            case "3" -> "Espera"; // 1 hora
            case "4" -> "Descanso"; // 1 hora
            default -> "Inválido"; // Default para tipos desconhecidos
        };
    }


    // Método para calcular o tempo recomendado
    private String calcularTempoRecomendado(String tipoMarcacao) {
        return switch (tipoMarcacao) {
            case "1" -> "00:30:00"; // 30 minutos
            case "2" -> "12:00:00"; // 12 horas
            case "3" -> "01:00:00"; // 1 hora
            case "4" -> "01:00:00"; // 1 hora
            default -> "00:00:00"; // Default para tipos desconhecidos
        };
    }

    // Método para formatar segundos em HH:mm:ss
    private String formatarSegundos(long segundos) {
        long horas = segundos / 3600;
        long minutos = (segundos % 3600) / 60;
        long segundosRestantes = segundos % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
    }

    // Método para calcular segundos noturnos
    private long calcularSegundosNoturnos(ZonedDateTime inicio, ZonedDateTime fim) {
        LocalTime inicioNoturno = LocalTime.of(22, 0, 0);
        LocalTime fimNoturno = LocalTime.of(5, 0, 0);
        long segundosNoturnos = 0;

        ZonedDateTime cursor = inicio;
        while (cursor.isBefore(fim)) {
            LocalTime time = cursor.toLocalTime();
            if (time.isAfter(inicioNoturno) || time.isBefore(fimNoturno) || time.equals(inicioNoturno)) {
                segundosNoturnos++;
            }
            cursor = cursor.plusSeconds(1);
        }

        return segundosNoturnos;
    }


}









