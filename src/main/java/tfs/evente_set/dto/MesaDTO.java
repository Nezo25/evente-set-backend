package tfs.evente_set.dto;

public record MesaDTO(
    Long id,
    Long eventoId,
    String identificador,
    Integer capacidadeMaxima,
    Integer ocupacaoAtual,
    Double positionX,
    Double positionY
) {}
