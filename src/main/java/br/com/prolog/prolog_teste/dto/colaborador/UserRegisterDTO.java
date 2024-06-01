package br.com.prolog.prolog_teste.dto.colaborador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UserRegisterDTO(
  @Email(message = "Endereço de e-mail inválido!")
  @NotBlank(message = "E-mail é obrigatório")
  String email,

  @NotBlank(message = "CPF é obrigatório")
  @CPF(message = "CPF está inválido")
  String cpf,

  @NotBlank(message = "Senha é obrigatória")
  String senha,

  @NotBlank(message = "Nome é obrigatório")
  String nome


) {}
