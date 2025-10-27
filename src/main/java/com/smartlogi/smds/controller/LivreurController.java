package com.smartlogi.smds.controller;

import com.smartlogi.smds.dto.LivreurDTO;
import com.smartlogi.smds.exception.ResourceNotFoundException;
import com.smartlogi.smds.service.LivreurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/livreurs")
public class LivreurController {
    private final LivreurService livreurService;

    public LivreurController(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @PostMapping
    public ResponseEntity<LivreurDTO> save(@Valid @RequestBody LivreurDTO livreurDTO){
        LivreurDTO dto = livreurService.save(livreurDTO);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LivreurDTO>> findAll(){
        List<LivreurDTO> list = livreurService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivreurDTO> findById(@PathVariable UUID id){
        LivreurDTO dto = livreurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livreur not found with id: " + id));
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivreurDTO> updateLivreur(@PathVariable UUID id, @Valid @RequestBody LivreurDTO livreurDetails) {
        livreurService.findById(id)
                .orElseThrow(()->new  ResourceNotFoundException("Livreur not found with id: " + id));
        livreurDetails.setId(id.toString()); // Convert UUID back to String for DTO
        LivreurDTO updatedLivreur = livreurService.save(livreurDetails);
        return ResponseEntity.ok(updatedLivreur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivreur(@PathVariable UUID id){
        livreurService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livreur not found with id: " + id));
        livreurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
// Commit 25 on 2025-10-27 17:21:54
