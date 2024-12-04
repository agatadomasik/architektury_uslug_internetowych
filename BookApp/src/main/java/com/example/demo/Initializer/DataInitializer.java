package com.example.demo.Initializer;

import com.example.demo.Book.Service.BookService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.Book.Model.Book;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer {

    private final BookService bookService;
    private final RestTemplate restTemplate;

    @Autowired
    public DataInitializer(BookService bookService, RestTemplate restTemplate) {
        this.bookService = bookService;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void initialize() {
        List<UUID> genreIds = bookService.findGenreIds();

        Book book1 = Book.builder()
                .title("The Metamorphosis")
                .author("Franz Kafka")
                .publicationYear(1915)
                .genreId(genreIds.get(0))
                .build();

        Book book2 = Book.builder()
                .title("The Stranger")
                .author("Albert Camus")
                .publicationYear(1942)
                .genreId(genreIds.get(0))
                .build();

        Book book3 = Book.builder()
                .title("The Picture of Dorian Gray")
                .author("Oscar Wilde")
                .publicationYear(1890)
                .genreId(genreIds.get(0))
                .build();

        Book book4 = Book.builder()
                .title("The Unbearable Lightness of Being")
                .author("Milan Kundera")
                .publicationYear(1984)
                .genreId(genreIds.get(1))
                .build();

        Book book5 = Book.builder()
                .title("My Year of Rest and Relaxation")
                .author("Ottessa Moshfegh")
                .publicationYear(2018)
                .genreId(genreIds.get(1))
                .build();

        Book book6 = Book.builder()
                .title("The Bell Jar")
                .author("Sylvia Plath")
                .publicationYear(1963)
                .genreId(genreIds.get(1))
                .build();

        Book book7 = Book.builder()
                .title("The Handmaid’s Tale")
                .author("Margaret Atwood")
                .publicationYear(1985)
                .genreId(genreIds.get(1))
                .build();

        Book book8 = Book.builder()
                .title("Man’s Search for Meaning")
                .author("Viktor E. Frankl")
                .publicationYear(1946)
                .genreId(genreIds.get(2))
                .build();

        Book book9 = Book.builder()
                .title("Escape from Freedom")
                .author("Erich Fromm")
                .publicationYear(1941)
                .genreId(genreIds.get(2))
                .build();

        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);
        bookService.save(book4);
        bookService.save(book5);
        bookService.save(book6);
        bookService.save(book7);
        bookService.save(book8);
        bookService.save(book9);
    }

//    private List<UUID> fetchGenreIdsFromGenreApp() {
//        String genreAppUrl = "http://localhost:8082/api/genres/ids";
//        try {
//            List<String> genreIdStrings = restTemplate.getForObject(genreAppUrl, List.class);
//            return genreIdStrings.stream()
//                    .map(UUID::fromString)
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            System.out.println("Error fetching genres: " + e.getMessage());
//            return null;
//        }
//    }
}
