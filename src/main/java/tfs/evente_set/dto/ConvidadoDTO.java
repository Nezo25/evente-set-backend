package tfs.evente_set.dto;

public record ConvidadoDTO(
    Long id,
    Long eventoId,
    Long mesaId,
    Long cardapioId,
    String nome,
    String telefone,
    Boolean confirmado,
    String restricoesAlimentares,
    String tag,
    String tokenRsvp,
    Boolean presente,
    java.time.LocalDateTime dataHoraCheckin,
    String grupoFamilia
) {}
