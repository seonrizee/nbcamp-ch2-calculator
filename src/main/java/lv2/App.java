package lv2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int first = sc.nextInt();

                System.out.print("두 번째 숫자를 입력하세요: ");
                int second = sc.nextInt();

                System.out.print("원하는 사칙연산 기호를 입력하세요: ");
                char operator = sc.next().charAt(0);

                int result = calculator.calculate(first, second, operator);
                System.out.println(first + " " + operator + " " + second + " = " + result);

                System.out.print("\n계산기를 계속 이용하려면 아무 키나 눌러주세요. 종료하려면 exit를 입력해주세요.: ");
                sc.nextLine();
                String cmd = sc.nextLine();

                if (cmd.equals("exit")) {
                    System.out.println("계산기가 종료됩니다.");
                    break;
                }
            }
        }

    }
}

