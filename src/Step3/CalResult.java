package Step3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CalResult {
    //속성
    private Map<Integer, Point> hashMap = new HashMap<>();
    //hashMap 에 원래 value 로 String 을 저장했었는데 이번엔 값을 비교하는 메소드가
    //추가되기 때문에 Point class 로 하여 string 과 double 값을 둘 다 저장
    //1/14TIL 2번 트러블슈팅
    private int id;
    // id와 hashMap을 외부에서 접근할 수 없도록 캡슐화  해주기
    //생성자
    CalResult() {};

    //기능
    // 입력값을 받아서 입력값보다 더 큰 결과값이 나온 hashMap 출력
    void compareNum(double rootNum){
        //hashMap은 values 로 바꾼 후 stream 화 가능 1/14TIL 4번 트러블슈팅
        hashMap.values().stream() //stream으로 만들고
                .filter(a->a.getRet()>rootNum) //filter + lambda 로 크기비교
                .forEach(a->printMethod(a)); //뽑아낸것만 출력
    }

    void printMethod(Point a){
        System.out.println(a.getRet()+" (" + a.getTempRet() + ")");
    }
    //forEach가 매개변수를 기준으로 함수를 실행시키는거라 따로 빼줌
    //result 값이 캡슐화 되어있기에 getter 사용

    void setResult(double a,double b,double ret, String sign){
        id++;
        String tempRet;
        //여기선 sign 이 예쁘게 안 바뀌어 오기때문에 재확인
        if (sign.equals("/")){
            sign = "÷";
        }
        //소숫점 떼주기
        if (a%1==0) a= (int)a;
        if (b%1==0) b= (int)b;
        if (ret%1==0) ret= (int)ret;

        tempRet = a + " "+sign+" " + b + " = "+ret;
        //연산식과 결과값을 각각 String, Double 형태로 hashMap value 로 put
        hashMap.put(id, new Point(tempRet, ret));
    }

    //리스트 형태로 결과값 출력
    public void getResult(){
        for (Integer i: hashMap.keySet()){
            System.out.println("id: "+i+" | result: "+hashMap.get(i).getTempRet());
        }
    }
    //id를 매개변수로 하여 id 가 hashMap 에 존재한다면 삭제
    public void rmResult(int id){
        if (hashMap.containsKey(id)){
            System.out.println(id+"번 계산 결과를 삭제합니다.");
            hashMap.remove(id);
        }else{
            System.out.println("존재하지 않는 id 입니다. ");
        }
    }
}
