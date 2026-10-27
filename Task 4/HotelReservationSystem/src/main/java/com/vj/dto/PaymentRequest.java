package com.vj.dto;

import com.vj.model.PaymentMethod;
import jakarta.validation.constraints.NotNull;

public record PaymentRequest(@NotNull PaymentMethod method, String cardNumber) {}
