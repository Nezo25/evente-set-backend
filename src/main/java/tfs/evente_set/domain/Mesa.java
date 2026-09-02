package tfs.evente_set.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mesas")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private String identificador;

    @Column(name = "capacidade_maxima", nullable = false)
    private Integer capacidadeMaxima;
}
