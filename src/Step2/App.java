package Step2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //스캐너를 메인함수에서 열고 닫는것이 권장된다고 해서 메인에서 스캐너 객체 선언
        Scanner s = new Scanner(System.in);

        //Calculator 객체 선언과 동시에 매개변수로 스캐너 전달
        Calculator c = new Calculator(s);

        // methodSign (exit 또는 list 또는 (enter)를 입력받아 exit 이면 종료
        while(true){
            int methodSign = c.start(); //exit이 아닌 값이 들어왔다면 다시 start method 실행
            if (methodSign == 1) break; //종료
        }

        s.close(); //종료이후 메모리 누수 등 방지를 위해 스캐너 닫기
    }
}
