package tfs.evente_set.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfs.evente_set.dto.MesaDTO;
import tfs.evente_set.service.MesaService;

import java.util.List;

@RestController
@RequestMapping("/api/eventos/{eventoId}/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaDTO> criarMesa(@PathVariable Long eventoId, @RequestBody MesaDTO dto) {
        return ResponseEntity.ok(mesaService.criarMesa(eventoId, dto));
    }

    @GetMapping
    public ResponseEntity<List<MesaDTO>> listarMesasDoEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(mesaService.listarMesasDoEvento(eventoId));
    }
}
