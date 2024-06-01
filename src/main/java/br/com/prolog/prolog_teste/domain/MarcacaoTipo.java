package br.com.prolog.prolog_teste.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "marcacao_tipo")
public class MarcacaoTipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tempo_recomendado_minutos")
    private Long tempoRecomendadoMinutos;
}
