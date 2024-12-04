package com.example.demo.Book.Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Entity
@Table(name="books")
public class Book implements Comparable<Book>, Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(name="genre_id", nullable = false)
    private UUID genreId;

    @Override
    public int compareTo(Book o) {
        if (title.compareTo(o.title)!=0) return title.compareTo(o.title);
        return Integer.compare(publicationYear, o.publicationYear);
    }
}

