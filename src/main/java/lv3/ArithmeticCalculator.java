package lv3;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class ArithmeticCalculator implements Calculator {
    /**
     * 연산 결과를 저장하는 컬렉션 Queue 입니다.
     */
    private Queue<Double> results = new ArrayDeque<>();

    /**
     * 입력된 숫자와 연산자에 따라 연산을 수행하여 결과를 반환합니다.
     *
     * @param first        첫번쨰 입력된 숫자
     * @param second       두번째 입력된 숫자
     * @param operatorType 입력된 연산자
     * @return 연산 결과
     */
    @Override
    public <T extends Number> Number calculate(T first, T second, OperatorType operatorType) {

        double result = operatorType.operate(first, second);

        results.offer(result);
        return result;
    }

    /**
     * 연산 결과가 저장된 Queue의 첫번째 엘리먼트를 제거합니다.
     *
     * @return 제거된 엘리먼트
     */
    public Optional<Double> removeFirstResult() {
        return Optional.ofNullable(results.poll());
    }

    /**
     * 연산 결과가 저장된 Queue를 반환합니다.
     *
     * @return 연산 결과가 저장된 Queue
     */
    public Queue<Double> getResults() {
        return new ArrayDeque<>(results);
    }

    /**
     * 연산 결과가 저장된 Queue를 수정합니다. -> 제거해도 된다고 생각하나 과제 조건이라 놔둡니다.
     *
     * @param results 연산 결과가 저장된 새로운 Queue
     */
    public void setResults(Queue<Double> results) {
        this.results = results;
    }

    /**
     * 저장된 연산 결과 중 입력된 값보다 큰 값들만 List로 반환합니다.
     *
     * @param value 사용자가 입력한 숫자
     * @return 저장된 연산 결과 중 입력된 값보다 큰 값들
     */
    public List<Double> getResultsGreaterThan(double value) {
        return results.stream()
                .filter(cur -> cur > value)
                .toList();
    }

    /**
     * 저장된 연산 결과 중 입력된 값보다 작은 값들만 List로 반환합니다.
     *
     * @param value 사용자가 입력한 숫자
     * @return 저장된 연산 결과 중 입력된 값보다 작은 값들
     */
    public List<Double> getResultsLessThan(double value) {
        return results.stream()
                .filter(cur -> cur < value)
                .toList();
    }

}
