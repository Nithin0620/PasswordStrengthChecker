import { PasswordChecks } from "@/types/password";

interface ValidationListProps {
  checks: PasswordChecks | null;
  visible: boolean;
}

export function ValidationList({ checks, visible }: ValidationListProps) {
  if (!visible || !checks) return null;

  const rules = [
    { key: "length", label: "At least 8 characters long" },
    { key: "uppercase", label: "Contains uppercase letter" },
    { key: "lowercase", label: "Contains lowercase letter" },
    { key: "digit", label: "Contains a number" },
    { key: "specialChar", label: "Contains a special character" },
    { key: "noCommonPatterns", label: "No common dictionary words" },
    { key: "noRepeatedChars", label: "No repeated characters (e.g., 'aaa')" },
  ];

  return (
    <div className="mt-6 p-4 rounded-xl bg-gray-900/50 border border-gray-800/50 backdrop-blur-sm animate-in fade-in zoom-in-95 duration-300">
      <h3 className="text-sm font-semibold text-gray-300 tracking-wide uppercase mb-4">
        Password Requirements
      </h3>
      <ul className="space-y-3">
        {rules.map((rule) => {
          const isMet = checks[rule.key as keyof PasswordChecks];
          return (
            <li key={rule.key} className="flex items-center space-x-3 text-sm transition-colors duration-300">
              <div
                className={`flex-shrink-0 w-5 h-5 rounded-full flex items-center justify-center transition-all duration-300 ${
                  isMet
                    ? "bg-emerald-500/20 text-emerald-500 shadow-[0_0_10px_rgba(52,211,153,0.2)]"
                    : "bg-gray-800 text-gray-500"
                }`}
              >
                {isMet ? (
                  <svg className="w-3.5 h-3.5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={3} d="M5 13l4 4L19 7" />
                  </svg>
                ) : (
                  <div className="w-1.5 h-1.5 rounded-full bg-gray-600" />
                )}
              </div>
              <span className={isMet ? "text-gray-200" : "text-gray-500"}>
                {rule.label}
              </span>
            </li>
          );
        })}
      </ul>
    </div>
  );
}
