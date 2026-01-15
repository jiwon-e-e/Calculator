package Step3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        ArithmeticCalculator<Double> cal = new ArithmeticCalculator<>(s);

        while(true){
            int methodSign = cal.start();
            if (methodSign == 1) break;
        }

        s.close();
    }
}
