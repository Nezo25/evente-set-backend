package tfs.evente_set.dto;

import java.util.List;

public record CardapioDTO(
    Long id,
    String nome,
    Boolean preDefinido,
    String tipoEvento,
    Long eventoId,
    List<ItemCardapioDTO> itens
) {}
