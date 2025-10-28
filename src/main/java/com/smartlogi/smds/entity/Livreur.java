package com.smartlogi.smds.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "livreur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livreur {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @NotNull(message = "La zone assignée ne peut pas être nulle")
    @ManyToOne
    @JoinColumn(name = "zone_assignee_id")
    private Zone zone;
}
// Commit 4 on 2025-10-26 10:12:59
// Commit 11 on 2025-10-26 01:51:47
// Commit 12 on 2025-10-28 16:06:29
