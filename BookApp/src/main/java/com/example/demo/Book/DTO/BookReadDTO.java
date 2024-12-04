package com.example.demo.Book.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookReadDTO {
    private UUID id;
    private String title;
    private String author;
    private int publicationYear;
    private UUID genreId;

    public BookReadDTO(UUID id, String title, String author, int publicationYear, UUID genreId){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genreId = genreId;
    }
}
