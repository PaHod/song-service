package com.pahod.music.songservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DeletedSongsIDs {
    List<Integer> ids;
}
