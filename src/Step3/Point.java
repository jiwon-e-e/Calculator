package Step3;

class Point {
    private String tempRet;
    private double ret;

    public Point (String tempRet, double ret){
        this.tempRet= tempRet;
        this.ret = ret;
    }

    public String getTempRet(){
        return tempRet;
    }

    public double getRet(){
        return ret;
    }
}