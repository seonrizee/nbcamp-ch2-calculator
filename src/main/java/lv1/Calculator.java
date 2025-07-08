package lv1;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("첫 번째 숫자를 입력하세요: ");
            int first = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            int second = sc.nextInt();

            System.out.print("원하는 사칙연산 기호를 입력하세요: ");
            char operator = sc.next().charAt(0);

            System.out.print("\n" + first + " " + operator + " " + second + " = ");

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

        }

    }
}

