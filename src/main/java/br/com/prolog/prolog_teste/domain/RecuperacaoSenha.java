package br.com.prolog.prolog_teste.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recuperacao_senha")
public class RecuperacaoSenha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "cpf")
    private Colaborador colaborador;

    @Column(name = "hash")
    private String hash;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "expirou")
    private Boolean expirou;

    @Column(name = "uso")
    private Boolean uso;

    @PrePersist
    public void prePersist() {
        dataCriacao = new Date();
    }

    public RecuperacaoSenha(String hash, Colaborador colaborador) {
        this.hash = hash;
        this.colaborador = colaborador;
        this.expirou = false;
        this.uso = false;
        this.dataCriacao = new Date();
    }

    public RecuperacaoSenha use() {
        this.uso = true;
        return this;
    }

    public RecuperacaoSenha expire() {
        this.expirou = true;
        return this;
    }

    public Boolean isActive() {
        return !this.expirou && !this.uso;
    }
}
