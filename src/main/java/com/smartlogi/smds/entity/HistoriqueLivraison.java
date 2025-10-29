package com.smartlogi.smds.entity;

import com.smartlogi.smds.entity.StatutColis;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "historique_livraison")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class HistoriqueLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Le statut ne peut pas être nul")
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutColis statut;

    @NotNull(message = "La date de changement ne peut pas être nulle")
    @Column(name = "date_changement")
    private LocalDateTime dateChangement;

    @Size(max = 255, message = "Le commentaire ne peut pas dépasser 255 caractères")
    @Column(name = "commentaire")
    private String commentaire;

    @NotNull(message = "Le colis ne peut pas être nul")
    @ManyToOne
    @JoinColumn(name = "colis_id")
    private Colis colis;
}
// Commit 35 on 2025-10-29 12:15:08
