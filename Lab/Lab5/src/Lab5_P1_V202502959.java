import java.util.Stack;
import java.util.Scanner;
public class Lab5_P1_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            Stack<Integer> s = new Stack<>();
            int i = sc.nextInt();
            if(i == 0){
                System.out.println("0");
            }else{
                // repeatedly divide by 8, push remainder onto stack, and update to quotient
                while(i != 0){
                    s.push(i % 8);
                    i /= 8;
                }
                String result = "";
                // pop all elements from the stack to form the octal representation
                while(!s.isEmpty()){
                    result += s.pop();
                }
                System.out.println(result);
            }
        }catch(Exception e){
        }
    }
}