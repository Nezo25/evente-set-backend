package tfs.evente_set.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfs.evente_set.domain.ItemCardapio;
import tfs.evente_set.domain.CategoriaEnum;
import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    List<ItemCardapio> findByCategoria(CategoriaEnum categoria);
}
