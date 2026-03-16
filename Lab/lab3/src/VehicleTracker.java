import java.util.Scanner;
public class VehicleTracker {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int carCount = sc.nextInt();
            Car[] cars = new Car[carCount];
            for(int i = 0; i < carCount; i++){
                cars[i] = new Car(sc.nextDouble(), sc.nextDouble());
            }
            int bicycleCount = sc.nextInt();
            Bicycle[] bicycles = new Bicycle[bicycleCount];
            for(int i = 0; i < bicycleCount; i++){
                bicycles[i] = new Bicycle(sc.nextDouble(), sc.nextDouble());
            }
            for(Car c: cars){
                System.out.println(c.printInfo());
            }
            for(Bicycle b: bicycles){
                System.out.println(b.printInfo());
            }
            System.out.printf("%d vehicles created", Vehicle.createdCount);
        }
    }
}
abstract class Vehicle{
    protected double speed;
    protected double time;
    public static int createdCount;

    public Vehicle(double speed, double time){
        this.speed = speed;
        this.time = time;
        createdCount++;
    }

    public double getDistance(){
        return speed * time;
    }

    public abstract String printInfo();
}

class Car extends Vehicle{

    public Car(double speed, double time){
        super(speed, time);
    }

    @Override
    public String printInfo() {
        return String.format("Car travelling at %.1f km/h%nDistance: %.1f", this.speed, this.getDistance());
    }
}

class Bicycle extends Vehicle{

    public Bicycle(double speed, double time){
        super(speed, time);
    }

    @Override
    public String printInfo() {
        return String.format("Bicycle travelling at %.1f km/h%nDistance: %.1f", this.speed, this.getDistance());
    }
}
