import java.util.Scanner;

public class Lab2_P3_V202502959{
    public static void main(String[] args) {
        //scanner wrapped in try-with-resources so it closes automatically
        try(Scanner sc = new Scanner(System.in)){
            int n = sc.nextInt();

            //validate input limit
            if(n <= 0){
                System.out.println("Error: Limit must be greater than 0.");
            }else{
                int sum = 0;

                //iterate from 1..n and accumulate numbers divisible by 3 or 5
                for(int i = 1; i <= n; i++){
                    //add i if divisible by 3 or 5, otherwise add 0
                    sum += i % 3 == 0 || i % 5 == 0 ? i : 0;
                }

                //print final sum
                System.out.println(sum);
            }
        }catch(Exception e){
            //handle non-integer input
            System.out.println("Error: Limit must be an integer.");
        }
    }
}