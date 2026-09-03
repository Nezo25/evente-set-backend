package tfs.evente_set.dto;

public record ConvidadoDTO(
    Long id,
    Long eventoId,
    Long mesaId,
    Long cardapioId,
    String nome,
    Boolean confirmado,
    String restricoesAlimentares
) {}
