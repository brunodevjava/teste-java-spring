package br.com.prolog.prolog_teste.dto.colaborador;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record PasswordRecoveryRecoverDTO(
        @NotBlank(message = "Nova senha é obrigatório")
        String novaSenha,

        @NotBlank(message = "Nova senha confirmação é obrigatório")
        String novaSenhaConfirmacao,

        @NotBlank(message = "Chave de recuperção é obrigatório")
        String chaveRecuperacao,

        @CPF(message = "CPF inválido")
        String cpf
) {

}
