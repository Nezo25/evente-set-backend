package tfs.evente_set.dto;

import java.time.LocalDateTime;

public record EventoDTO(
    Long id,
    String nomeCliente,
    String tipoEvento,
    LocalDateTime dataEvento,
    Integer totalConvidadosEstimado,
    String status
) {}
