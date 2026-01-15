package Step3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //스캐너를 메인함수에서 열고 닫는것이 권장된다고 해서 메인에서 스캐너 객체 선언
        Scanner s = new Scanner(System.in);

        //Calculator 객체 선언 (제네릭) 과 동시에 매개변수로 스캐너 전달
        ArithmeticCalculator<Double> cal = new ArithmeticCalculator<>(s);

        // methodSign (exit 또는 list 또는 (enter)를 입력받아 exit 이면 종료
        while(true){
            int methodSign = cal.start(); //exit이 아닌 값이 들어왔다면 다시 start method 실행
            if (methodSign == 1) break; //종료조건 검사
        }

        s.close(); //스캐너 닫기
    }
}
