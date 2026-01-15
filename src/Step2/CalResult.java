package Step2;

import java.util.HashMap;
import java.util.Map;

class CalResult {
    //속성
    private Map<Integer, String> hashMap = new HashMap<>();
    private int id;
    // id와 hashMap을 외부에서 접근할 수 없도록 캡슐화  해주기

    //생성자
    CalResult() {};
    //선언해줄필요 없긴한데 일단 함

    //기능
    //setter 함수
    void setResult(int a,int b,double ret, String sign){
        id++; //상태 id를 1씩 올리며 값 저장
        // 삭제하더라도 id 값은 올라가기만 하도록
        String tempRet; //String 형태로 계산 결과를 바꾸어서 저장하고 싶어서 지역변수 생성
        //sign이 혹시라도 /로 들어오면 바꿔주는 부분이였는데 안 써도 됨 이제
//        if (sign.equals("/")){
//            sign = "÷";
//        }
        if(ret%1==0){ //결과값이 정수면
            ret =(int) ret; //.0을 없애주기위해 int로 형변환
        }

        tempRet = a + " "+sign+" " + b + " = "+ret;
         //tempRet 을 각각 저장 후
        hashMap.put(id, tempRet); //id와 함께 hashMap 에 저장
    }

    void getResult(){
        for (Integer i: hashMap.keySet()){ //key로 반복문을 순회하며
            System.out.println("id: "+i+" | result: "+hashMap.get(i)); //id - result 형태로 출력
        }
    }

    public void rmResult(int id){
        //id를 매개변수로 하여
        if (hashMap.containsKey(id)){ //만약에 id 가 hashMap 에 존재한다면
            System.out.println(id+"번 계산 결과를 삭제합니다.");
            hashMap.remove(id); //빈계산결과 삭제
        }else{
            System.out.println("존재하지 않는 id 입니다. "); //아니라면 존재 x 메시지 출력 후 재입력 기다림 (id or 마저계산)
        }
    }
}
