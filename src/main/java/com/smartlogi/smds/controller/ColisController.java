package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.ColisDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.ColisService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/colis")
public class ColisController {
    private final ColisService colisService;

    public ColisController(ColisService colisService) {
        this.colisService = colisService;
    }

    @PostMapping
    public ResponseEntity<ColisDTO> save(@Valid @RequestBody ColisDTO colisDTO) {
        ColisDTO savedColis = colisService.save(colisDTO);
        return new ResponseEntity<>(savedColis, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ColisDTO>> findAll() {
        List<ColisDTO> colis = colisService.findAll();
        return ResponseEntity.ok(colis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColisDTO> findById(@PathVariable UUID id) {
        ColisDTO colis = colisService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis not found with id: " + id));
        return ResponseEntity.ok(colis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColisDTO> updateColis(@PathVariable UUID id, @Valid @RequestBody ColisDTO colisDetails) {
        colisService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis not found with id: " + id));

        colisDetails.setId(id.toString());
        ColisDTO updatedColis = colisService.save(colisDetails);
        return ResponseEntity.ok(updatedColis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColis(@PathVariable UUID id) {
        colisService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis not found with id: " + id));
        colisService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
// Commit 1 on 2025-10-30 23:01:50
// Commit 72 on 2025-10-28 20:16:09
// Commit 78 on 2025-10-30 19:19:27
// Commit 2 on 2025-10-27 08:04:45
// Commit 15 on 2025-10-30 14:16:52
