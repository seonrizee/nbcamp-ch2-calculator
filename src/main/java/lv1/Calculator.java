package lv1;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int first = sc.nextInt();

                System.out.print("두 번째 숫자를 입력하세요: ");
                int second = sc.nextInt();

                System.out.print("원하는 사칙연산 기호를 입력하세요: ");
                char operator = sc.next().charAt(0);

                System.out.print(first + " " + operator + " " + second + " = ");

                switch (operator) {
                    case '+':
                        System.out.print(first + second);
                        break;
                    case '-':
                        System.out.print(first - second);
                        break;
                    case '*':
                        System.out.print(first * second);
                        break;
                    case '/':
                        if (second == 0) {
                            System.out.println("0으로 나눌 수 없습ㄴ니다.");
                        } else {
                            System.out.print(first / second);
                        }
                        break;
                    default:
                        System.out.println("오류가 발생했습니다.");
                }

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

