package org.example.prac.service;

import lombok.RequiredArgsConstructor;
import org.example.prac.model.Movie;
import org.example.prac.model.Review;
import org.example.prac.repository.MovieRepository;
import org.example.prac.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public Page<Review> getByMovie(Long movieId, Pageable pageable) {
        return reviewRepository.findByMovieId(movieId, pageable);
    }

    public Review create(Long movieId, Review review) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NoSuchElementException("영화가 없습니다: " + movieId));

        review.setMovie(movie);

        return reviewRepository.save(review);
    }

    public Review update(Long id, Review review) {
        Review existReview = reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("리뷰가 존재하지 않습니다."));

        existReview.setReviewer(review.getReviewer());
        existReview.setRating(review.getRating());
        existReview.setComment(review.getComment());

        return reviewRepository.save(existReview);
    }

    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("리뷰가 존재하지 않습니다."));

        reviewRepository.delete(review);
    }
}
