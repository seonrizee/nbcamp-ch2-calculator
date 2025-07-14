package lv3;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum OperatorType {
    PLUS('+', (first, second) -> first + second),
    MINUS('-', (first, second) -> first - second),
    MULTIPLY('*', (first, second) -> first * second),
    DIVIDE('/', (first, second) -> {
        if (second == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return first / second;
    });


    private final char symbol;
    private final BiFunction<Double, Double, Double> operation;

    OperatorType(char symbol, BiFunction<Double, Double, Double> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }


    /**
     * 사용자로부터 입력받은 기호에 해당하는 operator를 반환합니다.
     *
     * @param command
     * @return
     */
    public static OperatorType findSymbol(char command) {
        return Arrays.stream(values())
                .filter(op -> op.symbol == command)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("+, -, *, / 중 하나의 기호만 입력해야 합니다."));
    }


    /**
     * operator 유형별로 BiFunction 함수형 인터페이스의 메소드인 apply에 람다로 정의한 연산을 수행하도록 합니다.
     *
     * @param first
     * @param second
     * @return
     */
    public double operate(Number first, Number second) {
        return operation.apply(first.doubleValue(), second.doubleValue());
    }

    public char getSymbol() {
        return symbol;
    }
}