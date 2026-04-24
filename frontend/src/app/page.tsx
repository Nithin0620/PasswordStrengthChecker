"use client";

import { useEffect, useState } from "react";
import { PasswordInput } from "@/components/PasswordInput";
import { StrengthMeter } from "@/components/StrengthMeter";
import { ValidationList } from "@/components/ValidationList";
import { PasswordCheckResponse } from "@/types/password";

export default function Home() {
  const [password, setPassword] = useState("");
  const [debouncedPassword, setDebouncedPassword] = useState("");
  const [result, setResult] = useState<PasswordCheckResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Debounce logic
  useEffect(() => {
    const handler = setTimeout(() => {
      setDebouncedPassword(password);
    }, 300);
    return () => clearTimeout(handler);
  }, [password]);

  // Fetch logic
  useEffect(() => {
    const checkPassword = async () => {
      if (!debouncedPassword) {
        setResult(null);
        return;
      }
      
      setLoading(true);
      setError("");
      
      try {
        const response = await fetch("http://localhost:8080/api/password/check", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ password: debouncedPassword }),
        });

        if (!response.ok) {
          throw new Error("Failed to check password.");
        }

        const data: PasswordCheckResponse = await response.json();
        setResult(data);
      } catch (err) {
        setError("Unable to reach the server. Make sure the backend is running.");
      } finally {
        setLoading(false);
      }
    };

    checkPassword();
  }, [debouncedPassword]);

  return (
    <main className="min-h-screen flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <div className="bg-gray-900/40 backdrop-blur-xl rounded-3xl p-8 shadow-2xl border border-gray-800/60 relative overflow-hidden">
          {/* Decorative gradients */}
          <div className="absolute top-0 left-0 w-full h-1 bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500" />
          <div className="absolute -top-24 -right-24 w-48 h-48 bg-indigo-500/10 rounded-full blur-3xl pointer-events-none" />
          
          <div className="text-center mb-8">
            <h1 className="text-3xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-gray-100 to-gray-400 mb-2">
              Password Strength
            </h1>
            <p className="text-sm text-gray-400">
              Check how secure your password really is.
            </p>
          </div>

          <PasswordInput value={password} onChange={setPassword} />

          {loading && (
            <div className="mt-4 flex justify-center">
              <div className="w-5 h-5 border-2 border-indigo-500 border-t-transparent rounded-full animate-spin" />
            </div>
          )}

          {error && (
            <div className="mt-4 p-3 rounded-lg bg-rose-500/10 border border-rose-500/20 text-rose-400 text-sm text-center">
              {error}
            </div>
          )}

          <StrengthMeter 
            score={result?.score ?? 0} 
            strength={result?.strength ?? "Weak"} 
            visible={!!result && !error} 
          />
          
          <ValidationList 
            checks={result?.checks ?? null} 
            visible={!!result && !error} 
          />

        </div>
      </div>
    </main>
  );
}
