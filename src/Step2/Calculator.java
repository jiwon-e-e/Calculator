package Step2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    private Scanner s ;

    Calculator(Scanner s){
        this.s = s;
    }
    CalResult c = new CalResult();


    //exit, list, 계산시작을 판별해서 main 에 가져다주기
    public int start(){
        System.out.println("####\n종료      : exit\n계산 결과 조회: list\n계산 시작   : (enter)\n####");
        String methodSign = s.nextLine();
        if (methodSign.equalsIgnoreCase("exit")){
            System.out.println("계산기를 종료합니다.");
            return 1;
        }
        else if (methodSign.equalsIgnoreCase("list")){
            System.out.println("계산 결과를 조회합니다.");
            c.getResult();
            ifRemove();
            return 2;
        }
        else if (methodSign.equalsIgnoreCase("")){
            System.out.println("계산을 시작합니다.");
            ioHandler();
            s.nextLine();
            return 0;
        }else{
            System.out.println("다시 입력해주세요.");
            s.nextLine();
            return -1;
        }
    }

    //첫번째 정수, 기호, 두번째 정수 순서로 입력 (inputNum, inputSign 호출)받기, chkDivideZeroError 호출
    public void ioHandler(){
        System.out.print("첫번째 정수 입력: ");
        int firstInt = inputNum();
        String sign = inputSign();
        System.out.print("두번째 정수 입력: ");
        int secondInt;
        do{
            secondInt = inputNum();
        }while (chkDivideZeroError(sign, secondInt));

        calculate(firstInt, secondInt, sign);
    }

    //정수만 제대로 입력되었는지 확인
    public int inputNum(){
        //정수를 입력해주세요
        //정수를 입력받은지 확인
        int temp;
        while (true){
            try{
                temp = s.nextInt();
                return temp;
            }catch (InputMismatchException e){
                System.out.println("정수만 입력하세요.");
                s.nextLine();
            }
        }
    }

    //두번째 정수 입력받을 때 사용 num == 0, sign == / 인 상황을 잡기
    public boolean chkDivideZeroError(String sign, int num){
        if (sign.equals("/")&&(num == 0)){
            System.out.println("0으로 나눌 수 없습니다.");
            return true;
        }else{
            return false;
        }
    }

    //원하는 sign 입력만 확인하기, exit list 등은 더 앞에서 확인하므로 연산기호만 입력받음
    public String inputSign(){
        String[] signList = {"+", "-", "x", "/"};
        while(true){
            System.out.print("기호 입력: ");
            String sign = s.next();

            for (int i=0;i<4;i++){
                if (sign.equals(signList[i])) {
                    return sign;
                }
                else{
                    if (i==3){
                        System.out.print("가능한 입력이 아닙니다.");
                        System.out.println("기호는 +, -, x, / 로 입력가능합니다.");
                    }
                    continue;
                }
            }
    }
}

    //계산 후 결과값을 HashMap 에 저장 (addResult 호출)
    public void calculate(int a, int b, String sign){
        switch (sign)
        {
            case "+":
                System.out.println(a+ " + "+b+" = "+(a+b));
                c.setResult(a,b,(a+b),sign);
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
                break;
        }
    }

    //삭제하고싶으면 id를 입력받고 rmresult 호출
    public void ifRemove() {
        while (true) {
            System.out.print("계산 결과를 삭제하려면 해당 id를, 다시 입력 화면으로 돌아가려면 (enter)를 눌러주세요:");

            String chk = s.nextLine();
            if (chk.equalsIgnoreCase("")) {
                break;
            } else {
                try {
                    int id = Integer.parseInt(chk);
                    c.rmResult(id);
                } catch (NumberFormatException e) {
                    System.out.println("id만 입력해주세요.");
                }
            }
        }
    }
}
