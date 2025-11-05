package com.berru.app.cabbookingapplication.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateRatingRequestDTO {

    @NotNull(message = "Rating value cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Double ratingValue;

    @Size(max = 500, message = "Feedback must be at most 500 characters")
    private String feedback;

    private Double tipAmount;
}
