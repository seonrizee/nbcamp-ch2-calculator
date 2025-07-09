package lv2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        try (Scanner sc = new Scanner(System.in)) {
            do {
                int inputOrder = 1;

                int first = App.getNumber(sc, inputOrder++);
                int second = App.getNumber(sc, inputOrder++);
                char operator = App.getOperator(sc);

                int result = calculator.calculate(first, second, operator);
                System.out.println(first + " " + operator + " " + second + " = " + result);

            } while (shouldContinue(sc));
        }

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

    /**
     * 사용자로부터 계산을 계속 이어갈지에 대해 입력받아 결과를 반환합니다.
     *
     * @param sc
     * @return
     */
    private static boolean shouldContinue(Scanner sc) {
        System.out.print("\n계산기를 계속 이용하려면 아무 키나 눌러주세요. 종료하려면 'exit'를 입력해주세요.: ");
        String cmd = sc.nextLine();

        if (cmd.equals("exit")) {
            System.out.println("계산기가 종료됩니다.");
            return false;
        }
        System.out.println("다시 계산이 시작됩니다.\n");
        return true;
    }
}

