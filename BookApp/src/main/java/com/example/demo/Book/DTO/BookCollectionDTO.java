package com.example.demo.Book.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookCollectionDTO implements Comparable<BookCollectionDTO>{
    private UUID id;
    private String title;
    private String author;
    private int publicationYear;
    private UUID genreId;

    @Override
    public int compareTo(BookCollectionDTO o) {
        return Integer.compare(publicationYear, o.publicationYear);
    }


}
