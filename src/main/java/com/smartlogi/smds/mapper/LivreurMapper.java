package com.smartlogi.smds.mapper;

import com.smartlogi.smds.entity.Livreur;
import com.smartlogi.smds.dto.LivreurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LivreurMapper {

    LivreurMapper INSTANCE = Mappers.getMapper(LivreurMapper.class);

    @Mapping(source = "zone.id", target = "zoneId")
    LivreurDTO toDto(Livreur livreur);

    @Mapping(source = "zoneId", target = "zone.id")
    Livreur toEntity(LivreurDTO livreurDTO);
}

