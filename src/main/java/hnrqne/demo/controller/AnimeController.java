package hnrqne.demo.controller;

import hnrqne.demo.domain.Anime;
import hnrqne.demo.mapper.AnimeMapper;
import hnrqne.demo.request.AnimePostRequest;
import hnrqne.demo.request.AnimePutRequest;
import hnrqne.demo.response.AnimeGetResponse;
import hnrqne.demo.response.AnimePostResponse;
import hnrqne.demo.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"v1/animes", "v1/animes/"})
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeMapper mapper;

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> list(@RequestParam(required = false) String name) {
        log.info("Request received to list all animes, param name '{}'", name);

        var animes = animeService.findAll(name);

        var animeGetResponses = mapper.toAnimeGetResponses(animes);

        return ResponseEntity.ok(animeGetResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.info("Request received find anime by id '{}'", id);

        Anime animeFound = animeService.findById(id);

        var response = mapper.toAnimeGetResponse(animeFound);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        log.info("Request received save anime '{}'", request);

        var anime = mapper.toAnime(request);

        anime = animeService.save(anime);

        var response = mapper.toAnimePostResponse(anime);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Request received to delete the anime by id '{}'", id);

        animeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        log.info("Request received to update the anime '{}'", request);

        var animeUpdated = mapper.toAnime(request);

        animeService.update(animeUpdated);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
