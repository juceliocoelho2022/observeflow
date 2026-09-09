package com.observeflow.service;

import com.observeflow.dto.FraudRequest;
import com.observeflow.dto.FraudResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FraudServiceTest {

    private FraudService fraudService;

    @BeforeEach
    void setUp() {
        fraudService = new FraudService();
    }

    @Test
    void shouldApprovePaymentBelowFraudLimit() {
        FraudRequest request = createRequest("9999.99");

        FraudResponse response = fraudService.analyze(request);

        assertTrue(response.approved());
        assertEquals(
                "Payment approved by fraud analysis",
                response.reason()
        );
    }

    @Test
    void shouldApprovePaymentAtFraudLimit() {
        FraudRequest request = createRequest("10000.00");

        FraudResponse response = fraudService.analyze(request);

        assertTrue(response.approved());
        assertEquals(
                "Payment approved by fraud analysis",
                response.reason()
        );
    }

    @Test
    void shouldRejectPaymentAboveFraudLimit() {
        FraudRequest request = createRequest("10000.01");

        FraudResponse response = fraudService.analyze(request);

        assertFalse(response.approved());
        assertEquals(
                "Payment rejected: amount exceeds fraud limit",
                response.reason()
        );
    }

    @Test
    void shouldRejectHighValuePayment() {
        FraudRequest request = createRequest("15000.00");

        FraudResponse response = fraudService.analyze(request);

        assertFalse(response.approved());
    }

    private FraudRequest createRequest(String amount) {
        return new FraudRequest(
                UUID.randomUUID(),
                UUID.randomUUID(),
                new BigDecimal(amount)
        );
    }
}