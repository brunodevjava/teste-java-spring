package br.com.prolog.prolog_teste.error;

import lombok.Getter;

@Getter
public class RequestFieldException extends RuntimeException {

  private final String fieldName;

  public RequestFieldException(String errorMessage, String fieldName) {
    super(errorMessage);
    this.fieldName = fieldName;
  }

}
