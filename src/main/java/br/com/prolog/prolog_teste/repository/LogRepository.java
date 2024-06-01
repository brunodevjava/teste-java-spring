package br.com.prolog.prolog_teste.repository;

import br.com.prolog.prolog_teste.domain.Colaborador;
import br.com.prolog.prolog_teste.domain.Log;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

@EnableJpaRepositories
public interface LogRepository extends JpaRepository<Log, Long> {

}
