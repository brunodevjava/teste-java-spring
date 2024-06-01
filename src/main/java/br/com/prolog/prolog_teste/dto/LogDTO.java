package br.com.prolog.prolog_teste.dto;

import br.com.prolog.prolog_teste.domain.Colaborador;
import br.com.prolog.prolog_teste.domain.Log;

public record LogDTO(
        Log.LogAction acao,
        String nomeTabela,
        Colaborador colaborador

) {
}
