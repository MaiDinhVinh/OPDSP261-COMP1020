import java.util.Scanner;

public class Lab08_P3_V202502959 {

    static int[] arr;
    static int size;

    // heapify down from index i — used both in build and extraction phase
    static void heapifyDown(int i) {
        while (true) {
            int largest = i;
            int left  = 2 * i + 1;
            int right = 2 * i + 2;

            if (left  < size && arr[left]  > arr[largest]) largest = left;
            if (right < size && arr[right] > arr[largest]) largest = right;

            if (largest == i) break;

            // swap and continue down
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            i = largest;
        }
    }

    // build maxheap in-place using bottom-up approach — O(n) instead of O(n log n)
    static void buildMaxHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    static void heapSort() {
        buildMaxHeap();

        // each iteration: pull max to the end, shrink heap, fix the root
        while (size > 1) {
            int tmp = arr[0];
            arr[0] = arr[size - 1];
            arr[size - 1] = tmp;
            size--;
            heapifyDown(0);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            arr = new int[N];
            size = N;
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
        }

        heapSort();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}