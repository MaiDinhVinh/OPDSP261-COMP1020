import java.util.Scanner;
public class SmartDeviceApp {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int fansCount = sc.nextInt();
            SmartFan[] fans = new SmartFan[fansCount];
            for(int i = 0; i < fansCount; i++){
                fans[i] = new SmartFan(sc.nextDouble(), sc.nextDouble());
            }
            int smartLightCount = sc.nextInt();
            SmartLight[] lights = new SmartLight[smartLightCount];
            for(int i = 0; i < smartLightCount; i++){
                lights[i] = new SmartLight(sc.nextDouble(), sc.nextDouble());
            }
            for(SmartFan f: fans){
                f.printInfo();
                System.out.printf("Energy usage: %.1f%n" ,f.calculateEnergy());
            }
            for(SmartLight l: lights){
                l.printInfo();
                System.out.printf("Energy usage: %.1f%n" ,l.calculateEnergy());
            }
            System.out.printf("%d devices created", Device.deviceCount);
        }
    }
}

abstract class Device{
    protected String name;
    protected double power;
    protected double hours;

    public static int deviceCount;

    public Device(){
        this(0, 0);
        deviceCount++;
    }

    public Device(double power, double hours){
        this.power = power;
        this.hours = hours;
        deviceCount++;
    }

    public double calculateEnergy(){
        return this.power * this.hours;
    }

    public int getDeviceCount(){
        return Device.deviceCount;
    }

    public abstract void printInfo();
}

interface Switchable{
    void turnOn();
    void turnOff();
}

class SmartFan extends Device implements Switchable{
    public SmartFan(double power, double hours){
        super(power, hours);
        this.name = "Smart Fan";
    }

    @Override
    public void printInfo() {
        System.out.printf("%s running at %.1f watts%n", this.name, this.power);
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }
}

class SmartLight extends Device implements Switchable{

    public SmartLight(double power, double hours){
        super(power, hours);
        this.name = "Smart Light";
    }

    @Override
    public void printInfo() {
        System.out.printf("%s running at %.1f watts%n", this.name, this.power);
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }
}
