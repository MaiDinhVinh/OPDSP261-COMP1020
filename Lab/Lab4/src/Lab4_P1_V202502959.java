import java.util.Scanner;
public class Lab4_P1_V202502959 {
    public static void main(String[] args) {
        // try-with-resources so scanner gets closed automatically at the end
        try(Scanner sc = new Scanner(System.in)){
            String transportation = sc.next();
            double distanceKm = sc.nextDouble();

            // distance must be positive first before checking trip details
            if(distanceKm > 0){
                if(transportation.equals("train")){
                    int seatClass = sc.nextInt();

                    // only class 1 or 2 is allowed for train
                    if(seatClass >= 1 && seatClass <= 2){
                        TrainTrip tt = new TrainTrip(distanceKm, seatClass);
                        // print type, fare, and carbon in one line with 2 decimal places
                        System.out.printf("%s %.2f %.2f%n", tt.getType(), tt.getFare(), tt.getCarbon());
                    }else{
                        System.out.println("Invalid input.");
                    }
                }else if(transportation.equals("flight")){
                    double luggageKg = sc.nextDouble();

                    // luggage cannot be negative
                    if(luggageKg >= 0){
                        FlightTrip ft = new FlightTrip(distanceKm, luggageKg);
                        // print type, fare, and carbon in one line with 2 decimal places
                        System.out.printf("%s %.2f %.2f%n", ft.getType(), ft.getFare(), ft.getCarbon());
                    }else{
                        System.out.println("Invalid input.");
                    }
                }else{
                    // only train or flight is accepted
                    System.out.println("Invalid input.");
                }
            }else{
                System.out.println("Invalid input.");
            }
        }catch(Exception e){
            // if input format is wrong or missing, just print invalid input
            System.out.println("Invalid input.");
        }
    }
}

abstract class Trip{
    public abstract double getFare();

    public abstract double getCarbon();

    public abstract String getType();
}

class TrainTrip extends Trip{
    private double distanceKm;
    private int seatClass;

    public TrainTrip(double distanceKm, int seatClass){
        this.distanceKm = distanceKm;
        this.seatClass = seatClass;
    }

    @Override
    public double getFare() {
        // class 1 is cheaper, class 2 costs more
        if(this.seatClass == 1){
            return 0.90 * this.distanceKm;
        }else{
            return 1.40 * this.distanceKm;
        }
    }

    @Override
    public double getCarbon() {
        return 0.05 * this.distanceKm;
    }

    @Override
    public String getType() {
        return "train";
    }
}

class FlightTrip extends Trip{

    private double distanceKm;
    private double luggageKg;

    public FlightTrip(double distanceKm, double luggageKg){
        this.distanceKm = distanceKm;
        this.luggageKg = luggageKg;
    }

    @Override
    public double getFare() {
        // flight fare = base price + distance price + extra luggage fee over 15kg
        return 50.0 + 0.35 * this.distanceKm + 2.0 * Math.max(0.0, this.luggageKg - 15.0);
    }

    @Override
    public double getCarbon() {
        // carbon depends on both distance and luggage
        return 0.18 * distanceKm + 0.04 * luggageKg;
    }

    @Override
    public String getType() {
        return "flight";
    }
}