package com.example.demo.Genre.Repository;

import com.example.demo.Genre.Model.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
    Optional<Genre> findByName(String name);

    @Modifying
    @Transactional
    @Query("delete from Genre g where g.name = ?1")
    void deleteByName(String name);
}
