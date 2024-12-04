package com.example.demo.Genre.Service;

import com.example.demo.Genre.Model.Genre;
import com.example.demo.Genre.Repository.GenreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;


    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public void deleteById(UUID id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not delete genre with id " + id));

        //bookRepository.deleteAllByGenreId(genre.getId());
        genreRepository.deleteById(id);
    }

    public Genre findById(UUID id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
    }

//    public Genre findByName(String name) {
//        return genreRepository.findByName(name)
//                .orElseThrow(() -> new RuntimeException("Genre not found with name: " + name));
//    }

/*    public Optional<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }*/

    public Genre update(UUID id, Genre updatedGenre) {
        Genre existingGenre = findById(id);

        existingGenre.setName(updatedGenre.getName());
        existingGenre.setDescription(updatedGenre.getDescription());

        return genreRepository.save(existingGenre);
    }
}
