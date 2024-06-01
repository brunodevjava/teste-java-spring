package br.com.prolog.prolog_teste.repository;

import br.com.prolog.prolog_teste.domain.RecuperacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface RecuperacaoSenhaRepository extends JpaRepository<RecuperacaoSenha, Long>{
  RecuperacaoSenha findByHash(String hash);
}
