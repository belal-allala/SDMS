package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ColisDTO;
import com.smartlogi.smds.entity.StatutColis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColisService {
    ColisDTO save(ColisDTO colisDTO);
    List<ColisDTO> findAll();
    Page<ColisDTO> searchColis(StatutColis statut, UUID livreurId, UUID zoneId, LocalDate dateDebut, LocalDate dateFin, Pageable pageable);
    Optional<ColisDTO> findById(UUID id);
    void deleteById(UUID id);
    ColisDTO updateStatus(UUID id, StatutColis newStatus);
    ColisDTO assignerLivreur(UUID colisId, UUID livreurId);
    Page<ColisDTO> findByClientExpediteurId(UUID clientExpediteurId, Pageable pageable);
}
