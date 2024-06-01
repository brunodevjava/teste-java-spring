package br.com.prolog.prolog_teste.repository;

import br.com.prolog.prolog_teste.domain.Colaborador;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

@EnableJpaRepositories
public interface ColaboradorRepository extends JpaRepository<Colaborador, String> {

    Colaborador findByEmailAndStatusTrue(String email);

    Page<Colaborador> findAllByStatusTrue(Pageable pageable);

    Page<Colaborador> findAllByStatusFalse(Pageable pageable);

    @Query("SELECT COUNT(u.cpf) > 0 FROM Colaborador u WHERE u.email = :email")
    Boolean emailExists(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE Colaborador c SET c.status = false WHERE c.cpf = :cpf")
    void delete(@Param("cpf") String cpf);

    @Query("SELECT c FROM Colaborador c WHERE c.cpf = :cpf")
    Colaborador findByCpf(@Param("cpf") String cpf);

}
