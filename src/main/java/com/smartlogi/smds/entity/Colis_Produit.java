package com.smartlogi.smds.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "colis_produit")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Colis_Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Le colis ne peut pas être nul")
    @ManyToOne
    @JoinColumn(name = "colis_id")
    private Colis colis;

    @NotNull(message = "Le produit ne peut pas être nul")
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @NotNull(message = "La quantité ne peut pas être nulle")
    @Positive(message = "La quantité doit être un nombre positif")
    @Column(name = "quantite")
    private Integer quantite;

    @NotNull(message = "Le prix ne peut pas être nul")
    @Positive(message = "Le prix doit être un nombre positif")
    @Column(name = "prix")
    private Double prix;

    @NotNull(message = "La date d'ajout ne peut pas être nulle")
    @Column(name = "date_ajout")
    private LocalDate dateAjout;

}
