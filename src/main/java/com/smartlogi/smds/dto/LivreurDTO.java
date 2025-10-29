package com.smartlogi.smds.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivreurDTO {
    private String id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères")
    private String prenom;

    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    @Size(max = 20, message = "Le numéro de téléphone ne peut pas dépasser 20 caractères")
    private String telephone;

    @NotBlank(message = "Le véhicule ne peut pas être vide")
    @Size(max = 100, message = "Le véhicule ne peut pas dépasser 100 caractères")
    private String vehicule;

    @NotBlank(message = "L'ID de la zone ne peut pas être vide")
    private String zoneId;
}
// Commit 26 on 2025-10-30 00:35:05
// Commit 111 on 2025-10-29 20:51:08
