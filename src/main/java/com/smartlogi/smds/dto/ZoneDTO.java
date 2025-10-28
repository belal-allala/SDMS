package com.smartlogi.smds.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneDTO {
    private String id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String nom;

    @NotBlank(message = "Le code postal ne peut pas être vide")
    @Size(max = 10, message = "Le code postal ne peut pas dépasser 10 caractères")
    private String codePostal;
}
// Commit 52 on 2025-10-29 17:56:32
// Commit 92 on 2025-10-28 09:29:12
// Commit 94 on 2025-10-26 16:18:10
// Commit 111 on 2025-10-28 03:14:38
