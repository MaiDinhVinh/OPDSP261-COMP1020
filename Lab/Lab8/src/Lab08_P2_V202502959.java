import java.util.Scanner;

public class Lab08_P2_V202502959 {

    static int[] heap = new int[100005];
    static int size = 0;

    // bubble up the last inserted element until heap property is satisfied
    static void insert(int val) {
        heap[size] = val;
        int i = size;
        size++;

        // swap with parent if strictly greater — keep going until root or no swap needed
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[i] > heap[parent]) {
                int tmp = heap[i];
                heap[i] = heap[parent];
                heap[parent] = tmp;
                i = parent;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            for (int j = 0; j < N; j++) {
                insert(sc.nextInt());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < size; j++) {
            if (j > 0) sb.append(" ");
            sb.append(heap[j]);
        }
        System.out.println(sb);
    }
}