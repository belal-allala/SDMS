package com.smartlogi.smds.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "destinataire")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Destinataire {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    @Column(name = "nom")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères")
    @Column(name = "prenom")
    private String prenom;

    @NotBlank(message = "L'adresse ne peut pas être vide")
    @Size(max = 255, message = "L'adresse ne peut pas dépasser 255 caractères")
    @Column(name = "adresse")
    private String adresse;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "L'email doit être valide")
    @Size(max = 255, message = "L'email ne peut pas dépasser 255 caractères")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    @Size(max = 20, message = "Le numéro de téléphone ne peut pas dépasser 20 caractères")
    @Column(name = "telephone")
    private String telephone;

}
// Commit 113 on 2025-10-27 00:31:55
// Commit 116 on 2025-10-30 15:52:23
