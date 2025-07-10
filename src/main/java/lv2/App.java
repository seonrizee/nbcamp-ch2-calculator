package lv2;

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
                        calculate(sc, calculator);
                        break;
                    case VIEW:
                        System.out.println("조회 기능이 시작됩니다.");
                        break;
                    case EXIT:
                        System.out.println("계산기가 종료됩니다.");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("다시 선택해주세요.");
                }
            }
        }

    }

    private static void calculate(Scanner sc, Calculator calculator) {
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

