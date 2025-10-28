package com.smartlogi.smds.service;

import com.smartlogi.smds.dto.LivreurDTO;
import com.smartlogi.smds.entity.Livreur;
import com.smartlogi.smds.entity.Zone;
import com.smartlogi.smds.mapper.LivreurMapper;
import com.smartlogi.smds.repository.LivreurRepository;
import com.smartlogi.smds.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class LivreurServiceImpl implements LivreurService {
    private final LivreurRepository livreurRepository;
    private final ZoneRepository zoneRepository;
    private final LivreurMapper livreurMapper = LivreurMapper.INSTANCE;

    public LivreurServiceImpl(LivreurRepository livreurRepository, ZoneRepository zoneRepository) {
        this.livreurRepository = livreurRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public LivreurDTO save(LivreurDTO livreurDTO) {
        Livreur livreur = livreurMapper.toEntity(livreurDTO);

        if (livreurDTO.getZoneId() != null) {
            Zone zone = zoneRepository.findById(UUID.fromString(livreurDTO.getZoneId()))
                    .orElseThrow(() -> new RuntimeException("Zone non trouvée"));
            livreur.setZone(zone);
        }

        livreur = livreurRepository.save(livreur);
        return livreurMapper.toDto(livreur);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivreurDTO> findAll() {
        return livreurRepository.findAll()
                .stream()
                .map(livreurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LivreurDTO> findById(UUID id) {
        return livreurRepository.findById(id).map(livreurMapper::toDto);
    }

    @Override
    public void deleteById(UUID id) {
        livreurRepository.deleteById(id);
    }
}
// Commit 106 on 2025-10-26 07:33:01
// Commit 26 on 2025-10-28 19:51:01
