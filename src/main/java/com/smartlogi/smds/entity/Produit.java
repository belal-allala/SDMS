package com.smartlogi.smds.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "produit")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    @Column(name = "nom")
    private String nom;

    @NotBlank(message = "La catégorie ne peut pas être vide")
    @Size(max = 100, message = "La catégorie ne peut pas dépasser 100 caractères")
    @Column(name = "categorie")
    private String categorie;

    @NotNull(message = "Le poids ne peut pas être nul")
    @Positive(message = "Le poids doit être un nombre positif")
    @Column(name = "poids")
    private Double poids;

    @NotNull(message = "Le prix ne peut pas être nul")
    @Positive(message = "Le prix doit être un nombre positif")
    @Column(name = "prix")
    private Double prix;
}
// Commit 34 on 2025-10-27 20:21:02
// Commit 45 on 2025-10-27 09:34:39
// Commit 6 on 2025-10-29 05:38:11
// Commit 7 on 2025-10-28 04:40:56
