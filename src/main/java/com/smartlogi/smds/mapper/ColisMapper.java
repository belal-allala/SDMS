package com.smartlogi.smds.mapper;

import com.smartlogi.smds.entity.Colis;
import com.smartlogi.smds.dto.ColisDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ColisMapper {
    ColisMapper INSTANCE = Mappers.getMapper(ColisMapper.class);

    @Mapping(source = "destinataire.id", target = "destinataireId")
    @Mapping(source = "clientExpediteur.id", target = "clientExpediteurId")
    @Mapping(source = "livreur.id", target = "livreurId")
    @Mapping(source = "zone.id", target = "zoneId")
    ColisDTO toDTO(Colis colis);

    @Mapping(target = "destinataire", ignore = true)
    @Mapping(target = "clientExpediteur", ignore = true)
    @Mapping(target = "livreur", ignore = true)
    @Mapping(target = "zone", ignore = true)
    Colis toEntity(ColisDTO colisDTO);
}

