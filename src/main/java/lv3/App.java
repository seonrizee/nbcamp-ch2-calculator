package lv3;

import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        boolean isRunning = true;

        try (Scanner sc = new Scanner(System.in)) {

            while (isRunning) {
                Menu.printMenu();
                int cmd;
                try {
                    cmd = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("오류: 올바른 번호를 입력해주세요.");
                    continue;
                }

                Optional<Menu> selectedMenuOpt = Menu.getMenu(cmd);
                if (selectedMenuOpt.isEmpty()) {
                    System.out.println("오류: 메뉴에 없는 번호입니다. 다시 선택해주세요.");
                    continue;
                }

                Menu selectedMenu = selectedMenuOpt.get();
                switch (selectedMenu) {
                    case CALCULATE:
                        handleCalculation(sc, calculator);
                        break;
                    case VIEW:
                        showPrevResults(calculator.getResults());
                        break;
                    case REMOVE_FIRST:
                        handleRemove(calculator);
                        break;
                    case EXIT:
                        System.out.println("계산기가 종료됩니다.");
                        isRunning = false;
                        break;
                }
            }
        }
    }


    /**
     * 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제합니다.
     *
     * @param calculator
     */
    private static void handleRemove(Calculator calculator) {
        System.out.println("가장 먼저 저장된 연산 결과 삭제가 시작됩니다.");

        Optional<Double> removedValue = calculator.removeFirstResult();
        if (removedValue.isEmpty()) {
            System.out.println("저장된 연산 결과가 없습니다.");
            return;
        }

        System.out.println("가장 먼저 저장된 연산 결과인 " + printFormatNumber(removedValue.get()) + "이(가) 삭제되었습니다.");
        showPrevResults(calculator.getResults());
    }

    /**
     * 저장된 이전 계산 결과를 출력합니다.
     *
     * @param prevResults
     */
    private static void showPrevResults(Queue<Double> prevResults) {
        System.out.println("이전 연산 결과 조회가 시작됩니다.");
        if (prevResults.isEmpty()) {
            System.out.println("저장된 결과가 없습니다.");
            return;
        }

        int idx = 1;
        for (Double cur : prevResults) {
            System.out.println("[" + idx++ + "]: " + printFormatNumber(cur));
        }
    }

    /**
     * 계산을 처리합니다.
     *
     * @param sc
     * @param calculator
     */
    private static void handleCalculation(Scanner sc, Calculator calculator) {
        System.out.println("계산이 시작됩니다.\n");
        int inputOrder = 1;

        double first = getNumber(sc, inputOrder++);
        double second = getNumber(sc, inputOrder++);
        char operator = getOperator(sc);

        try {
            double result = calculator.calculate(first, second, operator);
            System.out.println(
                    "결과: " + printFormatNumber(first) + " " + operator + " " + printFormatNumber(second) + " = "
                            + printFormatNumber(result));
        } catch (ArithmeticException e) {
            System.out.println("오류: 0으로 나눌 수 없습니다.");
        }
    }

    /**
     * 사용자로부터 정수를 입력받아 반환합니다.
     *
     * @param sc
     * @param inputOrder
     * @return
     */
    private static double getNumber(Scanner sc, int inputOrder) {
        while (true) {
            System.out.print(inputOrder + "번째 숫자를 입력하세요: ");
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("오류: 숫자를 입력해주세요.");
            }
        }
    }


    /**
     * 사용자로부터 연산자를 입력받아 반환합니다.
     *
     * @param sc
     * @return
     */
    private static char getOperator(Scanner sc) {
        while (true) {
            System.out.print("원하는 사칙연산 기호(+, -, *, /)를 입력하세요: ");
            String input = sc.nextLine().trim();
            if (input.length() != 1 || !"+-*/".contains(input)) {
                System.out.println("오류: +, -, *, / 중 하나의 기호만 입력해야 합니다.");
                continue;
            }
            return input.charAt(0);
        }
    }

    /**
     * 소수점의 존재 여부에 따라 숫자의 출력을 다르게 하여 반환합니다.
     *
     * @param number
     * @return
     */
    private static String printFormatNumber(double number) {
        if (number % 1 == 0) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }
}

