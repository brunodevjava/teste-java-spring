package br.com.prolog.prolog_teste.dto.colaborador;


import br.com.prolog.prolog_teste.domain.Colaborador;

public record UserDetailDTO(
        String cpf,
        String nome,
        String email

) {

    public UserDetailDTO(Colaborador colaborador) {
        this(colaborador.getCpf(), colaborador.getNome(), colaborador.getEmail());
    }

}
