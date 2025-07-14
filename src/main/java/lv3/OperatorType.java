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
    private final BiFunction<Double, Double, Double> operation;

    OperatorType(char command, BiFunction<Double, Double, Double> operation) {
        this.symbol = command;
        this.operation = operation;
    }


    /**
     * 사용자로부터 입력받은 기호에 해당하는 operator를 반환합니다.
     *
     * @param command
     * @return
     */
    public static OperatorType getOperatorFromCmd(char command) {
        return Arrays.stream(values())
                .filter(op -> op.symbol == command)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("오류: 지원하지 않는 연산자 기호입니다: " + command));
    }


    /**
     * operator 유형별로 BiFunction 함수형 인터페이스의 메소드인 apply에 람다로 정의한 연산을 수행하도록 합니다.
     *
     * @param first
     * @param second
     * @return
     */
    public double operate(double first, double second) {
        return operation.apply(first, second);
    }

}