package tfs.evente_set.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfs.evente_set.domain.CategoriaEnum;
import tfs.evente_set.dto.ItemCardapioDTO;
import tfs.evente_set.service.ItemCardapioService;

import java.util.List;

@RestController
@RequestMapping("/api/cardapio")
@RequiredArgsConstructor
public class CardapioController {

    private final ItemCardapioService itemCardapioService;

    @PostMapping("/itens")
    public ResponseEntity<ItemCardapioDTO> criarItemCardapio(@RequestBody ItemCardapioDTO dto) {
        return ResponseEntity.ok(itemCardapioService.criarItem(dto));
    }

    @GetMapping("/itens")
    public ResponseEntity<List<ItemCardapioDTO>> listarItensCardapio(@RequestParam(required = false) CategoriaEnum categoria) {
        if (categoria != null) {
            return ResponseEntity.ok(itemCardapioService.listarPorCategoria(categoria));
        }
        return ResponseEntity.ok(itemCardapioService.listarTodos());
    }
}
