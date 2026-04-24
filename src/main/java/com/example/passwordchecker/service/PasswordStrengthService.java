package com.example.passwordchecker.service;

import com.example.passwordchecker.model.PasswordStrengthResponse;
import com.example.passwordchecker.model.PasswordStrengthResponse.Checks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordStrengthService {

    public PasswordStrengthResponse evaluate(String password) {
        if (password == null || password.isEmpty()) {
            return new PasswordStrengthResponse(0, "Weak", new Checks(false, false, false, false, false, true, true));
        }

        boolean length = password.length() >= 8;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        // no repeated characters (e.g. 'aaa' or '111')
        boolean noRepeatedChars = true;
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                noRepeatedChars = false;
                break;
            }
        }

        // basic common pattern check
        List<String> commonPatterns = List.of("12345", "password", "qwerty", "admin", "12345678");
        boolean noCommonPatterns = true;
        String lowerPass = password.toLowerCase();
        for (String pattern : commonPatterns) {
            if (lowerPass.contains(pattern)) {
                noCommonPatterns = false;
                break;
            }
        }

        int score = 0;
        if (length) score += 20;
        if (hasLower) score += 10;
        if (hasUpper) score += 15;
        if (hasDigit) score += 15;
        if (hasSpecial) score += 20;
        if (noRepeatedChars) score += 10;
        if (noCommonPatterns) score += 10;
        
        // base length bonuses
        if (password.length() >= 12) score += 10;

        score = Math.min(score, 100);

        String strength;
        if (score < 40) strength = "Weak";
        else if (score < 70) strength = "Medium";
        else if (score < 90) strength = "Strong";
        else strength = "Very Strong";

        Checks checks = new Checks(length, hasUpper, hasLower, hasDigit, hasSpecial, noCommonPatterns, noRepeatedChars);
        return new PasswordStrengthResponse(score, strength, checks);
    }
}
