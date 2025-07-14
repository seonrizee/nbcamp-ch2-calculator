package lv3;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum OperatorType {
    PLUS('+', (first, second) -> first + second),
    MINUS('-', (first, second) -> first - second),
    MULTIPLY('*', (first, second) -> first * second),
    DIVIDE('/', (first, second) -> {
        if (second == 0) {
            throw new ArithmeticException("오류: 0으로 나눌 수 없습니다.");
        }
        return first / second;
    });


    private final char symbol; // 수정: 쉼표를 세미콜론으로 변경
    private final BiFunction<Integer, Integer, Integer> operation;

    OperatorType(char command, BiFunction<Integer, Integer, Integer> operation) {
        this.symbol = command;
        this.operation = operation;
    }

    public static OperatorType getOperatorFromCmd(char command) {
        return Arrays.stream(values())
                .filter(op -> op.symbol == command)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("오류: 지원하지 않는 연산자 기호입니다: " + command));
    }

    public int operate(int first, int second) {
        return operation.apply(first, second);
    }

}