package com.smartlogi.smds.dto;

import com.smartlogi.smds.entity.StatutColis;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColisDTO {
    private String id;

    @NotBlank(message = "La description ne peut pas être vide")
    @Size(max = 255, message = "La description ne peut pas dépasser 255 caractères")
    private String description;

    @NotNull(message = "Le poids ne peut pas être nul")
    @Positive(message = "Le poids doit être un nombre positif")
    private Double poids;

    @NotBlank(message = "La priorité ne peut pas être vide")
    private String priorite;

    @NotBlank(message = "La ville de destination ne peut pas être vide")
    private String villeDestination;

    @NotNull(message = "Le statut ne peut pas être nul")
    private StatutColis statutColis;

    private LocalDate dateCreation;

    @NotBlank(message = "L'ID du destinataire ne peut pas être vide")
    private String destinataireId;

    @NotBlank(message = "L'ID du client expéditeur ne peut pas être vide")
    private String clientExpediteurId;

    private String livreurId;

    @NotBlank(message = "L'ID de la zone ne peut pas être vide")
    private String zoneId;
}
