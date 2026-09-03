package tfs.evente_set.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cardapios")
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "is_pre_definido", nullable = false)
    private Boolean preDefinido = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cardapio_itens",
        joinColumns = @JoinColumn(name = "cardapio_id"),
        inverseJoinColumns = @JoinColumn(name = "item_cardapio_id")
    )
    private List<ItemCardapio> itens = new ArrayList<>();
}
