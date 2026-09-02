package tfs.evente_set.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfs.evente_set.domain.Convidado;
import tfs.evente_set.domain.Evento;
import tfs.evente_set.domain.Mesa;
import tfs.evente_set.dto.ConvidadoDTO;
import tfs.evente_set.repository.ConvidadoRepository;
import tfs.evente_set.repository.EventoRepository;
import tfs.evente_set.repository.MesaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConvidadoService {

    private final ConvidadoRepository convidadoRepository;
    private final MesaRepository mesaRepository;
    private final EventoRepository eventoRepository;

    @Transactional
    public ConvidadoDTO adicionarConvidado(Long eventoId, ConvidadoDTO dto) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
                
        Convidado convidado = new Convidado();
        convidado.setEvento(evento);
        convidado.setNome(dto.nome());
        convidado.setConfirmado(dto.confirmado());
        convidado.setRestricoesAlimentares(dto.restricoesAlimentares());
        
        Convidado salvo = convidadoRepository.save(convidado);
        return mapToDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<ConvidadoDTO> listarConvidados(Long eventoId) {
        return convidadoRepository.findByEventoId(eventoId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConvidadoDTO acomodarConvidado(Long convidadoId, Long mesaId) {
        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
                
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
                
        if (!convidado.getEvento().getId().equals(mesa.getEvento().getId())) {
            throw new RuntimeException("A mesa e o convidado não pertencem ao mesmo evento");
        }
        
        int ocupacaoAtual = convidadoRepository.findByMesaId(mesaId).size();
        if (ocupacaoAtual >= mesa.getCapacidadeMaxima()) {
            throw new RuntimeException("Não é possível acomodar: A mesa já está em sua capacidade máxima");
        }
        
        convidado.setMesa(mesa);
        Convidado salvo = convidadoRepository.save(convidado);
        return mapToDTO(salvo);
    }

    private ConvidadoDTO mapToDTO(Convidado c) {
        return new ConvidadoDTO(
                c.getId(), 
                c.getEvento().getId(), 
                c.getMesa() != null ? c.getMesa().getId() : null, 
                c.getNome(), 
                c.getConfirmado(), 
                c.getRestricoesAlimentares()
        );
    }
}
