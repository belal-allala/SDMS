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
// Commit 7 on 2025-10-30 22:30:48
// Commit 61 on 2025-10-27 17:36:46
// Commit 93 on 2025-10-26 18:30:16
// Commit 23 on 2025-10-28 08:31:32
// Commit 48 on 2025-10-30 11:40:14
// Commit 62 on 2025-10-28 15:47:15
