package Step3;

public class App {
    public static void main(String[] args) {
        Step3.Calculator<Double> cal = new Calculator<>();

        while(true){
            int methodSign = cal.start();
            if (methodSign == 1) break;
        }
    }
}
