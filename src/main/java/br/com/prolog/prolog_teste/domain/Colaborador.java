package br.com.prolog.prolog_teste.domain;

import br.com.prolog.prolog_teste.dto.colaborador.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "colaborador")
public class Colaborador implements UserDetails {

    @Id
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "senha")
    private String senha;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @PrePersist
    public void prePersist() {
        dataCriacao = OffsetDateTime.now();
        status = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status;
    }

    public Colaborador setPassword(String senha) {
        this.senha = senha;
        return this;
    }

    public Colaborador(UserRegisterDTO userRegisterDTO) {
        this.email = userRegisterDTO.email();
        this.nome = userRegisterDTO.nome();
        this.dataCriacao = OffsetDateTime.now();
        this.cpf = userRegisterDTO.cpf();
        this.status = true;
    }

}
