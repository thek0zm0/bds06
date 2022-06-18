package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT DISTINCT movie " +
            "FROM Movie movie " +
            "INNER JOIN movie.genre gen " +
            "WHERE (:genre IS null) OR (LOWER(gen.name) LIKE LOWER(CONCAT('%', :genre, '%'))) " +
            //"WHERE (LOWER(gen.name) LIKE LOWER(CONCAT('%', :genre, '%'))) " +
            "ORDER BY movie.title ASC")
    Page<Movie> find(Pageable pageable, String genre);
}
