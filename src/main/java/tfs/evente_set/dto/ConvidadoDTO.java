package tfs.evente_set.dto;

public record ConvidadoDTO(
    Long id,
    Long eventoId,
    Long mesaId,
    String nome,
    Boolean confirmado,
    String restricoesAlimentares
) {}
