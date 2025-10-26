package com.smartlogi.smds.mapper;

import com.smartlogi.smds.entity.HistoriqueLivraison;
import com.smartlogi.smds.dto.HistoriqueLivraisonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface HistoriqueLivraisonMapper {
    HistoriqueLivraisonMapper INSTANCE = Mappers.getMapper(HistoriqueLivraisonMapper.class);

    @Mapping(source = "colis.id", target = "colisId")
    HistoriqueLivraisonDTO toDTO(HistoriqueLivraison historiqueLivraison);

    @Mapping(source = "colisId", target = "colis.id")
    HistoriqueLivraison toEntity(HistoriqueLivraisonDTO historiqueLivraisonDTO);
}
// Commit 121 on 2025-10-30 02:47:46
// Commit 43 on 2025-10-26 19:41:41
