import java.util.Scanner;

public class Lab6_P4_V202502959 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            sc.nextLine(); // consume leftover newline after reading num
            String[] arr = new String[num];
            for (int i = 0; i < num; i++) {
                arr[i] = sc.nextLine();
            }

            quickSort(arr, 0, arr.length - 1);

            for (String str : arr) {
                System.out.println(str);
            }
        }
    }

    public static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            // partition returns the final position of the pivot,
            // then we recursively sort the two halves around it
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high]; // always pick last element as pivot
        int i = low - 1;         // i tracks the boundary of the "less than pivot" region

        for (int j = low; j < high; j++) {
            // move element to left side if:
            // - shorter than pivot (primary criterion), or
            // - same length but lexicographically <= pivot (secondary criterion)
            if (arr[j].length() < pivot.length() ||
                    (arr[j].length() == pivot.length() && arr[j].compareTo(pivot) <= 0)) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // place pivot into its correct sorted position
        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}