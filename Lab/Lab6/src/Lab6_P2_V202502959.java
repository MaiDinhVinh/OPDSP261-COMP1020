import java.util.Scanner;
import java.util.ArrayList;

public class Lab6_P2_V202502959 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ArrayList<Task> tasks = new ArrayList<>();
            String name = "";

            // read tasks line by line until we hit the "#" terminator
            while (!((name = sc.next()).equals("#"))) {
                int dl = sc.nextInt();
                tasks.add(new Task(name, dl));
            }

            Sorting.insertionSort(tasks);

            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }
}

class Task {
    private String name;
    private int deadline;

    public Task(String name, int deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    public String getName() { return name; }
    public int getDeadline() { return deadline; }

    @Override
    public String toString() {
        return "%s %d".formatted(this.name, this.deadline);
    }
}

class Sorting {

    public static void insertionSort(ArrayList<Task> tasks) {
        int n = tasks.size();

        for (int i = 1; i < n; i++) {
            Task key = tasks.get(i);
            int j = i - 1;

            // shift elements with strictly greater deadline one spot to the right
            // using strictly > (not >=) keeps equal-deadline tasks in original order — stable sort
            while (j >= 0 && tasks.get(j).getDeadline() > key.getDeadline()) {
                tasks.set(j + 1, tasks.get(j));
                j--;
            }

            tasks.set(j + 1, key);
        }
    }
}