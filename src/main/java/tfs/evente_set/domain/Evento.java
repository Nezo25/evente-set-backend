package tfs.evente_set.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "tipo_evento", nullable = false)
    private String tipoEvento;

    @Column(name = "data_evento", nullable = false)
    private LocalDateTime dataEvento;

    @Column(name = "total_convidados_estimado")
    private Integer totalConvidadosEstimado;

    private String status = "AGENDADO";

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
