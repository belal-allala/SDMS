package com.smartlogi.smds.service;

import com.smartlogi.smds.entity.Destinataire;
import com.smartlogi.smds.repository.DestinataireRepository;
import com.smartlogi.smds.dto.DestinataireDTO;
import com.smartlogi.smds.mapper.DestinataireMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DestinataireServiceImpl implements DestinataireService {
    private final DestinataireRepository destinataireRepository;
    private final DestinataireMapper destinataireMapper = DestinataireMapper.INSTANCE;

    public DestinataireServiceImpl(DestinataireRepository destinataireRepository) {
        this.destinataireRepository = destinataireRepository;
    }

    @Override
    public DestinataireDTO save(DestinataireDTO destinataireDTO) {
        Destinataire destinataire = destinataireMapper.toEntity(destinataireDTO);
        destinataire = destinataireRepository.save(destinataire);
        return destinataireMapper.toDTO(destinataire);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DestinataireDTO> findAll() {

        return destinataireRepository.findAll()
                .stream()
                .map(destinataireMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DestinataireDTO> findById(UUID id) {
        return destinataireRepository.findById(id)
                .map(destinataireMapper::toDTO);
    }

    @Override
    public void deleteById(UUID id) {
        destinataireRepository.deleteById(id);
    }
}
// Commit 84 on 2025-10-28 04:48:29
// Commit 88 on 2025-10-26 12:38:47
// Commit 96 on 2025-10-30 15:42:31
// Commit 20 on 2025-10-27 09:03:49
// Commit 46 on 2025-10-26 13:32:01
