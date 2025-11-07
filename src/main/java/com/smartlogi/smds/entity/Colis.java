package com.smartlogi.smds.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import com.smartlogi.smds.entity.StatutColis;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "colis")
@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "La description ne peut pas être vide")
    @Size(max = 255, message = "La description ne peut pas dépasser 255 caractères")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Le poids ne peut pas être nul")
    @Positive(message = "Le poids doit être un nombre positif")
    @Column(name = "poids")
    private Double poids;

    @NotBlank(message = "La priorité ne peut pas être vide")
    @Column(name = "priorite")
    private String priorite;

    @NotBlank(message = "La ville de destination ne peut pas être vide")
    @Column(name = "ville_destination")
    private String villeDestination;

    @NotNull(message = "Le statut ne peut pas être nul")
    @Column(name = "statut")
    @Enumerated(EnumType.STRING)
    private StatutColis statutColis;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @NotNull(message = "Le client expéditeur ne peut pas être nul")
    @ManyToOne
    @JoinColumn(name = "client_expediteur_id")
    private ClientExpediteur clientExpediteur;

    @NotNull(message = "Le destinataire ne peut pas être nul")
    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private Destinataire destinataire;

    @ManyToOne
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

}
