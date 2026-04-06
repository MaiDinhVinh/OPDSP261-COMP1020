import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Lab5_P3_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int k = sc.nextInt();
            sc.nextLine();
            String inp = sc.nextLine();
            try(Scanner scan = new Scanner(inp)){
                // initialize a min-heap to store the k largest elements
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                while(scan.hasNextInt()){
                    pq.offer(scan.nextInt());
                    // remove the smallest element if the heap size exceeds k
                    if(pq.size() > k){
                        pq.poll();
                    }
                }
                // print the root of the heap which is the k-th largest element
                System.out.println(pq.poll());
            }catch(Exception ex){
            }
        }catch(Exception e){
        }
    }
}