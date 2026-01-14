package Step3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CalResult {
    //속성
    private Map<Integer, Point> hashMap = new HashMap<>();
    private int id;

    //생성자
    CalResult() {};

    //기능
    void compareNum(double rootNum){
        hashMap.values().stream()
                .filter(a->a.getRet()>rootNum)
                .forEach(a->printMethod(a));
    }

    void printMethod(Point a){
        System.out.println(a.getRet()+" (" + a.getTempRet() + ")");
    }


    void setResult(double a,double b,double ret, String sign){
        id++;
        String tempRet;
        if (sign.equals("/")){
            sign = "÷";
        }
        tempRet = a + " "+sign+" " + b + " = "+ret;

        hashMap.put(id, new Point(tempRet, ret));
    }

    public void getResult(){
        for (Integer i: hashMap.keySet()){
            System.out.println("id: "+i+" | result: "+hashMap.get(i).getTempRet());
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
