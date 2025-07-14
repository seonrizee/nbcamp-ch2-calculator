package lv3;

public interface Calculator {
    <T extends Number> Number calculate(T first, T second, OperatorType operatorType);
}
