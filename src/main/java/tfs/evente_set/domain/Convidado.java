package tfs.evente_set.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.TenantId;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "convidados")
public class Convidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @TenantId
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;

    @Column(nullable = false)
    private String nome;

    private String telefone; // Numero do telefone para WhatsApp

    private Boolean confirmado = false;

    @Column(name = "restricoes_alimentares")
    private String restricoesAlimentares;

    // Fase 1: Tags e RSVP
    private String tag; // ex: VIP, Criança
    
    @Column(name = "token_rsvp", unique = true)
    private String tokenRsvp;

    // Fase 2: Check-in
    private Boolean presente = false;
    
    @Column(name = "data_hora_checkin")
    private java.time.LocalDateTime dataHoraCheckin;

    // Fase 4: Agrupamento Familiar
    @Column(name = "grupo_familia")
    private String grupoFamilia;
}
