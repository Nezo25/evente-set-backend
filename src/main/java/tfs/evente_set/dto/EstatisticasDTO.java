package tfs.evente_set.dto;

public record EstatisticasDTO(
    long totalConvidados,
    long totalConfirmados,
    long totalPresentes,
    long totalPendentes,
    long mesasOcupadas,
    long mesasLivres,
    java.util.Map<String, Long> restricoesAlimentares
) {}
