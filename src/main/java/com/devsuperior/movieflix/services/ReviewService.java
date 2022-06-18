package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.dto.ReviewDTO;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private MovieRepository movieRepository;

    public ReviewDTO insert(ReviewDTO dto) {
        var user = authService.authenticated();
        var movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found."));

        var review = new Review();
        review.setText(dto.getText());
        review.setUser(user);
        review.setMovie(movie);

        repository.save(review);

        return new ReviewDTO(review);
    }
}
