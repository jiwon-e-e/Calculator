package Step3;

import java.util.Scanner;
import java.util.function.BiFunction;

public class ArithmeticCalculator<T extends Number> {

    private Scanner s;
    //private 스캐너 변수 s 선언

    CalResult c = new CalResult();
    //계산 결과를 저장하는 CalResult 객체 선언

    public enum OperatorType{

        //sign 이 입력으로 들어오면 어떤 연산을 할건지 lambda 형식으로 정의
        PLUS ("+", (a,b) -> a + b),
        MINUS ("-", (a,b) -> a - b),
        TIMES ("*", (a,b) -> a * b),
        DIVIDE ("/", (a,b) -> a / b);

        //입력2 결과1 BiFunction : function 인터페이스를 받음
        BiFunction<Double,Double,Double> operatorType;
        String sign;
        //sign 을 나중에 op 로 반환해줘야 enum OperatorType에서는 연산을 정의만 해주고
        //계산하여 출력하고 저장하는 역할을 넘길 수 있음

        // 생성자
        OperatorType(String sign, BiFunction<Double,Double,Double> operatorType) {
            this.sign = sign;
            this.operatorType = operatorType;
        }

        public Double calculate(Double a, Double b) {
            return operatorType.apply(a,b);
        }
        //연산

        //String 을 받으면 해당 string이 뭔지 찾아서 Optype 형태로 반환
        static OperatorType getSign(String sign){
            for (OperatorType op : values()){
                if (op.sign.equals(sign)){
                    return op;
                }
            } //기호입력때 제대로 된 입력인지 확인하므로 필요없지만 혹시나 하니까 예외처리
            throw new IllegalArgumentException("기호 입력 실패");
        }
    }

    //생성자에서 스캐너 객체를 받아왔으므로 현재 클래스에서 사용할 수 있도록 함
    ArithmeticCalculator(Scanner s) {
        this.s = s;
    }

    //exit, list, 계산시작, find 을 판별해서 main 에 가져다주기
    public int start(){
        System.out.println("####\n계산 결과 조회: list\n계산 시작: (enter)\n종료: exit");
        System.out.println("(new!) 값 입력하여 해당 값보다 큰 결과값 조회 : find\n####");
        String methodSign = s.nextLine();
        if (methodSign.equalsIgnoreCase("exit")){
            System.out.println("계산기를 종료합니다.");
            return 1; //exit 이 입력되면 계산기 종료 메시지 출력 후 return 1 하여 메인에서도 종료할 수 있게 함
        }
        else if (methodSign.equalsIgnoreCase("list")){
            System.out.println("계산 결과를 조회합니다.");
            c.getResult(); //저장된 결과를 보여주고
            ifRemove(); // 만약 삭제하고싶다면 id를 입력할 수 있게 만들기
            // 삭제 자체는 다시 rmResult 를 호출하여 Calculator 함수는 어떻게 삭제되는지 알 수 없음
            return 0;
        }
        else if (methodSign.equalsIgnoreCase("")){
            System.out.println("계산을 시작합니다.");
            ioHandler(); //인풋 메소드
            s.nextLine();
            return 0;
        }
        else if (methodSign.equalsIgnoreCase("find")){
            System.out.println("값을 입력해주세요. 입력된 값보다 큰 결과값을 조회합니다.");
            findNum(); //함수 호출하면 Calresult 클래스의 compareNum 메소드와 연결함
            return 0;
        }
        else{
            System.out.println("다시 입력해주세요.");
            s.nextLine();
            return -1;
        }
    }

    void findNum(){
        double rootNum = s.nextDouble(); //비교할 값을 입력하고
        c.compareNum(rootNum); //매개변수로 전달
    }

    //첫번째 정수, 기호, 두번째 정수 순서로 입력 (inputNum, inputSign 호출)받기, chkDivideZeroError 호출
    public void ioHandler(){
        System.out.print("첫번째 숫자 입력: ");
        Double firstNum = inputNum();
        String sign = inputSign();
        OperatorType op = OperatorType.getSign(sign);
        //연산을 수행할때 eNum을 사용해야하므로 OperatorType 변수에 값을 입력

        System.out.print("두번째 숫자 입력: ");
        Double secondNum;
        do{
            secondNum = inputNum();
        }while (chkDivideZeroError(sign, secondNum));
        //두번째 변수도 입력받음

        double ret = op.calculate(firstNum, secondNum); //eNum으로 돌아가서 calculate
        // double 형태 ret에 저장

        c.setResult(firstNum,secondNum,ret, sign); //결과를 hashMap 에 저장하는 함수 호출
        System.out.println(firstNum + " "+ sign +" "+ secondNum + " = "+ ret); //출력
    }

    //inputNum에 집어넣어도 되지만 일단.. 분리
    double read() {
        try {
            return Double.parseDouble(s.next());
            //int가 들어오든 double이 들어오든 사용할 수 있도록 string 형태로 받아와서 형변환
        } catch (NumberFormatException e) {
            //숫자 형식이 아닌 경우
            throw e;
        }
    }

    public double inputNum() {
        while (true) {
            try {
                return read(); //제대로된 숫자가 입력될때까지 read
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요."); //exception이 이쪽으로 던져지면
                // 오류메시지를 출력하고 while문을 이어서 실행
            }
        }
    }

    //두번째 정수 입력받을 때 사용 num == 0, sign == / 인 상황을 잡기
    public boolean chkDivideZeroError(String sign, double num){
        //기호와 num (여기선 secondNum) 을 매개변수로 하여 조건문 실행
        if (sign.equals("/")&&(num == 0)){
            System.out.println("0으로 나눌 수 없습니다.");
            return true;
        }else{
            return false; //결과에 따라 true false
        }
    }

    //원하는 sign 입력만 확인하기, exit list 등은 더 앞에서 확인하므로 연산기호만 입력받음
    public String inputSign(){
        String[] signList = {"+", "-", "x", "/"};
        while(true){
            System.out.print("기호 입력: ");
            String sign = s.next();
            //기호 리스트 내에 있는 입력인지 확인하고 return
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
                //반복문 마지막까지 확인하고 재입력 요청
            }
        }
    }

    //삭제하고싶으면 id를 입력받고 rmresult 호출
    public void ifRemove() {
        while (true) {
            System.out.print("계산 결과를 삭제하려면 해당 id를, 다시 입력 화면으로 돌아가려면 (enter)를 눌러주세요:");

            String chk = s.nextLine();
            if (chk.equalsIgnoreCase("")) {
                break; //삭제를 원하는게 아니라면 엔터
            } else {
                try { //숫자형태인지 확인
                    int id = Integer.parseInt(chk);
                    c.rmResult(id); //id를 매개변수로 해서 삭제
                } catch (NumberFormatException e) {
                    System.out.println("id만 입력해주세요.");
                }
            }
        }
    }
}
