package Step3;

import java.util.Scanner;
import java.util.function.BiFunction;

public class ArithmeticCalculator<T extends Number> {
    private Scanner s;

    CalResult c = new CalResult();
    public enum OperatorType{


        PLUS ("+", (a,b) -> a + b),
        MINUS ("-", (a,b) -> a - b),
        TIMES ("*", (a,b) -> a * b),
        DIVIDE ("/", (a,b) -> a / b);

        BiFunction<Double,Double,Double> operatorType;
        String sign;


        // 생성자
        OperatorType(String sign, BiFunction<Double,Double,Double> operatorType) {
            this.sign = sign;
            this.operatorType = operatorType;
        }

         public Double calculate(Double a, Double b) {
            return operatorType.apply(a,b);
        }

        static OperatorType getSign(String sign){
            for (OperatorType op : values()){
                if (op.sign.equals(sign)){
                    return op;
                }
            }
            throw new IllegalArgumentException("기호 입력 실패");
        }
    }

    ArithmeticCalculator(Scanner s){
        this.s = s;
    }

    //exit, list, 계산시작을 판별해서 main 에 가져다주기
    public int start(){
        System.out.println("####\n계산 결과 조회: list\n계산 시작: (enter)\n종료: exit");
        System.out.println("(new!) 값 입력하여 해당 값보다 큰 결과값 조회 : find\n####");
        String methodSign = s.nextLine();
        if (methodSign.equalsIgnoreCase("exit")){
            System.out.println("계산기를 종료합니다.");
            return 1;
        }
        else if (methodSign.equalsIgnoreCase("list")){
            System.out.println("계산 결과를 조회합니다.");
            c.getResult();
            ifRemove();
            return 0;
        }
        else if (methodSign.equalsIgnoreCase("")){
            System.out.println("계산을 시작합니다.");
            ioHandler();
            s.nextLine();
            return 0;
        }
        else if (methodSign.equalsIgnoreCase("find")){
            System.out.println("값을 입력해주세요. 입력된 값보다 큰 결과값을 조회합니다.");
            findNum();
            return 0;
        }
        else{
            System.out.println("다시 입력해주세요.");
            s.nextLine();
            return -1;
        }
    }

    void findNum(){
        double rootNum = s.nextDouble();
        c.compareNum(rootNum);
    }

    //첫번째 정수, 기호, 두번째 정수 순서로 입력 (inputNum, inputSign 호출)받기, chkDivideZeroError 호출
    public void ioHandler(){
        System.out.print("첫번째 정수 입력: ");
        Double firstNum = inputNum();
        String sign = inputSign();
        OperatorType op = OperatorType.getSign(sign);

        System.out.print("두번째 정수 입력: ");
        Double secondNum;
        do{
            secondNum = inputNum();
        }while (chkDivideZeroError(sign, secondNum));

        double ret = op.calculate(firstNum, secondNum);
        c.setResult(firstNum,secondNum,ret, sign);
        System.out.println(firstNum + " "+ sign +" "+ secondNum + " = "+ ret);
    }

    double read() {
        try {
            return Double.parseDouble(s.next());
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    public double inputNum() {
        while (true) {
            try {
                return read();
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
            }
        }
    }

    //두번째 정수 입력받을 때 사용 num == 0, sign == / 인 상황을 잡기
    public boolean chkDivideZeroError(String sign, double num){
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
                }
            }
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
