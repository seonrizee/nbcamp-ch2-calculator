package lv2;

import java.util.Queue;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        boolean isRunning = true;

        try (Scanner sc = new Scanner(System.in)) {
            while (isRunning) {
                Menu.printMenu();
                int cmd = Integer.parseInt(sc.nextLine());

                Menu selectedMenu = Menu.getMenu(cmd);
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
                    default:
                        System.out.println("다시 선택해주세요.");
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
        if (calculator.getResults().isEmpty()) {
            System.out.println("저장된 연산 결과가 없습니다.");
            return;
        }
        int removedVale = calculator.removeFirstResult();
        System.out.println("가장 먼저 저장된 연산 결과인 " + removedVale + "이(가) 삭제되었습니다.");
        showPrevResults(calculator.getResults());
    }

    /**
     * 저장된 이전 계산 결과를 출력합니다.
     *
     * @param prevResults
     */
    private static void showPrevResults(Queue<Integer> prevResults) {
        System.out.println("이전 연산 결과 조회가 시작됩니다.");
        if (prevResults.isEmpty()) {
            System.out.println("저장된 결과가 없습니다.");
            return;
        }

        int idx = 1;
        for (Integer cur : prevResults) {
            System.out.println("[" + idx++ + "]: " + cur);
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

        int first = App.getNumber(sc, inputOrder++);
        int second = App.getNumber(sc, inputOrder++);
        char operator = App.getOperator(sc);

        int result = calculator.calculate(first, second, operator);
        System.out.println(first + " " + operator + " " + second + " = " + result);
    }

    /**
     * 사용자로부터 정수를 입력받아 반환합니다.
     *
     * @param sc
     * @param inputOrder
     * @return
     */
    private static int getNumber(Scanner sc, int inputOrder) {
        System.out.print(inputOrder + "번째 숫자를 입력하세요: ");
        return Integer.parseInt(sc.nextLine());
    }


    /**
     * 사용자로부터 연산자를 입력받아 반환합니다.
     *
     * @param sc
     * @return
     */
    private static char getOperator(Scanner sc) {
        System.out.print("원하는 사칙연산 기호(+, -, *, /)를 입력하세요: ");
        return sc.nextLine().charAt(0);
    }
}

