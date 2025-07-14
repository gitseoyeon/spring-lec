package org.example.prac.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.MovieDto;
import org.example.prac.model.Movie;
import org.example.prac.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public Page<Movie> list(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return movieService.getAll(pageable);

    }

    @GetMapping("/{id}")
    public Movie get(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @PostMapping
    public Movie create(@Valid  @RequestBody MovieDto movieDto) {
        Movie movie = new Movie();

        movie.setTitle(movieDto.getTitle());
        movie.setReleaseYear(movieDto.getReleaseYear());

        return movieService.create(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @Valid @RequestBody MovieDto movieDto) {
        Movie movie = new Movie();

        movie.setTitle(movieDto.getTitle());
        movie.setReleaseYear(movieDto.getReleaseYear());

        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        movieService.delete(id);

        return ResponseEntity.ok("삭제 성공");
    }
}
