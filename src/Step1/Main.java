package Step1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //scanner 변수 선언
        Scanner s = new Scanner(System.in);
        //종료조건 기재: 계산기를 시작할 때 한 번만 출력되게함
        System.out.println("계산을 시작합니다. 종료하시려면 exit 을 입력해주세요.");

        //sign 을 입력받아 exit 과 같기 전까지는 무한루프 while(true)사용
        while (true){
            System.out.println("## 기호를 입력하세요. ##");
            //sign 변수는 String 자료형에 저장
            String sign = s.next();
            //가장먼저 입력받은게 exit 인지 조건문을 통해 확인
            if(sign.equals("exit")){
                System.out.println("계산기를 종료합니다.");
                break;
            }

            //기호가 제대로 입력되었는지 확인, 아니라면 메시지 출력
            String[] signList = {"+", "-", "x", "/"};
            int chk1=0;

            for (int i=0;i<4;i++){
                if (sign.equals(signList[i])) chk1=1;
            }

            if (chk1==0) {
                System.out.println("가능한 입력이 아닙니다. 기호는 +, -, x, / 로 입력가능합니다.");
                continue;
            }

            // 제대로 된 입력이 나올 때 까지 while 문으로 반복
            int a=0;
            while (true){
                try{
                    System.out.print("첫번째 정수를 입력하세요: ");
                    a = s.nextInt();
                    //제대로 입력이 되었다면 catch로 넘어가지 않으니 break 을 통해 반복문 탈출
                    break;
                }catch (InputMismatchException e){
                    //입력받은 값이 자료형에 알맞은지 확인하고 아니라면 오류메시지 출력
                    System.out.println("정수만 입력하세요.");
                    //1/9 TIL 3.2 입력 무한반복 방지 buffer 비우기
                    s.nextLine();
                }
            }

            // b도 a와 똑같은 형식으로 입력받음
            int b=0;
            while (true){
                try{
                    System.out.print("두번째 정수를 입력하세요: ");
                    b = s.nextInt();
                    // sign 이 나누기 연산이고, 나누는 수가 분모라면 while문 재실행
                    if ((b==0)&&sign.equals("/")) {
                        System.out.println("0으로 나눌 수 없습니다.");
                        s.nextLine();
                        continue;
                    }
                    break;
                }catch (InputMismatchException e){
                    System.out.println("정수만 입력하세요.");
                    s.nextLine();
                }
            }

            // sign에 따라 switch 문의 case 를 다르게 하여 출력
            switch (sign)
            {
                case "+":
                    System.out.println(a+ " + "+b+" = "+(a+b));
                    break;
                case "-":
                    System.out.println(a+ " - "+b+" = "+(a-b));
                    break;
                case "x":
                    System.out.println(a+ " x "+b+" = "+(a*b));
                    break;
                case "/":
                    // 나누기는 소수로 출력해야하기때문에 double로 업캐스팅
                    System.out.println(a+ " ÷ "+b+" = "+((double)a/(double)b));
                    break;
            }
        }
    }
}
