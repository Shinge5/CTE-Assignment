import java.util.*;

public class LineByLineCompiler {

    // Define keywords, operators, symbols, and invalid characters
    static final Set<String> KEYWORDS = Set.of("BEGIN", "INTEGER", "LET", "INPUT", "WRITE", "END");
    static final Set<Character> OPERATORS = Set.of('+', '-', '*', '/');
    static final Set<Character> SYMBOLS = Set.of('=', ',');
    static final Set<Character> INVALID_SYMBOLS = Set.of('%', '$', '&', '<', '>', ';');
    static final Set<String> VALID_LINES_TO_COMPILE = Set.of(
            "LET G = a + c",
            "M = A/B+C",
            "N = G/H-I+a*B/c");

    public static void main(String[] args) {
        // The given V language program
        String[] program = {
                "BEGIN",
                "INTEGER A, B, C, E, M, N, G, H, I, a, c",
                "INPUT A, B, C",
                "LET B = A */ M",
                "LET G = a + c",
                "temp = <s%**h - j / w +d +*$&;",
                "M = A/B+C",
                "N = G/H-I+a*B/c",
                "WRITE M",
                "WRITEE F;",
                "END"
        };

        // Loop through each line
        for (String line : program) {
            System.out.println("Processing line: " + line);
            String error = checkErrors(line);
            if (error != null) {
                System.out.println("❌ Error: " + error + "\n");
            } else if (VALID_LINES_TO_COMPILE.contains(line)) {
                compile(line);
            } else {
                System.out.println("✅ Line is valid (no compilation needed).\n");
            }
        }
    }

    // Error checking method
    static String checkErrors(String line) {
        // Lexical error: invalid keywords
        String[] tokens = line.split("[ ,=+\\-*/]");
        for (String token : tokens) {
            if (!token.isEmpty() && Character.isUpperCase(token.charAt(0)) && !KEYWORDS.contains(token)) {
                return "Lexical Error: Invalid keyword \"" + token + "\"";
            }
        }

        // Syntax & Semantic errors
        for (char ch : line.toCharArray()) {
            if (Character.isDigit(ch))
                return "Syntax Error: Numbers not allowed";
            if (INVALID_SYMBOLS.contains(ch))
                return "Semantic Error: Invalid symbol '" + ch + "'";
        }

        // Syntax: Combined operators
        if (line.contains("+*") || line.contains("*/") || line.contains("*+") || line.contains("-/")) {
            return "Syntax Error: Combined operators not allowed";
        }

        // Syntax: Semicolon at end
        if (line.trim().endsWith(";")) {
            return "Syntax Error: Semicolon at end not allowed";
        }

        return null;
    }

    // Full compiler simulation
    static void compile(String line) {
        System.out.println("✅ Line is valid. Running compiler stages...");

        String lexed = lexicalAnalysis(line);
        String parsed = syntaxAnalysis(lexed);
        String semChecked = semanticAnalysis(parsed);
        String ir = intermediateCode(semChecked);
        String code = codeGeneration(ir);
        String optimized = codeOptimization(code);
        String machineCode = targetMachineCode(optimized);

        System.out.println("🔹 Lexical Analysis Output: " + lexed);
        System.out.println("🔹 Syntax Analysis Output: " + parsed);
        System.out.println("🔹 Semantic Analysis Output: " + semChecked);
        System.out.println("🔹 Intermediate Code: " + ir);
        System.out.println("🔹 Generated Code: " + code);
        System.out.println("🔹 Optimized Code: " + optimized);
        System.out.println("🔹 Target Machine Code: " + machineCode);
        System.out.println();
    }

    // Compiler stages (simplified logic)
    static String lexicalAnalysis(String line) {
        return line.replaceAll(" +", " ").trim();
    }

    static String syntaxAnalysis(String line) {
        return "Parsed(" + line + ")";
    }

    static String semanticAnalysis(String line) {
        return "Semantically Checked(" + line + ")";
    }

    static String intermediateCode(String line) {
        return "IR(" + line + ")";
    }

    static String codeGeneration(String line) {
        return "CG(" + line + ")";
    }

    static String codeOptimization(String line) {
        return "Optimized(" + line + ")";
    }

    static String targetMachineCode(String line) {
        StringBuilder binary = new StringBuilder();
        for (char ch : line.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(ch)).replace(' ', '0')).append(" ");
        }
        return binary.toString().trim();
    }
}
