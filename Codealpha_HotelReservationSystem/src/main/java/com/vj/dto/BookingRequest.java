package com.vj.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record BookingRequest(
        @NotNull Long roomId,
        @NotBlank String guestName,
        @NotBlank @Email String guestEmail,
        @Min(1) int guests,
        @NotNull LocalDate checkIn,
        @NotNull LocalDate checkOut) {}
