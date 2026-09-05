package tfs.evente_set.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfs.evente_set.dto.EstatisticasDTO;
import tfs.evente_set.service.EstatisticaService;

@RestController
@RequestMapping("/api/eventos/{eventoId}/estatisticas")
@RequiredArgsConstructor
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity<EstatisticasDTO> getEstatisticas(@PathVariable Long eventoId) {
        return ResponseEntity.ok(estatisticaService.gerarEstatisticas(eventoId));
    }
}
