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

