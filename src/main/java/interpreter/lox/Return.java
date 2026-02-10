package interpreter.lox;

public class Return extends RuntimeException{
    final Object value;

    Return(Object value) {
        // JVM 메커니즘을 비활성화 시켜, 호출 스택을 감는 용도로만 사용
        // 리턴문은 제어흐름을 바꾸기에 스택을 강제로 되감지만, 이 경우는 재귀성이 강해서 사용한다
        // 무거운 호출 스택 조작에 사용할 수 있다
        super(null, null, false, false);
        this.value = value;
    }
}
