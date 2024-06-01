package br.com.prolog.prolog_teste.services;

import br.com.prolog.prolog_teste.domain.RecuperacaoSenha;
import br.com.prolog.prolog_teste.dto.colaborador.PasswordRecoveryRecoverDTO;
import br.com.prolog.prolog_teste.repository.ColaboradorRepository;
import br.com.prolog.prolog_teste.repository.RecuperacaoSenhaRepository;
import br.com.prolog.prolog_teste.security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;

@Service
public class RecuperacaoSenhaService {

  @Autowired
  private ColaboradorRepository colaboradorRepository;
  
  @Autowired
  private ColaboradorService colaboradorService;

  @Autowired
  private RecuperacaoSenhaRepository repository;

  @Autowired
  private PasswordService passwordService;

  public String init(String cpf) {
    var hash = new BigInteger(130, new SecureRandom()).toString(32);
    var colaborador = this.colaboradorService.getColaborador(cpf);

    var recuperacaoSenha = new RecuperacaoSenha(hash, colaborador);
    this.repository.save(recuperacaoSenha);

    return recuperacaoSenha.getHash();
  }

  public void recover(PasswordRecoveryRecoverDTO passwordRecoveryRecoverDTO) {
    var recuperarSenha = this.repository.findByHash(passwordRecoveryRecoverDTO.chaveRecuperacao());
    if (!recuperarSenha.isActive() || !Objects.equals(recuperarSenha.getColaborador().getCpf(), passwordRecoveryRecoverDTO.cpf())) {
      return;
    }

    var user = recuperarSenha.getColaborador();
    user.setPassword(this.passwordService.encodePassword(passwordRecoveryRecoverDTO.novaSenha()));

    this.colaboradorRepository.save(user);

    recuperarSenha.use();
    this.repository.save(recuperarSenha);
  }
  
}
