package br.com.prolog.prolog_teste.repository;

import br.com.prolog.prolog_teste.domain.Marcacao;
import br.com.prolog.prolog_teste.domain.MarcacaoVinculoInicioFim;
import br.com.prolog.prolog_teste.domain.RecuperacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface MarcacaoRepository extends JpaRepository<Marcacao, Long> {
    List<Marcacao> findByColaboradorCpf(String cpf);
    List<Marcacao> findByColaboradorCpfAndDataHoraMarcacaoBeforeOrderByDataHoraMarcacao(String cpf, OffsetDateTime inicio);


}
