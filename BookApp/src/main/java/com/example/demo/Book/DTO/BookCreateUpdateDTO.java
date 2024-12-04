package com.example.demo.Book.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateUpdateDTO {
    private String title;
    private String author;
    private int publicationYear;
    private String genreId;

    public BookCreateUpdateDTO(String title, String author, int publicationYear, String genreId){
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genreId = genreId;
    }
}
