package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.entities.dto.MovieDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private AuthService authService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;


    public MovieDTO findById(Long movieId) {
        authService.authenticated();

        return new MovieDTO(movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
    }

    public Page<MovieDTO> findAllPaged(Pageable pageable, Long genreId) {
        var genre = (genreId == 0) ? null : genreRepository.getOne(genreId).getName();
        return movieRepository.find(pageable, genre).map(MovieDTO::new);
    }
}
