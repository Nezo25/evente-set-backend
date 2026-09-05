package tfs.evente_set.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.TenantId;

@Data
@Entity
@Table(name = "mesas")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @TenantId
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private String identificador;

    @Column(name = "capacidade_maxima", nullable = false)
    private Integer capacidadeMaxima;

    @Column(name = "position_x")
    private Double positionX = 0.0;

    @Column(name = "position_y")
    private Double positionY = 0.0;
}
