import java.util.PriorityQueue;
import java.util.Scanner;

public class Lab08_P4_V202502959 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            // PriorityQueue is MinHeap by default — root is always the smallest
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                minHeap.offer(sc.nextInt());

                // keep only K largest — kick out the smallest if heap exceeds K
                if (minHeap.size() > K) {
                    minHeap.poll();
                }
            }

            // root of the heap = smallest among K largest = K-th largest overall
            System.out.println(minHeap.peek());
        }
    }
}