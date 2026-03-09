import java.util.Scanner;

public class Lab2_P4_V202502959 {
    public static void main(String[] args) {
        //scanner wrapped in try-with-resources so it closes automatically
        try(Scanner sc = new Scanner(System.in)){
            int n = 0;
            int sum = 0;
            double attempt = 0;

            //keep reading numbers until a negative value appears
            while((n = sc.nextInt()) >= 0){
                sum += n;       //accumulate total sum
                attempt++;      //count how many valid inputs were entered
            }

            //compute average and check if result is NaN (happens when no valid inputs were entered)
            String result = Double.valueOf(sum/attempt).equals(Double.NaN) ?
                    "No data" : String.format("%.2f", sum/attempt);

            //print average or "No data"
            System.out.println(result);
        }catch(Exception e){
            //handle non-integer input in the stream
            System.out.println("Error: Input stream interrupted by non-integer data.");
        }
    }
}