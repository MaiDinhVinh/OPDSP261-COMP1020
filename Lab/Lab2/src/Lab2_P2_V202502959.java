import java.util.Scanner;

public class Lab2_P2_V202502959 {
    public static void main(String[] args) {
        //scanner wrapped in try-with-resources so it closes automatically
        try(Scanner sc = new Scanner(System.in)){
            int a = sc.nextInt();

            //use switch expression to map numeric grade to letter grade
            System.out.println(switch(a){
                case 4 -> "A";
                case 3 -> "B";
                case 2 -> "C";
                case 1 -> "D";
                case 0 -> "F";
                default -> "Invalid"; //handle values outside valid grade range
            });
        }catch(Exception e){
            //handle case when input is not an integer
            System.out.println("Invalid");
        }
    }
}