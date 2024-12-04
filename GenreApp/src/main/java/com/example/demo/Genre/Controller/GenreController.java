package com.example.demo.Genre.Controller;

import com.example.demo.Genre.DTO.GenreCollectionDTO;
import com.example.demo.Genre.DTO.GenreCreateUpdateDTO;
import com.example.demo.Genre.DTO.GenreReadDTO;
import com.example.demo.Genre.Model.Genre;
import com.example.demo.Genre.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;
    private final RestTemplate restTemplate;

    @Autowired
    public GenreController(GenreService genreService, RestTemplate restTemplate) {
        this.genreService = genreService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<GenreCollectionDTO>> getAllGenres() {
        List<Genre> genres = genreService.findAll();
        List<GenreCollectionDTO> dtoList = genres.stream()
                .map(this::mapToCollectionDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreReadDTO> getGenreById(@PathVariable UUID id) {
        Genre genre = genreService.findById(id);
        return ResponseEntity.ok(mapToReadDTO(genre));
    }

    @GetMapping("/ids")
    public List<UUID> getGenreIds() {
        List<Genre> genres = genreService.findAll();
        return genres.stream()
                .map(Genre::getId)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<GenreReadDTO> createGenre(@RequestBody GenreCreateUpdateDTO genreDTO) {
        Genre genre = mapToEntity(genreDTO);
        Genre savedGenre = genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToReadDTO(savedGenre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreReadDTO> updateGenreById(@PathVariable UUID id, @RequestBody GenreCreateUpdateDTO genreDTO) {
        Genre updatedGenre = genreService.update(id, mapToEntity(genreDTO));
        return ResponseEntity.ok(mapToReadDTO(updatedGenre));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenreById(@PathVariable UUID id) {
        genreService.deleteById(id);
        restTemplate.delete("http://localhost:8081/api/books/genres/" + id);
        return ResponseEntity.noContent().build();
    }

    private Genre mapToEntity(GenreCreateUpdateDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        return genre;
    }

    private GenreReadDTO mapToReadDTO(Genre genre) {
        GenreReadDTO dto = new GenreReadDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setDescription(genre.getDescription());
        return dto;
    }

    private GenreCollectionDTO mapToCollectionDTO(Genre genre) {
        GenreCollectionDTO dto = new GenreCollectionDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setDescription(genre.getDescription());
        return dto;
    }
}

