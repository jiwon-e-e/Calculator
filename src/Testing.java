import java.util.Scanner;


public class Testing {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int sum = 0;

        while(true){
            int price = s.nextInt();
            sum+=price;

            if (price==0){
                break;
            }
        }
        System.out.println("sum= "+sum);

    }
}
