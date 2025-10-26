package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.ProduitDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ResponseEntity<ProduitDTO> save(@Valid @RequestBody ProduitDTO produitDTO) {
        ProduitDTO savedProduit = produitService.save(produitDTO);
        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> findAll() {
        List<ProduitDTO> produits = produitService.findAll();
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> findById(@PathVariable UUID id) {
        ProduitDTO produit = produitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found with id: " + id));
        return ResponseEntity.ok(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> updateProduit(@PathVariable UUID id, @Valid @RequestBody ProduitDTO produitDetails) {
        produitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found with id: " + id));

        produitDetails.setId(id.toString()); // Convert UUID back to String for DTO
        ProduitDTO updatedProduit = produitService.save(produitDetails);
        return ResponseEntity.ok(updatedProduit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable UUID id) {
        produitService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found with id: " + id));
        produitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
// Commit 63 on 2025-10-29 06:54:48
// Commit 37 on 2025-10-26 02:49:58
