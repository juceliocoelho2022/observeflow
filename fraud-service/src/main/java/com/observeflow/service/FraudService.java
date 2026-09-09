package com.observeflow.service;

import com.observeflow.dto.FraudRequest;
import com.observeflow.dto.FraudResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudService {

    private static final BigDecimal FRAUD_LIMIT =
            new BigDecimal("10000.00");

    public FraudResponse analyze(FraudRequest request) {

        boolean approved =
                request.amount().compareTo(FRAUD_LIMIT) <= 0;

        String reason = approved
                ? "Payment approved by fraud analysis"
                : "Payment rejected: amount exceeds fraud limit";

        return new FraudResponse(
                request.paymentId(),
                approved,
                reason
        );
    }
}
