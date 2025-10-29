package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.DestinataireDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.DestinataireService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/destinataires")
public class DestinataireController {
    private final DestinataireService destinataireService;

    public DestinataireController(DestinataireService destinataireService) {
        this.destinataireService = destinataireService;
    }

    @PostMapping
    public ResponseEntity<DestinataireDTO> save(@Valid @RequestBody DestinataireDTO destinataireDTO) {
        DestinataireDTO dto = destinataireService.save(destinataireDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DestinataireDTO>> findAll() {
        List<DestinataireDTO> list = destinataireService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinataireDTO> findById(@PathVariable UUID id) {
        DestinataireDTO dto = destinataireService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destinataire not found with id: " + id));
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinataireDTO> updateDestinataire(@PathVariable UUID id, @Valid @RequestBody DestinataireDTO destinataireDetails) {
        destinataireService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destinataire not found with id: " + id));

        destinataireDetails.setId(id.toString()); // Convert UUID back to String for DTO
        DestinataireDTO updatedDestinataire = destinataireService.save(destinataireDetails);
        return ResponseEntity.ok(updatedDestinataire);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestinataire(@PathVariable UUID id){
        destinataireService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destinataire not found with id: " + id));
        destinataireService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
// Commit 44 on 2025-10-29 00:40:59
// Commit 60 on 2025-10-29 00:21:23
// Commit 115 on 2025-10-29 23:03:21
