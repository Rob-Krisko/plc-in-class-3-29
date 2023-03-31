public class Lexer {
    private String input;
    private int position;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    public TokenType nextToken() {
        if (position >= input.length()) {
            return null; // End of input
        }

        char currentChar = input.charAt(position++);
        switch (currentChar) {
            case 'A':
                return TokenType.A;
            case 'B':
                return TokenType.B;
            case 'a':
                return TokenType.a;
            case 'b':
                return TokenType.b;
            case 'c':
                return TokenType.c;
            default:
                throw new IllegalStateException("Unexpected character: " + currentChar);
        }
    }

    public enum TokenType {
        A, B, a, b, c
    }
}
