package tfs.evente_set.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfs.evente_set.domain.Cardapio;
import tfs.evente_set.domain.Convidado;
import tfs.evente_set.domain.Evento;
import tfs.evente_set.domain.Mesa;
import tfs.evente_set.dto.ConvidadoDTO;
import tfs.evente_set.repository.CardapioRepository;
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
    private final CardapioRepository cardapioRepository;

    @Transactional
    public ConvidadoDTO adicionarConvidado(Long eventoId, ConvidadoDTO dto) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
                
        Convidado convidado = new Convidado();
        convidado.setEvento(evento);
        convidado.setNome(dto.nome());
        convidado.setTelefone(dto.telefone());
        convidado.setConfirmado(dto.confirmado());
        convidado.setRestricoesAlimentares(dto.restricoesAlimentares());
        convidado.setTag(dto.tag());
        convidado.setGrupoFamilia(dto.grupoFamilia());
        convidado.setTokenRsvp(java.util.UUID.randomUUID().toString());
        
        Convidado salvo = convidadoRepository.save(convidado);
        return mapToDTO(salvo);
    }
    
    @Transactional
    public ConvidadoDTO atualizarConvidado(Long id, ConvidadoDTO dto) {
        Convidado convidado = convidadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
        convidado.setNome(dto.nome());
        convidado.setTelefone(dto.telefone());
        convidado.setTag(dto.tag());
        convidado.setGrupoFamilia(dto.grupoFamilia());
        if (dto.confirmado() != null) convidado.setConfirmado(dto.confirmado());
        if (dto.restricoesAlimentares() != null) convidado.setRestricoesAlimentares(dto.restricoesAlimentares());
        
        return mapToDTO(convidadoRepository.save(convidado));
    }
    
    @Transactional
    public void deletarConvidado(Long id) {
        convidadoRepository.deleteById(id);
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

    @Transactional
    public ConvidadoDTO desacomodarConvidado(Long convidadoId) {
        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
                
        convidado.setMesa(null);
        Convidado salvo = convidadoRepository.save(convidado);
        return mapToDTO(salvo);
    }

    @Transactional
    public ConvidadoDTO vincularCardapio(Long convidadoId, Long cardapioId) {
        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
                
        Cardapio cardapio = cardapioRepository.findById(cardapioId)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado"));
                
        if (!cardapio.getPreDefinido() && (cardapio.getEvento() == null || !cardapio.getEvento().getId().equals(convidado.getEvento().getId()))) {
            throw new RuntimeException("O cardápio selecionado não está disponível para este evento");
        }
        
        convidado.setCardapio(cardapio);
        Convidado salvo = convidadoRepository.save(convidado);
        return mapToDTO(salvo);
    }

    @Transactional
    public ConvidadoDTO checkin(Long convidadoId) {
        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
        convidado.setPresente(true);
        convidado.setDataHoraCheckin(java.time.LocalDateTime.now());
        return mapToDTO(convidadoRepository.save(convidado));
    }

    @Transactional(readOnly = true)
    public ConvidadoDTO buscarPorTokenRsvp(String token) {
        Convidado convidado = convidadoRepository.findByTokenRsvp(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));
        return mapToDTO(convidado);
    }

    @Transactional
    public ConvidadoDTO confirmarRsvp(String token, ConvidadoDTO dto) {
        Convidado convidado = convidadoRepository.findByTokenRsvp(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));
        convidado.setConfirmado(dto.confirmado());
        convidado.setRestricoesAlimentares(dto.restricoesAlimentares());
        return mapToDTO(convidadoRepository.save(convidado));
    }

    @Transactional
    public ConvidadoDTO atualizarTelefone(Long id, String telefone) {
        Convidado convidado = convidadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado"));
        convidado.setTelefone(telefone);
        return mapToDTO(convidadoRepository.save(convidado));
    }

    private ConvidadoDTO mapToDTO(Convidado c) {
        return new ConvidadoDTO(
                c.getId(), 
                c.getEvento().getId(), 
                c.getMesa() != null ? c.getMesa().getId() : null, 
                c.getCardapio() != null ? c.getCardapio().getId() : null,
                c.getNome(), 
                c.getTelefone(),
                c.getConfirmado(), 
                c.getRestricoesAlimentares(),
                c.getTag(),
                c.getTokenRsvp(),
                c.getPresente(),
                c.getDataHoraCheckin(),
                c.getGrupoFamilia()
        );
    }
}
