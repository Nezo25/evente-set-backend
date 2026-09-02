package tfs.evente_set.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfs.evente_set.domain.Evento;
import tfs.evente_set.dto.EventoDTO;
import tfs.evente_set.repository.EventoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventoService {
    
    private final EventoRepository eventoRepository;

    @Transactional(readOnly = true)
    public List<EventoDTO> listarTodos() {
        return eventoRepository.findAll().stream()
                .map(e -> new EventoDTO(e.getId(), e.getNomeCliente(), e.getTipoEvento(), e.getDataEvento(), e.getTotalConvidadosEstimado(), e.getStatus()))
                .collect(Collectors.toList());
    }

    @Transactional
    public EventoDTO criarEvento(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setNomeCliente(dto.nomeCliente());
        evento.setTipoEvento(dto.tipoEvento());
        evento.setDataEvento(dto.dataEvento());
        evento.setTotalConvidadosEstimado(dto.totalConvidadosEstimado());
        
        Evento salvo = eventoRepository.save(evento);
        return new EventoDTO(salvo.getId(), salvo.getNomeCliente(), salvo.getTipoEvento(), salvo.getDataEvento(), salvo.getTotalConvidadosEstimado(), salvo.getStatus());
    }

    @Transactional(readOnly = true)
    public EventoDTO buscarPorId(Long id) {
        Evento e = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return new EventoDTO(e.getId(), e.getNomeCliente(), e.getTipoEvento(), e.getDataEvento(), e.getTotalConvidadosEstimado(), e.getStatus());
    }

    @Transactional
    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
