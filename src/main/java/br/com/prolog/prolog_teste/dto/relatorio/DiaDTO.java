package br.com.prolog.prolog_teste.dto.relatorio;

import java.util.List;

public record DiaDTO(
        String dia,
        List<EventoDTO> eventos
) {}
