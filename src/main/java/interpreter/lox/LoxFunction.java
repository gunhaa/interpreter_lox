package interpreter.lox;

import java.util.List;

public class LoxFunction implements LoxCallable{

    private final Stmt.Function fn;
    private final Environment closure;

    public LoxFunction(Stmt.Function fn, Environment closure) {
        this.fn = fn;
        this.closure = closure;
    }

    @Override
    public int arity() {
        return fn.params.size();
    }

    // lox의 core
    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        // 환경을 매번 새로 만들어야 nested function을 해결 할 수 있다
        // Environment environment = new Environment(interpreter.globals);
        Environment environment = new Environment(closure);

        for (int i = 0; i < fn.params.size(); i++) {
            environment.define(fn.params.get(i).lexeme, arguments.get(i));
        }

        try {
            interpreter.executeBlock(fn.body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }
        return null;
    }

    @Override
    public String toString() {
        return "<fn " + fn.name.lexeme + ">";
    }
}
