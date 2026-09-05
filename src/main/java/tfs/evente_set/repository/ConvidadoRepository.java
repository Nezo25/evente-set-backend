package tfs.evente_set.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfs.evente_set.domain.Convidado;
import java.util.List;

@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
    List<Convidado> findByEventoId(Long eventoId);
    List<Convidado> findByMesaId(Long mesaId);
    java.util.Optional<Convidado> findByTokenRsvp(String tokenRsvp);
}
