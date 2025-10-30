package com.berru.app.cabbookingapplication.controller;

import com.berru.app.cabbookingapplication.dto.NewRatingRequestDTO;
import com.berru.app.cabbookingapplication.dto.RatingResponseDTO;
import com.berru.app.cabbookingapplication.dto.UpdateRatingRequestDTO;
import com.berru.app.cabbookingapplication.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
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

    @PutMapping("/{ratingId}")
    public ResponseEntity<RatingResponseDTO> updateRating(@PathVariable Integer ratingId, @RequestBody @Valid UpdateRatingRequestDTO updateRatingRequestDTO) {
        RatingResponseDTO ratingResponseDTO = ratingService.updateRating(ratingId, updateRatingRequestDTO);
        return ResponseEntity.ok(ratingResponseDTO);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<RatingResponseDTO> getRatingById(@PathVariable Integer ratingId) {
        RatingResponseDTO ratingResponseDTO = ratingService.getRatingById(ratingId);
        return ResponseEntity.ok(ratingResponseDTO);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<RatingResponseDTO>> getRatingsByDriverId(@PathVariable Integer driverId) {
        List<RatingResponseDTO> responses = ratingService.getRatingsByDriverId(driverId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<RatingResponseDTO> getRatingByBookingId(@PathVariable Integer bookingId) {
        RatingResponseDTO ratingResponseDTO = ratingService.getRatingByBookingId(bookingId);
        return ResponseEntity.ok(ratingResponseDTO);
    }
}