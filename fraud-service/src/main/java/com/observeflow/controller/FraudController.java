package com.observeflow.controller;

import com.observeflow.dto.FraudRequest;
import com.observeflow.dto.FraudResponse;
import com.observeflow.service.FraudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud")
public class FraudController {

    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<FraudResponse> analyze(
            @RequestBody FraudRequest request) {

        return ResponseEntity.ok(
                fraudService.analyze(request)
        );
    }
}
