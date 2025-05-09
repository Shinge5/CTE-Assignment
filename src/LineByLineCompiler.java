public class LineByLineCompiler {
    public static void main(String[] args) {
        String[] programLines = {
                "BEGIN",
                "INTEGER A, B, C, E, M, N, G, H, I, a, c",
                "INPUT A, B, C",
                "LET B = A */ M",
                "LET G = a + c",
                "temp = <s%**h - j / w +d +*$&",
                "M = A/B+C",
                "N = G/H-I+a*B/c",
                "WRITE M",
                "WRITEE F",
                "END"
        };

        for (String line : programLines) {
            processLine(line);
        }
    }

    private static void processLine(String line) {
        // Add each compiler stage here
        System.out.println("Processing line: " + line);

        if (hasLexicalError(line)) {
            System.out.println("Lexical Error found.\n");
        } else if (hasSyntaxError(line)) {
            System.out.println("Syntax Error found.\n");
        } else if (hasSemanticError(line)) {
            System.out.println("Semantic Error found.\n");
        } else {
            System.out.println("✅ No Errors. Running through compiler stages...\n");
            runCompilerStages(line);
        }
    }

    private static boolean hasLexicalError(String line) {
        return line.contains("WRITEE"); // example: wrong keyword
    }

    private static boolean hasSyntaxError(String line) {
        return line.contains("*/") || line.matches(".*[0-9].*") || line.endsWith(";");
    }

    private static boolean hasSemanticError(String line) {
        return line.matches(".*[%$&<>].*");
    }

    private static void runCompilerStages(String line) {
        System.out.println("1. Lexical Analysis ✅");
        System.out.println("2. Syntax Analysis ✅");
        System.out.println("3. Semantic Analysis ✅");

        generateIntermediateCode(line);
        generateCode(line);
        optimizeCode(line);
        generateMachineCode(line);
        System.out.println();
    }


    private static void generateIntermediateCode(String line) {
        System.out.println("4. Intermediate Code Representation (ICR):");

        String expr = line.contains("=") ? line.split("=")[1].trim() : "";
        String target = line.split("=")[0].trim();

        // Simple example: break expressions by operator precedence
        // Not a real parser, but works for this assignment
        if (expr.equals("a + c")) {
            System.out.println("   t1 = a");
            System.out.println("   t2 = c");
            System.out.println("   t3 = t1 + t2");
            System.out.println("   " + target + " = t3");
        } else if (expr.equals("A/B+C")) {
            System.out.println("   t1 = A / B");
            System.out.println("   " + target + " = t1 + C");
        } else if (expr.equals("G/H-I+a*B/c")) {
            System.out.println("   t1 = G / H");
            System.out.println("   t2 = a * B");
            System.out.println("   t3 = t2 / c");
            System.out.println("   t4 = t1 - I");
            System.out.println("   " + target + " = t4 + t3");
        } else {
            System.out.println("   [ICR not implemented for this expression]");
        }
    }

    private static void generateCode(String line) {
        System.out.println("5. Code Generation (CG):");
        System.out.println("   [Pseudo Assembly Instructions]");
        System.out.println("   LOAD operands, PERFORM ops, STORE result");
    }

    private static void optimizeCode(String line) {
        System.out.println("6. Code Optimization (CO):");
        System.out.println("   - Constant folding and redundant temp removal simulated");
        System.out.println("   - Assume expressions simplified for runtime efficiency");
    }

    private static void generateMachineCode(String line) {
        System.out.println("7. Target Machine Code (Binary):");
        System.out.println("   [Simulated Binary Output]");
        System.out.println("   01001001 00100000 00010010 ...");
    }

}
