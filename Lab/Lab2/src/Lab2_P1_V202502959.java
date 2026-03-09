import java.util.Scanner;

public class Lab2_P1_V202502959 {
    public static String coordinateGeo(double x, double y){
        //determine which quadrant the point (x,y) belongs to using nested ternary conditions
        return x > 0 && y > 0 ? "Quadrant 1" :
                (x < 0 && y > 0 ? "Quadrant 2" :
                        (x < 0 && y < 0 ? "Quadrant 3" :
                                (x > 0 && y < 0 ? "Quadrant 4" : "On Axis/Origin")));
    }

    public static void main(String[] args) {
        //scanner wrapped in try-with-resources so it is automatically closed
        try(Scanner sc = new Scanner(System.in)){
            double a = sc.nextDouble();
            double b = sc.nextDouble();

            System.out.println(coordinateGeo(a, b));
        }catch(Exception e){
            //handle invalid input if user enters non-numeric values
            System.out.println("Error: X and Y coordinates must be numbers.");
        }
    }
}