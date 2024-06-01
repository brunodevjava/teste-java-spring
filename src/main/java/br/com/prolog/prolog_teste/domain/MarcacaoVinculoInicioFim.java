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
@Table(name = "marcacao_vinculo_inicio_fim")
public class MarcacaoVinculoInicioFim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @OneToOne
    @JoinColumn(name = "cod_marcacao_inicio", nullable = false, unique = true)
    private Marcacao marcacaoInicio;

    @OneToOne
    @JoinColumn(name = "cod_marcacao_fim", nullable = false, unique = true)
    private Marcacao marcacaoFim;
}
