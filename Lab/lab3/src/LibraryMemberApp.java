import java.util.Scanner;
public class LibraryMemberApp {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            String city = sc.nextLine();
            String section = sc.nextLine();
            String name = sc.nextLine();
            int memberId = sc.nextInt();
            double fee = sc.nextDouble();
            sc.nextLine();
            String updatedLibName = sc.nextLine();
            Member mem = new Member(city, section, name, memberId, fee);
            Library.libraryName = updatedLibName;
            mem.printLibraryInfo();
            mem.printMemberInfo();
            System.out.println(mem.displayMemberType());
            mem.printMembershipFee();
        }
    }
}

abstract class Library{
    public static String libraryName = "Central Library";
    protected String city;
    protected String section;

    public Library(String city, String section){
        this.city = city;
        this.section = section;
    }

    public void printLibraryInfo(){
        System.out.printf("Library: %s%nCity: %s%nSection: %s%n", Library.libraryName, this.city, this.section);
    }

    public abstract String displayMemberType();
}

interface FeeInfo{
    void printMembershipFee();
}

class Member extends Library implements FeeInfo{

    private String name;
    private int memberId;
    private double membershipFee;

    public Member(String city, String section, String name, int memeberId, double membershipFee){
        super(city, section);
        this.name = name;
        this.memberId = memeberId;
        this.membershipFee = membershipFee;
    }


    @Override
    public void printMembershipFee() {
        System.out.printf("Membership Fee: %.1f", this.membershipFee);
    }

    @Override
    public String displayMemberType() {
        return "Membership Type: Regular Member";
    }

    public void printMemberInfo(){
        System.out.printf("Member Name: %s%nMember ID: %d%n", this.name, this.memberId);
    }
}
