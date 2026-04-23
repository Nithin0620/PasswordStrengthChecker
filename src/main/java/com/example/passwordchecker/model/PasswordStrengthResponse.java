package com.example.passwordchecker.model;

import java.util.List;

/**
 * API response payload returned by GET /api/check
 */
public class PasswordStrengthResponse {

    private int score;           // 0–100
    private String strength;     // "Weak" | "Medium" | "Strong"
    private List<String> suggestions;

    public PasswordStrengthResponse(int score, String strength, List<String> suggestions) {
        this.score = score;
        this.strength = strength;
        this.suggestions = suggestions;
    }

    public int getScore() { return score; }
    public String getStrength() { return strength; }
    public List<String> getSuggestions() { return suggestions; }
}
