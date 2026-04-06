import java.util.Scanner;
import java.util.Stack;

public class Lab5_P5_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            String line = sc.nextLine();
            try(Scanner scan = new Scanner(line)){
                SinglyLinkedList2 sl = new SinglyLinkedList2();
                // populate the custom linked list with the input integers
                while(scan.hasNextInt()){
                    sl.add(scan.nextInt());
                }
                Node2 current = sl.getHead();
                Stack<Integer> s = new Stack<>();
                // traverse the linked list and push every node's value onto a stack
                while(current != null){
                    s.push(current.getValue());
                    current = current.getNext();
                }
                Node2 cur = sl.getHead();
                boolean palindrome = true;
                // traverse from the head again, popping elements from the stack to compare
                while(cur != null){
                    if(cur.getValue() == s.pop()){
                        cur = cur.getNext();
                    }else{
                        palindrome = false;
                        break;
                    }
                }
                // print YES if it is a palindrome, NO otherwise
                if(palindrome){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }
}

// custom node class for the singly linked list
class Node2{
    private int value;
    private Node2 next;

    public Node2(int value){
        this.value = value;
    }

    public Node2 getNext() {
        return next;
    }

    public void setNext(Node2 next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }
}

// custom linked list to hold the sequence of numbers
class SinglyLinkedList2{
    private Node2 head;
    private Node2 tail;
    private int size;

    public void add(int i){
        // append a new node to the tail of the list
        Node2 n = new Node2(i);
        if(this.head == null && this.tail == null){
            this.head = this.tail = n;
        }else{
            Node2 current = this.head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(n);
            this.tail = n;
        }
        this.size++;
    }

    public Integer remove() {
        if(this.size > 0){
            Node2 n = this.head;
            this.head = this.head.getNext();
            this.size--;
            if(this.size == 0){
                this.head = this.tail = null;
            }
            return n.getValue();
        }else{
            return null;
        }
    }

    public Node2 getHead() {
        return head;
    }

    public Node2 getTail() {
        return tail;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public Integer peek(){
        return this.head == null ? null : this.head.getValue();
    }
}