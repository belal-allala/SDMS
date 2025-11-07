package com.smartlogi.smds.dto;

import com.smartlogi.smds.entity.StatutColis;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriqueLivraisonDTO {

    private String id;

    @NotNull(message = "Le statut ne peut pas être nul")
    private StatutColis statut;

    @NotNull(message = "La date de changement ne peut pas être nulle")
    private LocalDateTime dateChangement;

    @Size(max = 255, message = "Le commentaire ne peut pas dépasser 255 caractères")
    private String commentaire;

    @NotBlank(message = "L'ID du colis ne peut pas être vide")
    private String colisId;
}

