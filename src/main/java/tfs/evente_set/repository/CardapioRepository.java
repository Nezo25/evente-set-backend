package tfs.evente_set.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfs.evente_set.domain.Cardapio;

import java.util.List;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
    List<Cardapio> findByPreDefinidoTrue();
    List<Cardapio> findByEventoId(Long eventoId);
}
