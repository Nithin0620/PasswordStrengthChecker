export type PasswordStrength = "Weak" | "Medium" | "Strong" | "Very Strong";

export interface PasswordChecks {
  length: boolean;
  uppercase: boolean;
  lowercase: boolean;
  digit: boolean;
  specialChar: boolean;
  noCommonPatterns: boolean;
  noRepeatedChars: boolean;
}

export interface PasswordCheckResponse {
  score: number;
  strength: PasswordStrength;
  checks: PasswordChecks;
}
