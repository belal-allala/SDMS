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

    @Mapping(source = "destinataireId", target = "destinataire.id")
    @Mapping(source = "clientExpediteurId", target = "clientExpediteur.id")
    @Mapping(source = "livreurId", target = "livreur.id")
    @Mapping(source = "zoneId", target = "zone.id")
    Colis toEntity(ColisDTO colisDTO);
}
// Commit 114 on 2025-10-30 09:29:20
// Commit 55 on 2025-10-27 00:52:16
// Commit 63 on 2025-10-28 04:37:39
// Commit 64 on 2025-10-29 11:16:31
