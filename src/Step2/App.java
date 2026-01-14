package Step2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Calculator c = new Calculator(s);

        while(true){
            int methodSign = c.start();
            if (methodSign == 1) break;
        }

        s.close();
    }
}
