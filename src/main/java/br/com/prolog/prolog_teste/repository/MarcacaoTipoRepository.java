package br.com.prolog.prolog_teste.repository;

import br.com.prolog.prolog_teste.domain.Marcacao;
import br.com.prolog.prolog_teste.domain.MarcacaoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.OffsetDateTime;
import java.util.List;

@EnableJpaRepositories
public interface MarcacaoTipoRepository extends JpaRepository<MarcacaoTipo, Long>{

}
