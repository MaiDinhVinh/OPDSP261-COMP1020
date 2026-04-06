import java.util.Comparator;
import java.util.Scanner;

public class Lab6_P1_V202502959 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            sc.nextLine();
            Student[] arr = new Student[num];
            for (int i = 0; i < num; i++) {
                arr[i] = new Student(sc.nextInt(), sc.next(), sc.nextDouble());
            }

            // sort by GPA descending first, then by ID ascending as tiebreaker
            Comparator<Student> c = Comparator.comparing(Student::getGpa)
                    .reversed()
                    .thenComparingInt(Student::getId);

            Sort.mergeSort(arr, 0, arr.length - 1, c);

            for (Student s : arr) {
                System.out.println(s);
            }
        }
    }
}

class Student {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() { return id; }
    public double getGpa() { return gpa; }
    public String getName() { return name; }

    // format: "ID Name GPA" with GPA rounded to 1 decimal place
    @Override
    public String toString() {
        return "%d %s %.1f".formatted(this.id, this.name, this.gpa);
    }
}

class Sort {

    // standard merge sort — splits array in half recursively, then merges back
    public static void mergeSort(Student[] arr, int left, int right, Comparator<Student> c) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, c);
        mergeSort(arr, mid + 1, right, c);
        merge(arr, left, mid, right, c);
    }

    private static void merge(Student[] arr, int left, int mid, int right, Comparator<Student> c) {
        Student[] temp = new Student[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // pick the "smaller" element according to comparator each time
        // using <= 0 here is what makes this sort stable —
        // left-side elements come first when both sides are equal
        while (i <= mid && j <= right) {
            if (c.compare(arr[i], arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // drain whatever's left in either half
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}