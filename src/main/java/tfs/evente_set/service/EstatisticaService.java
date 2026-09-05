package tfs.evente_set.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfs.evente_set.domain.Convidado;
import tfs.evente_set.domain.Mesa;
import tfs.evente_set.dto.EstatisticasDTO;
import tfs.evente_set.repository.ConvidadoRepository;
import tfs.evente_set.repository.MesaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EstatisticaService {

    private final ConvidadoRepository convidadoRepository;
    private final MesaRepository mesaRepository;

    @Transactional(readOnly = true)
    public EstatisticasDTO gerarEstatisticas(Long eventoId) {
        List<Convidado> convidados = convidadoRepository.findByEventoId(eventoId);
        List<Mesa> mesas = mesaRepository.findByEventoId(eventoId);

        long totalConvidados = convidados.size();
        long totalConfirmados = convidados.stream().filter(c -> c.getConfirmado() != null && c.getConfirmado()).count();
        long totalPresentes = convidados.stream().filter(c -> c.getPresente() != null && c.getPresente()).count();
        long totalPendentes = totalConvidados - totalConfirmados;

        long mesasOcupadas = mesas.stream().filter(m -> !convidadoRepository.findByMesaId(m.getId()).isEmpty()).count();
        long mesasLivres = mesas.size() - mesasOcupadas;

        Map<String, Long> restricoesMap = new HashMap<>();
        convidados.stream()
            .filter(c -> c.getRestricoesAlimentares() != null && !c.getRestricoesAlimentares().trim().isEmpty())
            .forEach(c -> {
                String restricao = c.getRestricoesAlimentares().toLowerCase().trim();
                restricoesMap.put(restricao, restricoesMap.getOrDefault(restricao, 0L) + 1);
            });

        return new EstatisticasDTO(
                totalConvidados,
                totalConfirmados,
                totalPresentes,
                totalPendentes,
                mesasOcupadas,
                mesasLivres,
                restricoesMap
        );
    }
}
