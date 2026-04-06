import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Lab5_P2_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            Queue<String> q = new LinkedList<>();
            int input = sc.nextInt();
            if(input == 0){
                System.out.println(0);
            }
            // enqueue the first binary string
            q.offer("1");
            for(int i = 0; i < input; i++){
                String bin = "";
                // dequeue the front element and print it
                System.out.print(bin += q.poll());
                System.out.print(" ");
                // append 0 and 1 to the current string and enqueue the new elements
                q.offer(bin + 0);
                q.offer(bin + 1);
            }
        }catch(Exception e){
        }
    }
}