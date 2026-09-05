package tfs.evente_set.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfs.evente_set.domain.CategoriaEnum;
import tfs.evente_set.dto.CardapioDTO;
import tfs.evente_set.dto.ItemCardapioDTO;
import tfs.evente_set.service.CardapioService;
import tfs.evente_set.service.ItemCardapioService;

import java.util.List;

@RestController
@RequestMapping("/api/cardapio")
@RequiredArgsConstructor
public class CardapioController {

    private final ItemCardapioService itemCardapioService;
    private final CardapioService cardapioService;

    // --- Endpoints de Itens do Cardápio ---

    @PostMapping("/itens")
    public ResponseEntity<ItemCardapioDTO> criarItemCardapio(@RequestBody ItemCardapioDTO dto) {
        return ResponseEntity.ok(itemCardapioService.criarItem(dto));
    }
    
    @PutMapping("/itens/{id}")
    public ResponseEntity<ItemCardapioDTO> atualizarItemCardapio(@PathVariable Long id, @RequestBody ItemCardapioDTO dto) {
        return ResponseEntity.ok(itemCardapioService.atualizarItem(id, dto));
    }
    
    @DeleteMapping("/itens/{id}")
    public ResponseEntity<Void> deletarItemCardapio(@PathVariable Long id) {
        itemCardapioService.deletarItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/itens")
    public ResponseEntity<List<ItemCardapioDTO>> listarItensCardapio(@RequestParam(required = false) CategoriaEnum categoria) {
        if (categoria != null) {
            return ResponseEntity.ok(itemCardapioService.listarPorCategoria(categoria));
        }
        return ResponseEntity.ok(itemCardapioService.listarTodos());
    }

    // --- Endpoints de Cardápios (Modelos) ---

    @GetMapping("/pre-definidos")
    public ResponseEntity<List<CardapioDTO>> listarCardapiosPreDefinidos(@RequestParam(required = false) String tipoEvento) {
        return ResponseEntity.ok(cardapioService.listarPreDefinidos(tipoEvento));
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<CardapioDTO>> listarCardapiosDoEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(cardapioService.listarPorEvento(eventoId));
    }

    @PostMapping("/evento/{eventoId}")
    public ResponseEntity<CardapioDTO> criarCardapio(@PathVariable(required = false) Long eventoId, @RequestBody CardapioDTO dto) {
        return ResponseEntity.ok(cardapioService.criarCardapio(eventoId, dto));
    }
    
    @PostMapping
    public ResponseEntity<CardapioDTO> criarCardapioGlobal(@RequestBody CardapioDTO dto) {
        return ResponseEntity.ok(cardapioService.criarCardapio(null, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioDTO> atualizarCardapio(@PathVariable Long id, @RequestBody CardapioDTO dto) {
        return ResponseEntity.ok(cardapioService.atualizarCardapio(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCardapio(@PathVariable Long id) {
        cardapioService.excluirCardapio(id);
        return ResponseEntity.noContent().build();
    }
}
