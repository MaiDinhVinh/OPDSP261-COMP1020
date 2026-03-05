import linkedlist.LinkedList;
import linkedlist.Node;
import stack.StackArray;

public class Main {
    public static void main(String[] args) {
        StackArray sa = new StackArray();
        sa.push(1);
        sa.push(2);
        sa.push(3);
//        System.out.println(sa.peek());
        sa.pop();
        for(int i: sa){
            System.out.println(i);
        }
    }
}
