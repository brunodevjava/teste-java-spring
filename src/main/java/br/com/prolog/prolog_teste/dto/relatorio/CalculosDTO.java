package br.com.prolog.prolog_teste.dto.relatorio;

import java.util.List;

public record CalculosDTO(
        String tipoMarcacaoNumero,
        String tipoMarcacaoNome,
        String tempoRecomendado,
        String totalPeriodo,
        String horasNoturnas
) {}
