import java.util.Scanner;

public class Lab6_P3_V202502959 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            Product[] products = new Product[num];
            for (int i = 0; i < num; i++) {
                products[i] = new Product(sc.next(), sc.nextInt());
            }

            int swapCount = bubbleSort(products);

            for (Product p : products) {
                System.out.println(p);
            }
            // print swap count after the sorted list
            System.out.println("Swaps: %d".formatted(swapCount));
        }
    }

    public static int bubbleSort(Product[] arr) {
        int n = arr.length;
        int swapCount = 0;

        // outer loop: n-1 passes total, each pass guarantees one more element is in place
        for (int i = 0; i < n - 1; i++) {
            // each pass bubbles the largest unsorted element to the end,
            // so we shrink the inner loop by i each time
            for (int j = 0; j < n - i - 1; j++) {
                // strictly > keeps equal-price elements in original order — stable sort
                if (arr[j].getPrice() > arr[j + 1].getPrice()) {
                    // swap adjacent elements
                    Product temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                }
            }
        }

        return swapCount;
    }
}

class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    // format: "name price"
    @Override
    public String toString() {
        return "%s %d".formatted(this.name, this.price);
    }
}