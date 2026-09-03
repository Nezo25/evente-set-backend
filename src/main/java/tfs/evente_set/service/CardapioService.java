package tfs.evente_set.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfs.evente_set.domain.Cardapio;
import tfs.evente_set.domain.Evento;
import tfs.evente_set.domain.ItemCardapio;
import tfs.evente_set.dto.CardapioDTO;
import tfs.evente_set.dto.ItemCardapioDTO;
import tfs.evente_set.repository.CardapioRepository;
import tfs.evente_set.repository.EventoRepository;
import tfs.evente_set.repository.ItemCardapioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardapioService {

    private final CardapioRepository cardapioRepository;
    private final EventoRepository eventoRepository;
    private final ItemCardapioRepository itemCardapioRepository;

    @Transactional(readOnly = true)
    public List<CardapioDTO> listarPreDefinidos() {
        return cardapioRepository.findByPreDefinidoTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CardapioDTO> listarPorEvento(Long eventoId) {
        return cardapioRepository.findByEventoId(eventoId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CardapioDTO criarCardapio(Long eventoId, CardapioDTO dto) {
        Cardapio cardapio = new Cardapio();
        cardapio.setNome(dto.nome());
        cardapio.setPreDefinido(dto.preDefinido() != null ? dto.preDefinido() : false);

        if (eventoId != null) {
            Evento evento = eventoRepository.findById(eventoId)
                    .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
            cardapio.setEvento(evento);
        }

        if (dto.itens() != null && !dto.itens().isEmpty()) {
            List<Long> itemIds = dto.itens().stream().map(ItemCardapioDTO::id).collect(Collectors.toList());
            List<ItemCardapio> itens = itemCardapioRepository.findAllById(itemIds);
            cardapio.setItens(itens);
        }

        cardapio = cardapioRepository.save(cardapio);
        return toDTO(cardapio);
    }

    private CardapioDTO toDTO(Cardapio cardapio) {
        List<ItemCardapioDTO> itensDTO = cardapio.getItens().stream()
                .map(i -> new ItemCardapioDTO(i.getId(), i.getNome(), i.getDescricao(), i.getCategoria(), i.getAlergenos()))
                .collect(Collectors.toList());

        Long eventoId = cardapio.getEvento() != null ? cardapio.getEvento().getId() : null;

        return new CardapioDTO(
                cardapio.getId(),
                cardapio.getNome(),
                cardapio.getPreDefinido(),
                eventoId,
                itensDTO
        );
    }
}
