package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.ProduitDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produits")
@Tag(name = "Produits", description = "API pour la gestion des produits")
public class ProduitController {
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @Operation(summary = "Créer un nouveau produit")
    @PostMapping
    public ResponseEntity<ProduitDTO> save(@Valid @RequestBody ProduitDTO produitDTO) {
        ProduitDTO savedProduit = produitService.save(produitDTO);
        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer tous les produits")
    @GetMapping
    public ResponseEntity<List<ProduitDTO>> findAll() {
        List<ProduitDTO> produits = produitService.findAll();
        return ResponseEntity.ok(produits);
    }

    @Operation(summary = "Récupérer un produit par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> findById(@PathVariable UUID id) {
        ProduitDTO produit = produitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found with id: " + id));
        return ResponseEntity.ok(produit);
    }

    @Operation(summary = "Mettre à jour un produit")
    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable UUID id, @Valid @RequestBody ProduitDTO produitDetails) {
        produitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found with id: " + id));

        produitDetails.setId(id.toString());
        ProduitDTO updatedProduit = produitService.save(produitDetails);
        return ResponseEntity.ok(updatedProduit);
    }

    @Operation(summary = "Supprimer un produit")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable UUID id) {
        produitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found with id: " + id));
        produitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
