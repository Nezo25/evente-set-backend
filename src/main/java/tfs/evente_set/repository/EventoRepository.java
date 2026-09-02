package tfs.evente_set.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfs.evente_set.domain.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
