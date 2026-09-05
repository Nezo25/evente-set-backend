package tfs.evente_set.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfs.evente_set.domain.Evento;
import tfs.evente_set.domain.Mesa;
import tfs.evente_set.dto.MesaDTO;
import tfs.evente_set.repository.EventoRepository;
import tfs.evente_set.repository.MesaRepository;
import tfs.evente_set.repository.ConvidadoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final EventoRepository eventoRepository;
    private final ConvidadoRepository convidadoRepository;

    @Transactional
    public MesaDTO criarMesa(Long eventoId, MesaDTO dto) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
                
        Mesa mesa = new Mesa();
        mesa.setEvento(evento);
        mesa.setIdentificador(dto.identificador());
        mesa.setCapacidadeMaxima(dto.capacidadeMaxima());
        
        Mesa salva = mesaRepository.save(mesa);
        return new MesaDTO(salva.getId(), salva.getEvento().getId(), salva.getIdentificador(), salva.getCapacidadeMaxima(), 0, salva.getPositionX(), salva.getPositionY());
    }

    @Transactional
    public MesaDTO atualizarPosicao(Long id, Double positionX, Double positionY) {
        Mesa mesa = mesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        mesa.setPositionX(positionX);
        mesa.setPositionY(positionY);
        Mesa salva = mesaRepository.save(mesa);
        int ocupacao = convidadoRepository.findByMesaId(salva.getId()).size();
        return new MesaDTO(salva.getId(), salva.getEvento().getId(), salva.getIdentificador(), salva.getCapacidadeMaxima(), ocupacao, salva.getPositionX(), salva.getPositionY());
    }

    @Transactional(readOnly = true)
    public List<MesaDTO> listarMesasDoEvento(Long eventoId) {
        return mesaRepository.findByEventoId(eventoId).stream()
                .map(m -> {
                    int ocupacao = convidadoRepository.findByMesaId(m.getId()).size();
                    return new MesaDTO(m.getId(), m.getEvento().getId(), m.getIdentificador(), m.getCapacidadeMaxima(), ocupacao, m.getPositionX(), m.getPositionY());
                })
                .collect(Collectors.toList());
    }
}
