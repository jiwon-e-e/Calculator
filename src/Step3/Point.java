package Step3;

class Point {
    //hashMap value 로 사용
    //의미없긴 하지만 나중에 Point 에 다시 접근할수도 있으니까 캡슐화도 해주기
    private String tempRet; //연산식
    private double ret; //결과값 double

    public Point (String tempRet, double ret){
        this.tempRet= tempRet;
        this.ret = ret;
    }
    //getter
    public String getTempRet(){
        return tempRet;
    }
    //setter
    public double getRet(){
        return ret;
    }
}