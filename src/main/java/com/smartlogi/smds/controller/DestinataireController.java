package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.DestinataireDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.DestinataireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/destinataires")
@Tag(name = "Destinataires", description = "API pour la gestion des destinataires")
public class DestinataireController {
    private final DestinataireService destinataireService;

    public DestinataireController(DestinataireService destinataireService) {
        this.destinataireService = destinataireService;
    }

    @Operation(summary = "Créer un nouveau destinataire")
    @PostMapping
    public ResponseEntity<DestinataireDTO> save(@Valid @RequestBody DestinataireDTO destinataireDTO) {
        DestinataireDTO dto = destinataireService.save(destinataireDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer tous les destinataires")
    @GetMapping
    public ResponseEntity<List<DestinataireDTO>> findAll() {
        List<DestinataireDTO> list = destinataireService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Récupérer un destinataire par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<DestinataireDTO> findById(@PathVariable UUID id) {
        DestinataireDTO dto = destinataireService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destinataire not found with id: " + id));
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "Mettre à jour un destinataire")
    @PutMapping("/{id}")
    public ResponseEntity<DestinataireDTO> updateDestinataire(@PathVariable UUID id, @Valid @RequestBody DestinataireDTO destinataireDetails) {
        destinataireService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destinataire not found with id: " + id));

        destinataireDetails.setId(id.toString());
        DestinataireDTO updatedDestinataire = destinataireService.save(destinataireDetails);
        return ResponseEntity.ok(updatedDestinataire);
    }

    @Operation(summary = "Supprimer un destinataire")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestinataire(@PathVariable UUID id){
        destinataireService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destinataire not found with id: " + id));
        destinataireService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
