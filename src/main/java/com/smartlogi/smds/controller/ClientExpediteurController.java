package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.ClientExpediteurDTO;
import com.smartlogi.smds.dto.ColisDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.ClientExpediteurService;
import com.smartlogi.smds.service.ColisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client-expediteurs")
@Tag(name = "Clients Expéditeurs", description = "API pour la gestion des clients expéditeurs")
public class ClientExpediteurController {
    private final ClientExpediteurService clientExpediteurService;
    private final ColisService colisService;

    public ClientExpediteurController(ClientExpediteurService clientExpediteurService, ColisService colisService) {
        this.clientExpediteurService = clientExpediteurService;
        this.colisService = colisService;
    }

    @Operation(summary = "Créer un nouveau client expéditeur")
    @PostMapping
    public ResponseEntity<ClientExpediteurDTO> save(@Valid @RequestBody ClientExpediteurDTO clientExpediteurDTO) {
        ClientExpediteurDTO savedClient = clientExpediteurService.save(clientExpediteurDTO);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer un client expéditeur par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClientExpediteurDTO> findById(@PathVariable UUID id) {
        ClientExpediteurDTO client = clientExpediteurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClientExpediteur not found with id: " + id));
        return ResponseEntity.ok(client);
    }

    @Operation(summary = "Récupérer tous les clients expéditeurs")
    @GetMapping
    public ResponseEntity<List<ClientExpediteurDTO>> findAll() {
        List<ClientExpediteurDTO> clients = clientExpediteurService.findAll();
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Mettre à jour un client expéditeur")
    @PutMapping("/{id}")
    public ResponseEntity<ClientExpediteurDTO> updateClientExpediteur(@PathVariable UUID id, @Valid @RequestBody ClientExpediteurDTO clientDetails) {
        clientExpediteurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClientExpediteur not found with id: " + id));

        clientDetails.setId(id.toString());
        ClientExpediteurDTO updatedClient = clientExpediteurService.save(clientDetails);
        return ResponseEntity.ok(updatedClient);
    }

    @Operation(summary = "Supprimer un client expéditeur")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientExpediteur(@PathVariable UUID id){
        clientExpediteurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClientExpediteur not found with id: " + id));
        clientExpediteurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Récupérer les colis d'un client expéditeur spécifique")
    @GetMapping("/{id}/colis")
    public ResponseEntity<Page<ColisDTO>> findColisByClient(@PathVariable UUID id, Pageable pageable) {
        clientExpediteurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClientExpediteur not found with id: " + id));

        Page<ColisDTO> colisPage = colisService.findByClientExpediteurId(id, pageable);
        return ResponseEntity.ok(colisPage);
    }
}
