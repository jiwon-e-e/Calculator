package Step2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    private Scanner s ; //private 스캐너 변수 s 선언

    //생성자에서 스캐너 객체를 받아왔으므로 현재 클래스에서 사용할 수 있도록 함
    Calculator(Scanner s){
        this.s = s;
    }
    //계산 결과를 저장하는 CalResult 객체 선언
    CalResult c = new CalResult();


    //exit, list, 계산시작을 판별해서 main 에 가져다주기
    public int start(){
        System.out.println("####\n종료      : exit\n계산 결과 조회: list\n계산 시작   : (enter)\n####");
        //기본 메시지 출력
        String methodSign = s.nextLine();
        if (methodSign.equalsIgnoreCase("exit")){
            System.out.println("계산기를 종료합니다.");
            return 1; //exit 이 입력되면 계산기 종료 메시지 출력 후 return 1 하여 메인에서도 종료할 수 있게 함
        }
        else if (methodSign.equalsIgnoreCase("list")){
            System.out.println("계산 결과를 조회합니다."); //list 를 조회
            c.getResult(); //저장된 결과를 보여주고
            ifRemove(); // 만약 삭제하고싶다면 id를 입력할 수 있게 만들기
            // id를 입력하면 ifRemove 함수 내에서 rmResult 라는 삭제메소드 호출
            return 2; //사실 return 값을 구분해줄 필요는 없지만 언젠가 기능을 확장하게 되면 사용할 수도 있을 것 같아서 작성
        }
        else if (methodSign.equalsIgnoreCase("")){
            System.out.println("계산을 시작합니다.");
            ioHandler(); //input 3개를 받아야하므로 따로 함수 작성
            s.nextLine();
            return 0;
        }else{
            System.out.println("다시 입력해주세요."); //주어진 입력이 아니라면 재 입력 요청
            s.nextLine();
            return -1;
        }
    }

    //첫번째 정수, 기호, 두번째 정수 순서로 입력 (inputNum, inputSign 호출)받기, chkDivideZeroError 호출
    public void ioHandler(){
        System.out.print("첫번째 정수 입력: ");
        int firstInt = inputNum(); //첫번째 정수 입력 후 저장
        String sign = inputSign(); //sign 입력은 inputSign 함수 내에 메시지 출력
        System.out.print("두번째 정수 입력: ");
        int secondInt;
        do{ //일단 한번 입력받고
            secondInt = inputNum();
        }while (chkDivideZeroError(sign, secondInt)); //0으로 나누는지 확인하는 과정
        // 모두 정상적으로 입력되었으면 calculate 메소드 호출
        calculate(firstInt, secondInt, sign);
    }

    //정수만 제대로 입력되었는지 확인
    public int inputNum(){
        //정수를 입력해주세요
        //정수를 입력받은지 확인
        int temp; //지역변수 선언
        while (true){
            try{
                temp = s.nextInt();
                return temp;
            }catch (InputMismatchException e){ //정수가 아닌 다른 입력이 감지되면
                System.out.println("정수만 입력하세요.");
                s.nextLine(); //버퍼를 비우고 다시 inputNum 수행
            }
        }
    }

    //두번째 정수 입력받을 때 사용 num == 0, sign == / 인 상황을 잡기
    public boolean chkDivideZeroError(String sign, int num){
        if (sign.equals("/")&&(num == 0)){ //기호와 num (여기선 secondNum) 을 매개변수로 하여 조건문 실행
            System.out.println("0으로 나눌 수 없습니다."); //안내메시지 출력
            return true;
        }else{
            return false; //dowhile 문의 while 조건에 해당하므로 true/false 로 반환
        }
    }

    //원하는 sign 입력만 확인하기, exit list 등은 더 앞에서 확인하므로 연산기호만 입력받음
    public String inputSign(){
        String[] signList = {"+", "-", "x", "/"};
        //signList 를 지역변수로 선언
        while(true){
            System.out.print("기호 입력: ");
            String sign = s.next();

            for (int i=0;i<4;i++){ //기호 리스트 내에 있는 입력인지 확인
                if (sign.equals(signList[i])) {
                    return sign; //맞다면 바로 return
                }
                else{
                    if (i==3){ //반복문 마지막까지 돌았는데도 기호리스트에 없다면
                        System.out.print("가능한 입력이 아닙니다.");
                        System.out.println("기호는 +, -, x, / 로 입력가능합니다.");
                    } // 아니라면 안내메시지 출력 후 재입력
                }
            }
    }
}

    //계산 후 결과값을 HashMap 에 저장 (addResult 호출)
    public void calculate(int a, int b, String sign){
        switch (sign) //sign이 아래에 있는 수 중 하나인지 확인
        {
            case "+":
                System.out.println(a+ " + "+b+" = "+(a+b)); //계산결과를 출력해주고
                c.setResult(a,b,(a+b),sign); //결과값 저장은 CalResult 클래스에게 넘기기
                break;
            case "-":
                System.out.println(a+ " - "+b+" = "+(a-b));
                c.setResult(a,b,(a-b),sign);
                break;
            case "x":
                System.out.println(a+ " x "+b+" = "+(a*b));
                c.setResult(a,b,(a*b),sign);
                break;
            case "/":
                // 나누기는 소수로 출력해야하기때문에 double로 업캐스팅
                System.out.println(a+ " ÷ "+b+" = "+((double)a/(double)b));
                c.setResult(a,b,((double)a/b),"÷");
                // 나누기 sign 은 따로 작성한 문자열로 보내기
                break;
        }
    }

    //삭제하고싶으면 id를 입력받고 rmresult 호출
    public void ifRemove() {
        //호출위치: Calculator -> CalResult.getResult 이후
        while (true) {
            System.out.print("계산 결과를 삭제하려면 해당 id를, 다시 입력 화면으로 돌아가려면 (enter)를 눌러주세요:");

            String temp = s.nextLine(); //입력받기
            if (temp.equalsIgnoreCase("")) { //엔터라면 다시 입력으로
                break;
            } else { //엔터가 아니라면
                try { //숫자형태인지 확인하고
                    int id = Integer.parseInt(temp); //숫자로 형변환 해주고
                    c.rmResult(id); //삭제
                } catch (NumberFormatException e) {
                    System.out.println("id만 입력해주세요.");
                }
            }
        }
    }
}
