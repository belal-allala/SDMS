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

    @NotNull(message = "Le prix ne peut pas être nul")
    @Positive(message = "Le prix doit être un nombre positif")
    private Double prix;

    @NotNull(message = "La date d'ajout ne peut pas être nulle")
    private LocalDate dateAjout;
}
// Commit 30 on 2025-10-26 16:10:18
// Commit 33 on 2025-10-26 00:05:22
// Commit 92 on 2025-10-27 19:37:17
