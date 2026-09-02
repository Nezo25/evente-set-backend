package tfs.evente_set.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "convidados")
public class Convidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @Column(nullable = false)
    private String nome;

    private Boolean confirmado = false;

    @Column(name = "restricoes_alimentares")
    private String restricoesAlimentares;
}
