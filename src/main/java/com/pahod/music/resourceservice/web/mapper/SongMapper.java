package com.pahod.music.resourceservice.web.mapper;

import com.pahod.music.resourceservice.entity.SongEntity;
import com.pahod.music.resourceservice.web.dto.SongDTO;
import com.pahod.music.resourceservice.web.dto.SongResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

  SongDTO modelToDTO(SongEntity resource);

  SongResponse modelToResponse(SongEntity resource);

}
