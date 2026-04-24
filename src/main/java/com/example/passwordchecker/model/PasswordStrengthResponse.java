package com.example.passwordchecker.model;

/**
 * API response payload returned by POST /api/password/check
 */
public record PasswordStrengthResponse(int score, String strength, Checks checks) {

    public record Checks(
            boolean length,
            boolean uppercase,
            boolean lowercase,
            boolean digit,
            boolean specialChar,
            boolean noCommonPatterns,
            boolean noRepeatedChars
    ) {}
}
