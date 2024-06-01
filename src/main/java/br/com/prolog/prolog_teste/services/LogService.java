package br.com.prolog.prolog_teste.services;

import br.com.prolog.prolog_teste.domain.Log;
import br.com.prolog.prolog_teste.dto.LogDTO;
import br.com.prolog.prolog_teste.error.RequestFieldException;
import br.com.prolog.prolog_teste.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

  @Autowired
  private LogRepository repository;

  public void create(LogDTO logDTO) throws RequestFieldException {
    try {
      var log = new Log(logDTO);
      this.repository.save(log);
    } catch (Exception ex) {
      throw new RequestFieldException("Erro ao criar o log: " + ex.getMessage(), "logDTO");
    }
  }
}

