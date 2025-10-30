package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.ZoneDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.ZoneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {
    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ResponseEntity<ZoneDTO> save(@Valid @RequestBody ZoneDTO zoneDTO) {
        ZoneDTO savedZone = zoneService.save(zoneDTO);
        return new ResponseEntity<>(savedZone, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ZoneDTO>> findAll() {
        List<ZoneDTO> zones = zoneService.findAll();
        return ResponseEntity.ok(zones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> findById(@PathVariable UUID id) {
        ZoneDTO zone = zoneService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
        return ResponseEntity.ok(zone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZoneDTO> updateZone(@PathVariable UUID id, @Valid @RequestBody ZoneDTO zoneDetails) {
        zoneService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));

        zoneDetails.setId(id.toString()); // Convert UUID back to String for DTO
        ZoneDTO updatedZone = zoneService.save(zoneDetails);
        return ResponseEntity.ok(updatedZone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable UUID id) {
        zoneService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
        zoneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
// Commit 5 on 2025-10-29 12:20:22
// Commit 27 on 2025-10-27 18:38:49
// Commit 48 on 2025-10-30 12:21:57
