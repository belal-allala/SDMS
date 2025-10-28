package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.ColisDTO;
import com.smartlogi.smds.entity.*;
import com.smartlogi.smds.mapper.ColisMapper;
import com.smartlogi.smds.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ColisServiceImpl implements ColisService {
    private final ColisRepository colisRepository;
    private final ClientExpediteurRepository clientExpediteurRepository;
    private final DestinataireRepository destinataireRepository;
    private final LivreurRepository livreurRepository;
    private final ZoneRepository zoneRepository;
    private final ColisMapper colisMapper = ColisMapper.INSTANCE;

    public ColisServiceImpl(ColisRepository colisRepository, ClientExpediteurRepository clientExpediteurRepository, DestinataireRepository destinataireRepository, LivreurRepository livreurRepository, ZoneRepository zoneRepository) {
        this.colisRepository = colisRepository;
        this.clientExpediteurRepository = clientExpediteurRepository;
        this.destinataireRepository = destinataireRepository;
        this.livreurRepository = livreurRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public ColisDTO save(ColisDTO colisDTO) {
        Colis colis = colisMapper.toEntity(colisDTO);

        if (colisDTO.getClientExpediteurId() != null) {
            ClientExpediteur clientExpediteur = clientExpediteurRepository.findById(UUID.fromString(colisDTO.getClientExpediteurId()))
                    .orElseThrow(() -> new RuntimeException("ClientExpediteur non trouvé"));
            colis.setClientExpediteur(clientExpediteur);
        }

        if (colisDTO.getDestinataireId() != null) {
            Destinataire destinataire = destinataireRepository.findById(UUID.fromString(colisDTO.getDestinataireId()))
                    .orElseThrow(() -> new RuntimeException("Destinataire non trouvé"));
            colis.setDestinataire(destinataire);
        }

        if (colisDTO.getLivreurId() != null) {
            Livreur livreur = livreurRepository.findById(UUID.fromString(colisDTO.getLivreurId()))
                    .orElseThrow(() -> new RuntimeException("Livreur non trouvé"));
            colis.setLivreur(livreur);
        }

        if (colisDTO.getZoneId() != null) {
            Zone zone = zoneRepository.findById(UUID.fromString(colisDTO.getZoneId()))
                    .orElseThrow(() -> new RuntimeException("Zone non trouvée"));
            colis.setZone(zone);
        }

        colis = colisRepository.save(colis);
        return colisMapper.toDTO(colis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ColisDTO> findAll() {
        return colisRepository.findAll()
                .stream()
                .map(colisMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ColisDTO> findById(UUID id) {
        return colisRepository.findById(id).map(colisMapper::toDTO);
    }

    @Override
    public void deleteById(UUID id) {
        colisRepository.deleteById(id);
    }
}
// Commit 53 on 2025-10-29 06:55:07
// Commit 9 on 2025-10-27 15:21:21
// Commit 36 on 2025-10-26 06:06:27
// Commit 51 on 2025-10-30 16:02:22
// Commit 76 on 2025-10-28 14:57:24
