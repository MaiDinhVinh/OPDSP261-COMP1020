import linkedlist.LinkedList;
import linkedlist.Node;

public class Main {
    public static void main(String[] args) {
        Node n1 = new Node(3);
        Node n2 = new Node(5);
        Node n3 = new Node(7);
        LinkedList ll = new LinkedList();
        ll.addToBack(n1);
        ll.addToBack(n2);
        ll.addToFront(n3);
        ll.remove(Integer.valueOf(5));
        Integer[] arr = ll.toArray(new Integer[0]);
        for(int i: arr){
            System.out.println(i);
        }
    }
}
