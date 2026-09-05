package tfs.evente_set.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfs.evente_set.dto.ConvidadoDTO;
import tfs.evente_set.service.ConvidadoService;

@RestController
@RequestMapping("/api/rsvp")
@RequiredArgsConstructor
public class RSVPController {

    private final ConvidadoService convidadoService;

    @GetMapping("/{token}")
    public ResponseEntity<ConvidadoDTO> buscarConvidadoPorToken(@PathVariable String token) {
        return ResponseEntity.ok(convidadoService.buscarPorTokenRsvp(token));
    }

    @PutMapping("/{token}")
    public ResponseEntity<ConvidadoDTO> confirmarRsvp(@PathVariable String token, @RequestBody ConvidadoDTO dto) {
        return ResponseEntity.ok(convidadoService.confirmarRsvp(token, dto));
    }
}
