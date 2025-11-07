package com.smartlogi.smds.mapper;

import com.smartlogi.smds.dto.ZoneDTO;
import com.smartlogi.smds.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ZoneMapper {

    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);
    Zone toEntity(ZoneDTO zoneDTO);
    ZoneDTO toDTO(Zone zone);
}

