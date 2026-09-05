package tfs.evente_set.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.TenantId;

@Data
@Entity
@Table(name = "itens_cardapio")
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @TenantId
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaEnum categoria;

    private String alergenos;
}
