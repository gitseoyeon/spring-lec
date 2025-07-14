package org.example.prac.service;

import lombok.RequiredArgsConstructor;
import org.example.prac.model.Movie;
import org.example.prac.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> getAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("영화가 없습니다: " + id));
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(Long id, Movie movie) {
        Movie existMovie = getById(id);
        existMovie.setTitle(movie.getTitle());
        existMovie.setReleaseYear(movie.getReleaseYear());

        return movieRepository.save(existMovie);
    }

    public void delete(Long id) {
        Movie m = getById(id);

        movieRepository.delete(m);
    }

}
