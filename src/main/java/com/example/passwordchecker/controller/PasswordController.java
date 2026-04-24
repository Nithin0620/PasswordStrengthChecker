package com.example.passwordchecker.controller;

import com.example.passwordchecker.model.PasswordRequest;
import com.example.passwordchecker.model.PasswordStrengthResponse;
import com.example.passwordchecker.service.PasswordStrengthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller exposing the password strength API.
 *
 * Routes:
 *   GET  /                       → health check / welcome message
 *   POST /api/password/check     → password strength evaluation
 */
@RestController
@CrossOrigin(origins = "*")  // allow any front-end / testing tool
public class PasswordController {

    private final PasswordStrengthService service;

    public PasswordController(PasswordStrengthService service) {
        this.service = service;
    }

    /** Home route — confirms the API is alive. */
    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(Map.of(
                "status",  "running",
                "message", "Password Strength Checker API is running. Use POST /api/password/check with JSON payload { \"password\": \"...\" }"
        ));
    }

    /**
     * Evaluate password strength.
     *
     * @param request the password to evaluate (JSON body)
     * @return JSON with score (0-100), strength label, and checks object
     */
    @PostMapping("/api/password/check")
    public ResponseEntity<PasswordStrengthResponse> checkPassword(@RequestBody PasswordRequest request) {
        String password = request != null && request.password() != null ? request.password() : "";
        PasswordStrengthResponse result = service.evaluate(password);
        return ResponseEntity.ok(result);
    }
}
