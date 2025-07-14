package lv3;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;

public class App {

    private final ArithmeticCalculator calculator;
    private final Scanner sc;

    public App(ArithmeticCalculator calculator, Scanner sc) {
        this.calculator = calculator;
        this.sc = sc;
    }

    /**
     * 계산기에서 수행할 수 있는 모든 메뉴를 출력합니다.
     */
    private static void showAllMenu() {
        System.out.println();
        log("--------------------------------------------------------------");
        Menu.getFormattedMenuItems()
                .forEach(App::log);
        log("--------------------------------------------------------------\n");
        logInput("원하는 메뉴의 숫자를 입력하세요.");
    }

    /**
     * 저장된 이전 계산 결과를 특정값 기준으로 조회합니다.
     *
     * @param sc         스캐너
     * @param calculator 계산기
     */
    private static void showPrevResultsWithValues(Scanner sc, ArithmeticCalculator calculator) {
        log("이전 연산 결과 조회가 시작됩니다. (기준값 사용)");

        double value = getNumber(sc, "기준이 될 숫자를 입력하세요.");

        List<Double> filteredResults = calculator.getResultsGreaterThan(value);
        printFilteredResults(filteredResults,
                "저장된 연산 결과 중 " + printFormatNumber(value) + "보다 큰 숫자가 없습니다.",
                "저장된 연산 결과 중 " + printFormatNumber(value) + "보다 큰 숫자 목록입니다.");

        filteredResults = calculator.getResultsLessThan(value);
        printFilteredResults(filteredResults,
                "저장된 연산 결과 중 " + printFormatNumber(value) + "보다 작은 숫자가 없습니다.",
                "저장된 연산 결과 중 " + printFormatNumber(value) + "보다 작은 숫자 목록입니다.");
    }

    /**
     * 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제합니다.
     *
     * @param calculator 계산기
     */
    private static void handleRemove(ArithmeticCalculator calculator) {
        log("가장 먼저 저장된 연산 결과 삭제가 시작됩니다.");

        Optional<Double> removedValue = calculator.removeFirstResult();
        if (removedValue.isEmpty()) {
            log("저장된 연산 결과가 없습니다.");
            return;
        }

        log("가장 먼저 저장된 연산 결과인 " + printFormatNumber(removedValue.get()) + "이(가) 삭제되었습니다.");
        showPrevResults(calculator.getResults());
    }

    /**
     * 저장된 이전 계산 결과를 조회합니다.
     *
     * @param prevResults 저장된 이전 계산 결과
     */
    private static void showPrevResults(Queue<Double> prevResults) {
        log("이전 연산 결과 조회가 시작됩니다.");

        if (prevResults.isEmpty()) {
            log("저장된 결과가 없습니다.");
            return;
        }

        int idx = 1;
        for (Double cur : prevResults) {
            log("[" + idx++ + "]: " + printFormatNumber(cur));
        }
    }

    /**
     * 계산을 처리합니다.
     *
     * @param sc         스캐너
     * @param calculator 계산기
     */
    private static void handleCalculation(Scanner sc, ArithmeticCalculator calculator) {
        log("계산이 시작됩니다.");
        int inputOrder = 1;

        double first = getNumber(sc, inputOrder++ + "번째 숫자를 입력하세요.");
        double second = getNumber(sc, inputOrder++ + "번째 숫자를 입력하세요.");
        OperatorType operator = getOperator(sc);

        try {
            double result = (double) calculator.calculate(first, second, operator);
            log(
                    "결과: " + printFormatNumber(first) + " " + operator.getSymbol() + " " + printFormatNumber(second)
                            + " = "
                            + printFormatNumber(result));
        } catch (ArithmeticException e) {
            logError("0으로 나눌 수 없습니다.");
        } catch (IllegalArgumentException e) {
            logError(e.getMessage());
        }
    }

    /**
     * 사용자로부터 정수를 입력받아 반환합니다.
     *
     * @param sc      스캐너
     * @param message 입력받을 때 사용할 메시지
     * @return 입력받은 정수
     */
    private static double getNumber(Scanner sc, String message) {
        while (true) {
            logInput(message);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                logError("숫자를 입력해주세요. ");
            }
        }
    }

    /**
     * 사용자로부터 연산자를 입력받아 반환합니다.
     *
     * @param sc 스캐너
     * @return 입력받은 연산자 객체
     */
    private static OperatorType getOperator(Scanner sc) {
        while (true) {
            logInput("원하는 사칙연산 기호(+, -, *, /)를 입력하세요.");

            String input = sc.nextLine().trim();
            if (input.length() != 1) {
                logError("연산자 기호는 한 글자만 입력해야 합니다.");
                continue;
            }
            char inputChar = input.charAt(0);
            try {
                return OperatorType.findSymbol(inputChar);
            } catch (IllegalArgumentException e) {
                logError(e.getMessage());
            }
        }
    }

    /**
     * 소수점의 존재 여부에 따라 숫자의 출력을 다르게 하여 반환합니다.
     *
     * @param number 출력할 숫자
     * @return 포맷된 숫자
     */
    private static String printFormatNumber(double number) {
        if (number % 1 == 0) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }

    /**
     * 저장된 연산 결과 중 입력된 조건을 만족하는 결과들을 포맷에 맞춰 출력합니다.
     *
     * @param filteredResults 저장된 연산 결과
     * @param emptyMsg        결과가 없을 때 출력할 메시지
     * @param okMsg           결과가 있을 때 출력할 메시지
     */
    private static void printFilteredResults(List<Double> filteredResults, String emptyMsg, String okMsg) {
        if (filteredResults.isEmpty()) {
            log(emptyMsg);
        } else {
            log(okMsg);
            for (int idx = 0; idx < filteredResults.size(); idx++) {
                log("[" + (idx + 1) + "]: " + printFormatNumber(filteredResults.get(idx)));
            }
        }
    }

    /**
     * 일반적인 내용을 포맷에 맞춰 출력합니다.
     *
     * @param message 출력할 메시지
     */
    public static void log(String message) {
        System.out.println("APP:::: " + message);
    }

    /**
     * 사용자의 입력을 요청하는 내용을 포맷에 맞춰 출력합니다.
     *
     * @param message 출력할 메시지
     */
    public static void logInput(String message) {
        System.out.print("APP:::: " + message + ": ");
    }

    /**
     * 예외를 처리하는 내용을 포맷에 맞춰 출력합니다.
     *
     * @param message 출력할 메시지
     */
    public static void logError(String message) {
        System.out.println("App_ERROR:::: " + message);
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            showAllMenu();

            Menu selectedMenu;
            try {
                int cmd = Integer.parseInt(sc.nextLine().trim());
                selectedMenu = Menu.findMenu(cmd);
            } catch (NumberFormatException e) {
                logError("원하는 메뉴의 번호를 입력하세요.");
                continue;
            } catch (IllegalArgumentException e) {
                logError(e.getMessage());
                continue;
            }

            switch (selectedMenu) {
                case CALCULATE:
                    handleCalculation(sc, calculator);
                    break;
                case VIEW:
                    showPrevResults(calculator.getResults());
                    break;
                case VIEW_WITH_VALUE:
                    showPrevResultsWithValues(sc, calculator);
                    break;
                case REMOVE_FIRST:
                    handleRemove(calculator);
                    break;
                case EXIT:
                    log("계산기가 종료됩니다.");
                    isRunning = false;
                    break;
            }
        }

    }
}

