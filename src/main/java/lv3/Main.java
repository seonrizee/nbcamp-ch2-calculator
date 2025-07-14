package lv3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArithmeticCalculator calculator = new ArithmeticCalculator();
        Scanner sc = new Scanner(System.in);

        App app = new App(calculator, sc);
        app.run();
        
        sc.close();
    }
}
