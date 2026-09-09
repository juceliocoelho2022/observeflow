package com.observeflow.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record FraudRequest(
        UUID paymentId,
        UUID userId,
        BigDecimal amount
) {
}
