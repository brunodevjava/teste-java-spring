package br.com.prolog.prolog_teste.domain;

import br.com.prolog.prolog_teste.dto.LogDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "log")
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome_tabela")
    private String nomeTabela;

    @Enumerated(EnumType.STRING)
    private LogAction acao;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cpf")
    private Colaborador colaborador;

    public enum LogAction {
        CREATE,
        UPDATE,
        DELETE,
    }

    public Log(LogDTO logDTO) {
        this.nomeTabela = logDTO.nomeTabela();
        this.acao = logDTO.acao();
        this.dataCriacao = OffsetDateTime.now();
        this.colaborador = logDTO.colaborador();
    }
}
