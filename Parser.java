public class Parser {
    private Lexer lexer;
    private Lexer.TokenType currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    public void parseS() {
        if (currentToken == Lexer.TokenType.a) {
            consume(Lexer.TokenType.a);
            parseS();
            consume(Lexer.TokenType.b);
        } else if (currentToken == Lexer.TokenType.b) {
            consume(Lexer.TokenType.b);
            parseA();
            parseA();
        } else if (currentToken == Lexer.TokenType.c) {
            consume(Lexer.TokenType.c);
            parseS();
        } else {
            throw new IllegalStateException("Expected a, b, or c, but found: " + currentToken);
        }
    }

    public void parseA() {
        if (currentToken == Lexer.TokenType.a) {
            consume(Lexer.TokenType.a);
        } else if (currentToken == Lexer.TokenType.b) {
            consume(Lexer.TokenType.b);
        } else if (currentToken == Lexer.TokenType.c) {
            consume(Lexer.TokenType.c);
            parseB();
        } else {
            throw new IllegalStateException("Expected a, b, or c, but found: " + currentToken);
        }
    }

    public void parseB() {
        if (currentToken == Lexer.TokenType.c) {
            consume(Lexer.TokenType.c);
            parseB();
        } else if (currentToken == Lexer.TokenType.a) {
            consume(Lexer.TokenType.a);
            parseA();
        } else {
            throw new IllegalStateException("Expected a or c, but found: " + currentToken);
        }
    }

    private void consume(Lexer.TokenType expected) {
        if (currentToken == expected) {
            currentToken = lexer.nextToken();
        } else {
            throw new IllegalStateException("Expected " + expected + ", but found: " + currentToken);
        }
    }

    public static void main(String[] args) {
        String[] inputs = {
            "abcabababab",
            "aabbbbb",
            "cbcccccaab",
            "acacacbaabbb",
            "bbb",
            "acb"
        };
    
        for (String input : inputs) {
            System.out.println("Parsing input: " + input);
            Lexer lexer = new Lexer(input);
            Parser parser = new Parser(lexer);
    
            try {
                parser.parseS();
                System.out.println("The input '" + input + "' is valid.");
            } catch (IllegalStateException e) {
                System.out.println("Error in input '" + input + "': " + e.getMessage());
            }
            System.out.println();
        }
    }    
}
