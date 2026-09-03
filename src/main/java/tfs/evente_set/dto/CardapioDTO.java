package tfs.evente_set.dto;

import java.util.List;

public record CardapioDTO(
    Long id,
    String nome,
    Boolean preDefinido,
    Long eventoId,
    List<ItemCardapioDTO> itens
) {}
