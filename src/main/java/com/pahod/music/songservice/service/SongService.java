package com.pahod.music.songservice.service;

import com.pahod.music.songservice.entity.SongEntity;
import com.pahod.music.songservice.exception.FileParsingException;
import com.pahod.music.songservice.exception.SongNotFoundException;
import com.pahod.music.songservice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    private static Metadata parseMetadata(MultipartFile audioFile) {
        Mp3Parser mp3Parser = new Mp3Parser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();

        try (InputStream inputStream = audioFile.getInputStream()) {
            mp3Parser.parse(inputStream, handler, metadata, parseContext);

            LyricsHandler lyrics = new LyricsHandler(inputStream, handler);
            while (lyrics.hasLyrics()) {
                System.out.println(lyrics);
            }
        } catch (IOException | SAXException | TikaException e) {
            throw new FileParsingException("Failed to parse metadata.");
        }

        return metadata;
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
