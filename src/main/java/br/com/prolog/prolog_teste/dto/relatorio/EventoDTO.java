package br.com.prolog.prolog_teste.dto.relatorio;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public record EventoDTO(
        String tipoMarcacao,
        Optional<MarcacoesDeTempoInicioDTO> INICIO,
        Optional<MarcacoesDeTempoFimDTO> FIM

) {}
