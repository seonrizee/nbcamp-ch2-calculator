package lv1;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("첫 번째 숫자를 입력하세요: ");
            int first = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            int second = sc.nextInt();
        }
    }
}
