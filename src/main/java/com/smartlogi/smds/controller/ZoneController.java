package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.ZoneDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.ZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/zones")
@Tag(name = "Zones", description = "API pour la gestion des zones de livraison")
public class ZoneController {
    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @Operation(summary = "Créer une nouvelle zone")
    @PostMapping
    public ResponseEntity<ZoneDTO> save(@Valid @RequestBody ZoneDTO zoneDTO) {
        ZoneDTO savedZone = zoneService.save(zoneDTO);
        return new ResponseEntity<>(savedZone, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer toutes les zones")
    @GetMapping
    public ResponseEntity<List<ZoneDTO>> findAll() {
        List<ZoneDTO> zones = zoneService.findAll();
        return ResponseEntity.ok(zones);
    }

    @Operation(summary = "Récupérer une zone par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> findById(@PathVariable UUID id) {
        ZoneDTO zone = zoneService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
        return ResponseEntity.ok(zone);
    }

    @Operation(summary = "Mettre à jour une zone")
    @PutMapping("/{id}")
    public ResponseEntity<ZoneDTO> updateZone(@PathVariable UUID id, @Valid @RequestBody ZoneDTO zoneDetails) {
        zoneService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));

        zoneDetails.setId(id.toString());
        ZoneDTO updatedZone = zoneService.save(zoneDetails);
        return ResponseEntity.ok(updatedZone);
    }

    @Operation(summary = "Supprimer une zone")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable UUID id) {
        zoneService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
        zoneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
