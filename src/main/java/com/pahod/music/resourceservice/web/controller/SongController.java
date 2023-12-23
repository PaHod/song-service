package com.pahod.music.resourceservice.web.controller;

import com.pahod.music.resourceservice.entity.SongEntity;
import com.pahod.music.resourceservice.service.SongService;
import com.pahod.music.resourceservice.web.dto.RemovedResourcesIDs;
import com.pahod.music.resourceservice.web.dto.SongDTO;
import com.pahod.music.resourceservice.web.mapper.SongMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.metadata.Metadata;
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
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<?> uploadAudioResource(@RequestBody Metadata metadata) {
        System.out.println(metadata);

//    String validationResult = validateAudioFile(file);
//    if (!validationResult.isEmpty()) {
//      return ResponseEntity.badRequest().body(validationResult);
//    }
//
//    SongEntity resource = songService.uploadAudioResource(file);
//    SongResponse songResponse = songMapper.modelToResponse(resource);
        return ResponseEntity.ok().build();
    }

    private String validateAudioFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "Error: File is empty.";
        }
        System.out.println("content type: " + file.getContentType());
        if (!"audio/mpeg".equals(file.getContentType())) {
            return "File has wrong content type. Should be audio/mpeg";
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            return "Error: Empty file name.";
        }
        log.debug("Validated audio file with name: {}", fileName);

        return "";
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getResource(@PathVariable("id") int id) {
        log.debug("Get resource ID: {}", id);
        SongEntity resource = songService.getResource(id);
        return ResponseEntity.ok(songMapper.modelToDTO(resource));
    }

    @DeleteMapping("/resources")
    public ResponseEntity<RemovedResourcesIDs> deleteResources(@RequestParam String idsParam) {
        log.debug("Delete resource ID: {}", idsParam);

        if (idsParam.length() >= 200)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID length exceeds limit");

        List<Integer> idsToDelete = Arrays.stream(idsParam.split(",")).map(Integer::parseInt).toList();

        List<Integer> idsOfRemoved = songService.deleteResources(idsToDelete);
        return ResponseEntity.ok(new RemovedResourcesIDs(idsOfRemoved));
    }
}
