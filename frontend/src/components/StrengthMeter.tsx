import { PasswordStrength } from "@/types/password";

interface StrengthMeterProps {
  score: number;
  strength: PasswordStrength;
  visible: boolean;
}

export function StrengthMeter({ score, strength, visible }: StrengthMeterProps) {
  if (!visible) return null;

  const getColor = () => {
    switch (strength) {
      case "Weak":
        return "bg-rose-500 shadow-[0_0_10px_rgba(244,63,94,0.5)]";
      case "Medium":
        return "bg-amber-400 shadow-[0_0_10px_rgba(251,191,36,0.5)]";
      case "Strong":
        return "bg-emerald-400 shadow-[0_0_10px_rgba(52,211,153,0.5)]";
      case "Very Strong":
        return "bg-indigo-500 shadow-[0_0_10px_rgba(99,102,241,0.6)]";
      default:
        return "bg-gray-700";
    }
  };

  const getTextColor = () => {
    switch (strength) {
      case "Weak":
        return "text-rose-500";
      case "Medium":
        return "text-amber-500";
      case "Strong":
        return "text-emerald-500";
      case "Very Strong":
        return "text-indigo-400";
      default:
        return "text-gray-400";
    }
  };

  return (
    <div className="w-full mt-6 space-y-2 animate-in fade-in slide-in-from-bottom-2 duration-300">
      <div className="flex justify-between items-center text-sm font-medium">
        <span className="text-gray-400">Password Strength</span>
        <span className={`transition-colors duration-300 ${getTextColor()}`}>
          {strength}
        </span>
      </div>
      <div className="h-2 w-full bg-gray-800 rounded-full overflow-hidden">
        <div
          className={`h-full rounded-full transition-all duration-500 ease-out ${getColor()}`}
          style={{ width: `${Math.max(score, 5)}%` }}
        />
      </div>
    </div>
  );
}
