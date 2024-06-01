package br.com.prolog.prolog_teste.dto.relatorio;

import java.util.List;

public record RelatorioDTO(
        String companhia,
        String período,
        String gerado,
        String colaborador,
        List<DiaDTO> dias,
        List<CalculosDTO> calculos

) {}
