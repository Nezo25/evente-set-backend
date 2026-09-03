package tfs.evente_set.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfs.evente_set.dto.ConvidadoDTO;
import tfs.evente_set.service.ConvidadoService;

import java.util.List;

@RestController
@RequestMapping("/api/eventos/{eventoId}/convidados")
@RequiredArgsConstructor
public class ConvidadoController {

    private final ConvidadoService convidadoService;

    @PostMapping
    public ResponseEntity<ConvidadoDTO> adicionarConvidado(@PathVariable Long eventoId, @RequestBody ConvidadoDTO dto) {
        return ResponseEntity.ok(convidadoService.adicionarConvidado(eventoId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ConvidadoDTO>> listarConvidados(@PathVariable Long eventoId) {
        return ResponseEntity.ok(convidadoService.listarConvidados(eventoId));
    }

    @PutMapping("/{id}/acomodar")
    public ResponseEntity<ConvidadoDTO> acomodarConvidado(
            @PathVariable Long eventoId,
            @PathVariable Long id, 
            @RequestParam Long mesaId) {
        return ResponseEntity.ok(convidadoService.acomodarConvidado(id, mesaId));
    }

    @PutMapping("/{id}/cardapio")
    public ResponseEntity<ConvidadoDTO> vincularCardapio(
            @PathVariable Long eventoId,
            @PathVariable Long id,
            @RequestParam Long cardapioId) {
        return ResponseEntity.ok(convidadoService.vincularCardapio(id, cardapioId));
    }
}
