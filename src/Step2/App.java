package Step2;

public class App {
    public static void main(String[] args) {
        Calculator c = new Calculator();

        while(true){
            int methodSign = c.start();
            if (methodSign == 1) break;
        }
    }
}
