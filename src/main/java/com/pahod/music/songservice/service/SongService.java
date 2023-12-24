package com.pahod.music.songservice.service;

import com.pahod.music.songservice.entity.SongEntity;
import com.pahod.music.songservice.exception.SongNotFoundException;
import com.pahod.music.songservice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public SongEntity createSongData(SongEntity songEntity) {
        return songRepository.save(songEntity);
    }

    public SongEntity getSong(int id) {
        return songRepository
                .findById(id)
                .orElseThrow(() -> new SongNotFoundException("Couldn't find song for Id: " + id));
    }

    public List<Integer> deleteSongs(List<Integer> idsToDelete) {
        log.debug("IDs to be removed: {}", idsToDelete);
        return idsToDelete.stream()
                .filter(songRepository::existsById)
                .peek(songRepository::deleteById)
                .toList();
    }
}
