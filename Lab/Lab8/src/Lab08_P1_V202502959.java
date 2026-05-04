import java.util.Scanner;

public class Lab08_P1_V202502959 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            int i = sc.nextInt();

            // parent is (i-1)/2, but root has no parent
            int parent = (i == 0) ? -1 : (i - 1) / 2;

            // left = 2i+1, right = 2i+2 — output -1 if out of bounds
            int left  = (2 * i + 1 < N) ? 2 * i + 1 : -1;
            int right = (2 * i + 2 < N) ? 2 * i + 2 : -1;

            System.out.println(parent + " " + left + " " + right);
        }
    }
}