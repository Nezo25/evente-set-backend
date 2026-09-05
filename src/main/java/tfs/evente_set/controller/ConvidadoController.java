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

    @PutMapping("/{id}/desacomodar")
    public ResponseEntity<ConvidadoDTO> desacomodarConvidado(
            @PathVariable Long eventoId,
            @PathVariable Long id) {
        return ResponseEntity.ok(convidadoService.desacomodarConvidado(id));
    }

    @PutMapping("/{id}/cardapio")
    public ResponseEntity<ConvidadoDTO> vincularCardapio(
            @PathVariable Long eventoId,
            @PathVariable Long id,
            @RequestParam Long cardapioId) {
        return ResponseEntity.ok(convidadoService.vincularCardapio(id, cardapioId));
    }

    @PostMapping("/{id}/checkin")
    public ResponseEntity<ConvidadoDTO> checkin(
            @PathVariable Long eventoId,
            @PathVariable Long id) {
        return ResponseEntity.ok(convidadoService.checkin(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvidadoDTO> atualizar(
            @PathVariable Long eventoId,
            @PathVariable Long id,
            @RequestBody ConvidadoDTO dto) {
        return ResponseEntity.ok(convidadoService.atualizarConvidado(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long eventoId,
            @PathVariable Long id) {
        convidadoService.deletarConvidado(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/importar")
    public ResponseEntity<List<ConvidadoDTO>> importarConvidados(
            @PathVariable Long eventoId,
            @RequestParam("file") org.springframework.web.multipart.MultipartFile file) {
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(file.getInputStream()));
            String line;
            List<ConvidadoDTO> salvos = new java.util.ArrayList<>();
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { isFirstLine = false; continue; } // assume first line is header: Nome,Tag,Telefone
                String[] cols = line.split(",");
                if (cols.length > 0 && !cols[0].trim().isEmpty()) {
                    String nome = cols[0].trim();
                    String tag = cols.length > 1 ? cols[1].trim() : null;
                    String telefone = cols.length > 2 ? cols[2].trim() : null;
                    
                    ConvidadoDTO dto = new ConvidadoDTO(null, eventoId, null, null, nome, telefone, false, null, tag, null, false, null, null);
                    salvos.add(convidadoService.adicionarConvidado(eventoId, dto));
                }
            }
            return ResponseEntity.ok(salvos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
