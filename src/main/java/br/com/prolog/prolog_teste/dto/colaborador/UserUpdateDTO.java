package br.com.prolog.prolog_teste.dto.colaborador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(
  @Email(message = "Endereço de e-mail inválido!")
  String email,

  @NotBlank(message = "Nome é obrigatório")
  String nome
) {}
