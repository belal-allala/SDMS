package com.smartlogi.smds.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Colis_ProduitDTO {
    private String id;

    @NotBlank(message = "L'ID du colis ne peut pas être vide")
    private String colisId;

    @NotBlank(message = "L'ID du produit ne peut pas être vide")
    private String produitId;

    @NotNull(message = "La quantité ne peut pas être nulle")
    @Positive(message = "La quantité doit être un nombre positif")
    private Integer quantite;

    // Le prix et la date d'ajout seront gérés par le serveur
    private Double prix;
    private LocalDate dateAjout;
}
