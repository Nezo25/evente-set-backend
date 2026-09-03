package tfs.evente_set.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tfs.evente_set.domain.Cardapio;
import tfs.evente_set.domain.Evento;
import tfs.evente_set.dto.CardapioDTO;
import tfs.evente_set.repository.CardapioRepository;
import tfs.evente_set.repository.EventoRepository;
import tfs.evente_set.repository.ItemCardapioRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardapioServiceTest {

    @Mock
    private CardapioRepository cardapioRepository;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private CardapioService cardapioService;

    private Cardapio cardapioGold;
    private Evento evento;

    @BeforeEach
    void setUp() {
        cardapioGold = new Cardapio();
        cardapioGold.setId(1L);
        cardapioGold.setNome("Gold");
        cardapioGold.setPreDefinido(true);

        evento = new Evento();
        evento.setId(10L);
    }

    @Test
    void deveListarCardapiosPreDefinidos() {
        when(cardapioRepository.findByPreDefinidoTrue()).thenReturn(Arrays.asList(cardapioGold));

        List<CardapioDTO> result = cardapioService.listarPreDefinidos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Gold", result.get(0).nome());
        verify(cardapioRepository, times(1)).findByPreDefinidoTrue();
    }

    @Test
    void deveCriarCardapioPersonalizado() {
        CardapioDTO dto = new CardapioDTO(null, "Meu Menu", false, 10L, List.of());
        
        when(eventoRepository.findById(10L)).thenReturn(Optional.of(evento));
        
        Cardapio salvo = new Cardapio();
        salvo.setId(2L);
        salvo.setNome("Meu Menu");
        salvo.setPreDefinido(false);
        salvo.setEvento(evento);
        
        when(cardapioRepository.save(any(Cardapio.class))).thenReturn(salvo);

        CardapioDTO result = cardapioService.criarCardapio(10L, dto);

        assertNotNull(result);
        assertEquals("Meu Menu", result.nome());
        assertEquals(10L, result.eventoId());
        assertFalse(result.preDefinido());
        verify(eventoRepository, times(1)).findById(10L);
        verify(cardapioRepository, times(1)).save(any(Cardapio.class));
    }
}
