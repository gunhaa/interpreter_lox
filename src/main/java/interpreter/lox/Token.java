package interpreter.lox;

class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    // 에러 발생시 추적은 line을 통한 줄 번호까지만 구현한다
    final int line;

    public Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return type + " " + lexeme + " "+ literal;
    }
}
