package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.ColisDTO;
import com.smartlogi.smds.entity.StatutColis;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.ColisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/colis")
@Tag(name = "Colis", description = "API pour la gestion des colis")
public class ColisController {
    private final ColisService colisService;

    public ColisController(ColisService colisService) {
        this.colisService = colisService;
    }

    @Operation(summary = "Créer un nouveau colis", description = "Crée un nouveau colis et retourne ses informations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Colis créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données d'entrée invalides")
    })
    @PostMapping
    public ResponseEntity<ColisDTO> save(@Valid @RequestBody ColisDTO colisDTO) {
        ColisDTO savedColis = colisService.save(colisDTO);
        return new ResponseEntity<>(savedColis, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer tous les colis", description = "Retourne une liste de tous les colis.")
    @GetMapping
    public ResponseEntity<List<ColisDTO>> findAll() {
        List<ColisDTO> colis = colisService.findAll();
        return ResponseEntity.ok(colis);
    }

    @Operation(summary = "Récupérer un colis par son ID", description = "Retourne les informations d'un colis spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colis trouvé"),
            @ApiResponse(responseCode = "404", description = "Colis non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ColisDTO> findById(@Parameter(description = "ID du colis à récupérer") @PathVariable UUID id) {
        ColisDTO colis = colisService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis not found with id: " + id));
        return ResponseEntity.ok(colis);
    }

    @Operation(summary = "Mettre à jour un colis", description = "Met à jour les informations d'un colis existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colis mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Colis non trouvé"),
            @ApiResponse(responseCode = "400", description = "Données d'entrée invalides")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ColisDTO> updateColis(@Parameter(description = "ID du colis à mettre à jour") @PathVariable UUID id, @Valid @RequestBody ColisDTO colisDetails) {
        colisService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis not found with id: " + id));

        colisDetails.setId(id.toString());
        ColisDTO updatedColis = colisService.save(colisDetails);
        return ResponseEntity.ok(updatedColis);
    }

    @Operation(summary = "Supprimer un colis", description = "Supprime un colis par son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Colis supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Colis non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColis(@Parameter(description = "ID du colis à supprimer") @PathVariable UUID id) {
        colisService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis not found with id: " + id));
        colisService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Mettre à jour le statut d'un colis", description = "Met à jour le statut d'un colis et crée une entrée dans l'historique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statut mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Colis non trouvé")
    })
    @PatchMapping("/{id}/statut")
    public ResponseEntity<ColisDTO> updateStatus(
            @Parameter(description = "ID du colis à mettre à jour") @PathVariable UUID id, 
            @Parameter(description = "Nouveau statut du colis") @RequestParam StatutColis newStatus) {
        ColisDTO updatedColis = colisService.updateStatus(id, newStatus);
        return ResponseEntity.ok(updatedColis);
    }

    @Operation(summary = "Assigner un livreur à un colis", description = "Assigne un livreur existant à un colis existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livreur assigné avec succès"),
            @ApiResponse(responseCode = "404", description = "Colis ou livreur non trouvé")
    })
    @PatchMapping("/{id}/assigner-livreur")
    public ResponseEntity<ColisDTO> assignerLivreur(
            @Parameter(description = "ID du colis") @PathVariable UUID id, 
            @Parameter(description = "ID du livreur à assigner") @RequestParam UUID livreurId){
        ColisDTO updatedColis = colisService.assignerLivreur(id, livreurId);
        return ResponseEntity.ok(updatedColis);
    }

    @Operation(summary = "Rechercher et filtrer les colis", description = "Recherche des colis avec des filtres dynamiques et pagination.")
    @GetMapping("/search")
    public ResponseEntity<Page<ColisDTO>> searchColis(
            @Parameter(description = "Filtrer par statut") @RequestParam(required = false) StatutColis statut,
            @Parameter(description = "Filtrer par ID de livreur") @RequestParam(required = false) UUID livreurId,
            @Parameter(description = "Filtrer par ID de zone") @RequestParam(required = false) UUID zoneId,
            @Parameter(description = "Date de début (format YYYY-MM-DD)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @Parameter(description = "Date de fin (format YYYY-MM-DD)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
            Pageable pageable) {

        Page<ColisDTO> colisPage = colisService.searchColis(statut, livreurId, zoneId, dateDebut, dateFin, pageable);
        return ResponseEntity.ok(colisPage);
    }
}
