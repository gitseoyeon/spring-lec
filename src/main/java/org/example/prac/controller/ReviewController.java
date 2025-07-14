package org.example.prac.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.ReviewDto;
import org.example.prac.model.Review;
import org.example.prac.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/api/movies/{movieId}/reviews")
    public Page<Review> listByMovie(@PathVariable Long movieId,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return reviewService.getByMovie(movieId, pageable);
    }
    @PostMapping("/api/movies/{movieId}/reviews")
    public Review add(@PathVariable Long movieId, @Valid @RequestBody ReviewDto reviewDto) {
        Review review = new Review();
        review.setReviewer(reviewDto.getReviewer());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        return reviewService.create(movieId, review);
    }

    @PutMapping("/api/reviews/{id}")
    public Review update(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        Review review = new Review();

        review.setReviewer(reviewDto.getReviewer());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        return reviewService.update(id, review);
    }

    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        reviewService.delete(id);

        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }

}
