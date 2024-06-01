package br.com.prolog.prolog_teste.dto.colaborador;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UserChangePasswordDTO(
  @NotBlank(message = "Senha atual obrigatória")
  String senhaAntiga,

  @NotBlank(message = "Nova senha atual obrigatória")
  String novaSenha,

  @CPF(message = "CPF inválido")
  String cpf

) {
  
}
