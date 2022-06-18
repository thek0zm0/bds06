package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.entities.dto.GenreDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDTO> findAll(Pageable pageable) {
        return genreRepository.findAll(pageable).stream().map(GenreDTO::new).collect(Collectors.toList());
    }
}
