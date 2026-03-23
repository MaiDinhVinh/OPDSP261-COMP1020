import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Lab4_P3_V202502959 {
    public static void main(String[] args) {
        // try-with-resources so scanner closes automatically
        try(Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            Container c = new Container(new ArrayList<>());
            for(int i = 0; i < num; i++){
                Item item = new Item(sc.next(), sc.nextDouble());
                c.addItem(item);
            }
            // sort items by numeric value before printing
            c.sortByValue();
            c.display();
        }
    }
}

class Item<T extends Number>{
    private String name;
    private T value;

    public Item(String name, T value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return this.name;
    }

    public double getDoubleValue(){
        // this casts generic value back to Double so it can be used as a primitive double
        return (Double)this.value;
    }
}

class Container{
    // raw type again, so java treats stored elements like Item without specific generic info
    private ArrayList<Item> items;

    public Container(ArrayList<Item> items){
        this.items = items;
    }

    public void addItem(Item i){
        this.items.add(i);
    }

    public void sortByValue(){
        Collections.sort(this.items, new Comparator<>() {
            @Override
            public int compare(Item i1, Item i2) {
                // compare by numeric value so smaller one comes first
                return (int)(i1.getDoubleValue() - i2.getDoubleValue());
            }
        });
    }

    public void display(){
        for(Item i: this.items){
            System.out.printf("%s %.2f%n", i.getName(), i.getDoubleValue());
        }
    }
}