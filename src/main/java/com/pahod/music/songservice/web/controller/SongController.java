package com.pahod.music.songservice.web.controller;

import com.pahod.music.songservice.entity.SongEntity;
import com.pahod.music.songservice.service.SongService;
import com.pahod.music.songservice.web.dto.DeletedSongsIDs;
import com.pahod.music.songservice.web.dto.MetadataDTO;
import com.pahod.music.songservice.web.dto.SongResponseDTO;
import com.pahod.music.songservice.web.mapper.SongMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final SongMapper songMapper;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createSongData(@RequestBody MetadataDTO metadataDTO) {
        SongEntity savedSong = songService.createSongData(songMapper.metadataToModel(metadataDTO));
        return ResponseEntity.ok(songMapper.modelToSongIdDTO(savedSong));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponseDTO> getSong(@PathVariable("id") int id) {
        log.debug("Get song ID: {}", id);
        SongEntity song = songService.getSong(id);
        return ResponseEntity.ok(songMapper.modelToDTO(song));
    }

    @DeleteMapping("/songs")
    public ResponseEntity<DeletedSongsIDs> deleteSongs(@RequestParam String idsParam) {
        log.debug("Delete songs IDs: {}", idsParam);

        if (idsParam.length() >= 200)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID length exceeds limit");

        List<Integer> idsToDelete = Arrays.stream(idsParam.split(",")).map(Integer::parseInt).toList();

        List<Integer> idsOfRemoved = songService.deleteSongs(idsToDelete);
        return ResponseEntity.ok(new DeletedSongsIDs(idsOfRemoved));
    }
}
