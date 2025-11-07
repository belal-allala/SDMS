package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.Colis_ProduitDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.Colis_ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/colis-produits")
@Tag(name = "Colis-Produits", description = "API pour la gestion du contenu des colis")
public class ColisProduitController {

    private final Colis_ProduitService colisProduitService;

    public ColisProduitController(Colis_ProduitService colisProduitService) {
        this.colisProduitService = colisProduitService;
    }

    @Operation(summary = "Ajouter un produit à un colis")
    @PostMapping
    public ResponseEntity<Colis_ProduitDTO> addProduitToColis(@Valid @RequestBody Colis_ProduitDTO colisProduitDTO) {
        Colis_ProduitDTO savedRelation = colisProduitService.save(colisProduitDTO);
        return new ResponseEntity<>(savedRelation, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer toutes les associations colis-produit")
    @GetMapping
    public ResponseEntity<List<Colis_ProduitDTO>> findAll() {
        List<Colis_ProduitDTO> relations = colisProduitService.findAll();
        return ResponseEntity.ok(relations);
    }

    @Operation(summary = "Récupérer une association par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<Colis_ProduitDTO> findById(@PathVariable UUID id) {
        Colis_ProduitDTO relation = colisProduitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relation Colis-Produit non trouvée avec l'ID: " + id));
        return ResponseEntity.ok(relation);
    }

    @Operation(summary = "Mettre à jour une association (ex: changer la quantité)")
    @PutMapping("/{id}")
    public ResponseEntity<Colis_ProduitDTO> updateRelation(@PathVariable UUID id, @Valid @RequestBody Colis_ProduitDTO details) {
        colisProduitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relation Colis-Produit non trouvée avec l'ID: " + id));
        details.setId(id.toString());
        Colis_ProduitDTO updatedRelation = colisProduitService.save(details);
        return ResponseEntity.ok(updatedRelation);
    }

    @Operation(summary = "Supprimer un produit d'un colis")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduitFromColis(@PathVariable UUID id) {
        colisProduitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relation Colis-Produit non trouvée avec l'ID: " + id));
        colisProduitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
