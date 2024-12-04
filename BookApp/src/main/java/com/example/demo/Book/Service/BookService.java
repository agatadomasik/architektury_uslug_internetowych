package com.example.demo.Book.Service;

import com.example.demo.Book.Repository.BookRepository;
import com.example.demo.Book.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public BookService(BookRepository bookRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }


    public Book save(Book book) {
        return bookRepository.save(book);
    }


    public void deleteById(UUID id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found");
        }
    }


    public Book update(UUID id, Book updatedBook) {
        Book existingBook = findById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());
        existingBook.setGenreId(updatedBook.getGenreId());
        return bookRepository.save(existingBook);
    }


//    public List<Book> findBooksByGenreName(String genreName) {
//        Genre genre = genreService.findByName(genreName)
//                .orElseThrow(() -> new RuntimeException("Genre not found with name: " + genreName));
//
//        return bookRepository.findByGenreName(genreName);
//    }


    public List<Book> findAllByGenre(UUID id) {
        List<Book> b = bookRepository.findAllByGenreId(id);
        return b;
    }

    public void deleteByGenre(UUID id) {
        bookRepository.deleteAllByGenreId(id);
    }

    public List<UUID> findGenreIds() {
        String url = "http://localhost:8082/api/genres/ids";
        try {
            List<String> genreIdStrings = restTemplate.getForObject(url, List.class);
            return genreIdStrings.stream()
                    .map(UUID::fromString)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error fetching genres: " + e.getMessage());
            return null;
        }
    }

    public void createGenre(String name, String description) {
        String url = "http://localhost:8082/api/genres";

    }
}
