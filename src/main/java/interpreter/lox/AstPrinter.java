package interpreter.lox;

public class AstPrinter implements Expr.Visitor<String>{
    @Override
    public String visitBinaryExpr(Expr.Binary EXPR) {
        return parenthesize(EXPR.operator.lexeme, EXPR.left, EXPR.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping EXPR) {
        return parenthesize("group", EXPR.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal EXPR) {
        if (EXPR.value == null) return "nil";
        return EXPR.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary EXPR) {
        return parenthesize(EXPR.operator.lexeme, EXPR.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");
        return builder.toString();
    }

    String print(Expr expr) {
        return expr.accept(this);
    }

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)
                ),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)
                )
        );
        System.out.println(new AstPrinter().print(expression));
    }
}
