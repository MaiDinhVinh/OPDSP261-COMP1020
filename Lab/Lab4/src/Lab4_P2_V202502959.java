import java.util.Scanner;
import java.util.ArrayList;
public class Lab4_P2_V202502959 {
    public static void main(String[] args) {
        // try-with-resources so scanner closes by itself
        try(Scanner sc = new Scanner(System.in)){
            ArrayList arr = new ArrayList();
            int num = sc.nextInt();

            // read exactly num employees
            for(int i = 0; i < num; i++){
                String empType = sc.next();
                double salary = sc.nextDouble();
                double bonus = sc.nextDouble();
                int hours = sc.nextInt();
                int projects = sc.nextInt();

                // only create object if all input values stay inside allowed range
                if((salary >= 0 && salary <= 100000) && (bonus >= 0 && bonus <= 10000) && (hours >= 0 && hours <= 60) && (projects >= 0 && projects <= 100)){
                    if(empType.equals("fulltime")){
                        FullTimeEmployeee fe = new FullTimeEmployeee(salary, bonus, projects);
                        arr.add(fe);
                    }else if(empType.equals("contractor")){
                        Contractor con = new Contractor(projects);
                        arr.add(con);
                    }else {
                        // store null so output loop can print invalid employee type later
                        arr.add(null);
                    }
                }else{
                    // invalid data also gets stored as null for later handling
                    arr.add(null);
                }
            }

            // use pattern matching to detect actual object type before printing
            for(Object obj: arr){
                if(obj instanceof FullTimeEmployeee fe){
                    System.out.printf("%s %.1f %.1f %d %d%n", "fulltime", fe.getSalary(), fe.getBonus(), fe.getHoursPerWeek(), fe.getProjectCount());
                }else if(obj instanceof Contractor con){
                    System.out.printf("%s %.1f %.1f %d %d%n", "contractor", con.getSalary(), con.getBonus(), con.getHoursPerWeek(), con.getProjectCount());
                }else{
                    System.out.println("Invalid employee type.");
                }
            }
        }catch(Exception e){
            // bad input format anywhere ends up here
            System.out.println("Invalid employee type.");
        }
    }
}

interface Payable{
    double getSalary();
    double getBonus();
}

interface Workable{
    int getHoursPerWeek();
    int getProjectCount();
}

class FullTimeEmployeee implements Payable, Workable{

    private double salary;
    private double bonus;
    private int projects;

    public FullTimeEmployeee(double salary, double bonus, int projects){
        this.salary = salary;
        this.bonus = bonus;
        this.projects = projects;
    }

    @Override
    public double getSalary() {
        // fulltime salary is reduced by 15%
        return this.salary * (1 - 0.15);
    }

    @Override
    public double getBonus() {
        // each project increases bonus by 1%
        return bonus * (1 + projects * 0.01);
    }

    @Override
    public int getHoursPerWeek() {
        return 40;
    }

    @Override
    public int getProjectCount() {
        return this.projects;
    }
}

class Contractor implements Payable, Workable{

    private int projects;

    public Contractor(int projects){
        this.projects = projects;
    }

    @Override
    public double getSalary() {
        // contractor pay is based fully on number of projects
        return this.projects * 500;
    }

    @Override
    public double getBonus() {
        return this.projects * 100;
    }

    @Override
    public int getHoursPerWeek() {
        // more projects means more working hours
        return this.projects * 5;
    }

    @Override
    public int getProjectCount() {
        return this.projects;
    }
}