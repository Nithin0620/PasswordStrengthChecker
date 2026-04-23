package com.example.passwordchecker.service;

import com.example.passwordchecker.model.PasswordStrengthResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Core business logic — ported directly from the original Swing evaluate() method.
 *
 * Scoring:
 *   Length < 6          → 10 pts base
 *   Length 6–10         → 25 pts base
 *   Length > 10         → 50 pts base
 *   Has lowercase       → +10
 *   Has uppercase       → +10
 *   Has digit           → +15
 *   Has special char    → +15
 *   Max capped at 100
 *
 * Thresholds:
 *   ≤ 40  → Weak
 *   41–70 → Medium
 *   > 70  → Strong
 */
@Service
public class PasswordStrengthService {

    public PasswordStrengthResponse evaluate(String password) {
        if (password == null || password.isEmpty()) {
            return new PasswordStrengthResponse(0, "Weak",
                    List.of("Password cannot be empty."));
        }

        int score = password.length() < 6 ? 10
                  : password.length() <= 10 ? 25
                  : 50;

        boolean hasLower   = false;
        boolean hasUpper   = false;
        boolean hasDigit   = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c))      hasLower   = true;
            else if (Character.isUpperCase(c)) hasUpper   = true;
            else if (Character.isDigit(c))     hasDigit   = true;
            else                               hasSpecial = true;
        }

        List<String> suggestions = new ArrayList<>();

        if (hasLower)   score += 10; else suggestions.add("Add lowercase letters.");
        if (hasUpper)   score += 10; else suggestions.add("Add uppercase letters.");
        if (hasDigit)   score += 15; else suggestions.add("Add numbers.");
        if (hasSpecial) score += 15; else suggestions.add("Add special characters (e.g. !@#$).");

        score = Math.min(score, 100);

        if (score > 70) {
            suggestions.add("Great password!");
        }

        String strength = score <= 40 ? "Weak"
                        : score <= 70 ? "Medium"
                        : "Strong";

        return new PasswordStrengthResponse(score, strength, suggestions);
    }
}
