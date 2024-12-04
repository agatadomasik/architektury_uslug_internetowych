package com.example.demo.Book.Repository;

import com.example.demo.Book.Model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
//    Optional<Book> findByTitle(String title);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book b WHERE b.genreId = :id")
    void deleteAllByGenreId(UUID id);

    @Modifying
    @Transactional
    @Query("SELECT b FROM Book b WHERE b.genreId = :id")
    List<Book> findAllByGenreId(UUID id);
}
