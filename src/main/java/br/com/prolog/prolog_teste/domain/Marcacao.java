package br.com.prolog.prolog_teste.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "marcacao")
public class Marcacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "cod_tipo_marcacao", nullable = false)
    private MarcacaoTipo tipoMarcacao;

    @ManyToOne
    @JoinColumn(name = "cpf_colaborador", nullable = false)
    private Colaborador colaborador;

    @Column(name = "data_hora_marcacao", nullable = false)
    @CreationTimestamp
    private OffsetDateTime dataHoraMarcacao;

    @Column(name = "tipo_marcacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMarcacao tipoMarcacaoEnum;

    public enum TipoMarcacao {
        MARCACAO_INICIO, MARCACAO_FIM
    }
}
