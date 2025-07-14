package lv3;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class Calculator {

    /**
     * 연산 결과를 저장하는 컬렉션 Queue
     */
    private Queue<Integer> results = new ArrayDeque<>();

    /**
     * 연산 결과를 반환하는 메서드
     *
     * @param first   첫번쨰 입력된 숫자
     * @param second  두번째 입력된 숫자
     * @param command 입력된 연산자
     * @return 연산 결과
     */
    public int calculate(int first, int second, char command) {

        OperatorType operatorType = OperatorType.getOperatorFromCmd(command);
        int result = operatorType.operate(first, second);

        results.offer(result);
        return result;
    }

    /**
     * @return
     */
    public Optional<Integer> removeFirstResult() {
        return Optional.ofNullable(results.poll());
    }

    /**
     * 연산 결과가 저장된 필드에 접근하는 메서드
     *
     * @return
     */
    public Queue<Integer> getResults() {
        return new ArrayDeque<>(results);
    }

    /**
     * 연산 결과가 저장된 필드를 수정하는 메서드
     *
     * @param results
     */
    public void setResults(Queue<Integer> results) {
        this.results = results;
    }
}
