package Step2;

import java.util.HashMap;
import java.util.Map;

class CalResult {
    //속성
    private Map<Integer, String> hashMap = new HashMap<>();
    private int id;

    //생성자
    CalResult() {};

    //기능
    void setResult(int a,int b,double ret, String sign){
        id++;
        String tempRet;
        if (sign.equals("/")){
            sign = "÷";
        }
        if(ret%1==0){
            int intRet =(int) ret;
            tempRet = a + " "+sign+" " + b + " = "+intRet;
        }else{
            tempRet = a + " "+sign+" " + b + " = "+ret;
        }
        hashMap.put(id, tempRet);
    }

    void getResult(){
        for (Integer i: hashMap.keySet()){
            System.out.println("id: "+i+" | result: "+hashMap.get(i));
        }
    }

    public void rmResult(int id){
        if (hashMap.containsKey(id)){
            System.out.println(id+"번 계산 결과를 삭제합니다.");
            hashMap.remove(id);
        }else{
            System.out.println("존재하지 않는 id 입니다. ");
        }
    }
}
