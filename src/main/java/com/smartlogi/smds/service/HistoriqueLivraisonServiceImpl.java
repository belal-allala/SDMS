package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.HistoriqueLivraisonDTO;
import com.smartlogi.smds.entity.Colis;
import com.smartlogi.smds.entity.HistoriqueLivraison;
import com.smartlogi.smds.mapper.HistoriqueLivraisonMapper;
import com.smartlogi.smds.repository.ColisRepository;
import com.smartlogi.smds.repository.HistoriqueLivraisonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class HistoriqueLivraisonServiceImpl implements HistoriqueLivraisonService {

    private final HistoriqueLivraisonRepository historiqueLivraisonRepository;
    private final ColisRepository colisRepository;
    private final HistoriqueLivraisonMapper historiqueLivraisonMapper = HistoriqueLivraisonMapper.INSTANCE;

    public HistoriqueLivraisonServiceImpl(HistoriqueLivraisonRepository historiqueLivraisonRepository, ColisRepository colisRepository) {
        this.historiqueLivraisonRepository = historiqueLivraisonRepository;
        this.colisRepository = colisRepository;
    }

    @Override
    public HistoriqueLivraisonDTO save(HistoriqueLivraisonDTO historiqueLivraisonDTO) {
        HistoriqueLivraison historiqueLivraison = historiqueLivraisonMapper.toEntity(historiqueLivraisonDTO);

        if (historiqueLivraisonDTO.getColisId() != null) {
            Colis colis = colisRepository.findById(UUID.fromString(historiqueLivraisonDTO.getColisId()))
                    .orElseThrow(() -> new RuntimeException("Colis non trouvé"));
            historiqueLivraison.setColis(colis);
        }

        historiqueLivraison = historiqueLivraisonRepository.save(historiqueLivraison);
        return historiqueLivraisonMapper.toDTO(historiqueLivraison);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoriqueLivraisonDTO> findAll() {
        return historiqueLivraisonRepository.findAll()
                .stream()
                .map(historiqueLivraisonMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistoriqueLivraisonDTO> findById(UUID id) {
        return historiqueLivraisonRepository.findById(id).map(historiqueLivraisonMapper::toDTO);
    }

    @Override
    public void deleteById(UUID id) {
        historiqueLivraisonRepository.deleteById(id);
    }
}
// Commit 82 on 2025-10-29 20:14:14
// Commit 53 on 2025-10-27 21:18:36
