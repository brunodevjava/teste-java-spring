package br.com.prolog.prolog_teste.repository;

import br.com.prolog.prolog_teste.domain.Marcacao;
import br.com.prolog.prolog_teste.domain.MarcacaoVinculoInicioFim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@EnableJpaRepositories
public interface MarcacaoVinculoInicioFimRepository extends JpaRepository<MarcacaoVinculoInicioFim, Long>{
    List<MarcacaoVinculoInicioFim> findByMarcacaoInicioIn(List<Marcacao> marcacoesInicio);

}
