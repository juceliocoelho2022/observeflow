package com.observeflow.dto;

import java.util.UUID;

public record FraudResponse(
        UUID paymentId,
        boolean approved,
        String reason
) {
}
