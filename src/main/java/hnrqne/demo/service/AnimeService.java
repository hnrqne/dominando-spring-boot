package hnrqne.demo.service;

import hnrqne.demo.domain.Anime;
import hnrqne.demo.repository.AnimeHardCodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeHardCodedRepository repository;

    public List<Anime> findAll(String name){
        return repository.findByName(name);
    }

    public Anime findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }

    public Anime save(Anime anime){
        return repository.save(anime);
    }

    public void delete(Long id){
        var anime = findById(id);
        repository.delete(anime);
    }

    public void update(Anime animeToUpdate){
        assertAnimeExists(animeToUpdate);
        repository.update(animeToUpdate);
    }

    private void assertAnimeExists(Anime animeToUpdate) {
        findById(animeToUpdate.getId());
    }
}
