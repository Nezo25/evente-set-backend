package tfs.evente_set.dto;

import tfs.evente_set.domain.CategoriaEnum;

public record ItemCardapioDTO(
    Long id,
    String nome,
    String descricao,
    CategoriaEnum categoria,
    String alergenos
) {}
