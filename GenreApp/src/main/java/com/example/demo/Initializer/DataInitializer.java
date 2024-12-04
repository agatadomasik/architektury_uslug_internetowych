package com.example.demo.Initializer;
import java.util.Collections;
import java.util.UUID;
import com.example.demo.Genre.DTO.GenreReadDTO;
import com.example.demo.Genre.Model.Genre;
import com.example.demo.Genre.Service.GenreService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataInitializer {

    private final GenreService genreService;
    private RestTemplate restTemplate;


    @Autowired
    public DataInitializer(GenreService genreService, RestTemplate restTemplate) {
        this.genreService = genreService;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void initialize() {
        Genre classics = Genre.builder()
                .name("Classics")
                .description("A classic stands the test of time. The work is usually " +
                        "considered to be a representation of the period in which it was " +
                        "written; and the work merits lasting recognition.")
                .build();

        Genre novels = Genre.builder()
                .name("Novels")
                .description("A novel is a long prose narrative that usually describes " +
                        "fictional characters and events in the form of a sequential story.")
                .build();

        Genre psychology = Genre.builder()
                .name("Psychology")
                .description("Books that involve psychology; " +
                        "the study of mental processes and human behavior.")
                .build();

        genreService.save(classics);
        genreService.save(novels);
        genreService.save(psychology);

//        sendToBooksApi(classics.getId());
//        sendToBooksApi(novels.getId());
//        sendToBooksApi(psychology.getId());
    }
    private void sendToBooksApi(UUID genreId) {
        String bookAppUrl = "http://localhost:8081/books/genres";
        restTemplate.postForObject(bookAppUrl, Collections.singletonList(genreId), Void.class);
    }
}
