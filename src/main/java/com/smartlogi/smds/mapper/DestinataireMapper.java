package com.smartlogi.smds.mapper;

import com.smartlogi.smds.entity.Destinataire;
import com.smartlogi.smds.dto.DestinataireDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DestinataireMapper {

    DestinataireMapper INSTANCE = Mappers.getMapper(DestinataireMapper.class);

    DestinataireDTO toDTO(Destinataire destinataire);

    Destinataire toEntity(DestinataireDTO destinataireDTO);
}
// Commit 2 on 2025-10-29 22:22:34
