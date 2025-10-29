package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.ClientExpediteurDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.ClientExpediteurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client-expediteurs")
public class ClientExpediteurController {
    private final ClientExpediteurService clientExpediteurService;

    public ClientExpediteurController(ClientExpediteurService clientExpediteurService) {
        this.clientExpediteurService = clientExpediteurService;
    }

    @PostMapping
    public ResponseEntity<ClientExpediteurDTO> save(@Valid @RequestBody ClientExpediteurDTO clientExpediteurDTO) {
        ClientExpediteurDTO savedClient = clientExpediteurService.save(clientExpediteurDTO);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientExpediteurDTO> findById(@PathVariable UUID id) {
        ClientExpediteurDTO client = clientExpediteurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClientExpediteur not found with id: " + id));
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientExpediteurDTO>> findAll() {
        List<ClientExpediteurDTO> clients = clientExpediteurService.findAll();
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientExpediteurDTO> updateClientExpediteur(@PathVariable UUID id, @Valid @RequestBody ClientExpediteurDTO clientDetails) {
        clientExpediteurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClientExpediteur not found with id: " + id));

        clientDetails.setId(id.toString()); // Convert UUID back to String for DTO
        ClientExpediteurDTO updatedClient = clientExpediteurService.save(clientDetails);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientExpediteur(@PathVariable UUID id){
        clientExpediteurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClientExpediteur not found with id: " + id));
        clientExpediteurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
// Commit 22 on 2025-10-29 23:58:30
// Commit 40 on 2025-10-29 08:21:04
// Commit 85 on 2025-10-26 22:26:38
// Commit 34 on 2025-10-30 03:55:53
// Commit 98 on 2025-10-29 06:05:35
