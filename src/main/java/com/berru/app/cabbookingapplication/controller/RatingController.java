package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewRatingRequestDTO;
import com.berru.app.cabbookingapplication.dto.RatingResponseDTO;
import com.berru.app.cabbookingapplication.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<RatingResponseDTO> createRating(@RequestBody @Valid NewRatingRequestDTO newRatingRequestDTO) {
        RatingResponseDTO ratingResponseDTO = ratingService.createRating(newRatingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingResponseDTO);
    }
}