import java.util.Scanner;
class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your full name: ");
        String name = sc.nextLine();
        System.out.print("Enter your Student ID: ");
        String studentId = sc.nextLine();
        System.out.println("---------------------------------");
        System.out.println("Welcome to Java Lab 1!");
        String welcome1 = String.format("Student: %s", name);
        System.out.println(welcome1);
        System.out.format("ID: %s%n", studentId);
        System.out.println("---------------------------------");
    }
}