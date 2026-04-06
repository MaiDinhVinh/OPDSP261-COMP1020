import java.util.Scanner;
public class Lab5_P4_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int count = sc.nextInt();
            sc.nextLine();
            SinglyLinkedList sl = new SinglyLinkedList();
            for(int i = 0; i < count; i++){
                String command = sc.next();
                // process each queue operation based on the command string
                switch(command){
                    case "enqueue":
                        int input = sc.nextInt();
                        sl.add(input);
                        break;
                    case "dequeue":
                        System.out.println(sl.remove());
                        break;
                    case "front":
                        System.out.println(sl.peek());
                        break;
                    default:
                        System.out.println(sl.isEmpty());
                }
            }
        }
    }
}

// custom node class to store value and the next pointer
class Node{
    private int value;
    private Node next;

    public Node(int value){
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }
}

// custom queue implementation using a singly linked list
class SinglyLinkedList{
    private Node head;
    private Node tail;
    private int size;

    public void add(int i){
        // enqueue operation to insert a new node at the rear
        Node n = new Node(i);
        if(this.head == null && this.tail == null){
            this.head = this.tail = n;
        }else{
            Node current = this.head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(n);
            this.tail = n;
        }
        this.size++;
    }

    public Integer remove() {
        // dequeue operation to remove and return the front element
        if(this.size > 0){
            Node n = this.head;
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

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    public Integer peek(){
        // front operation to retrieve the head value without removing it
        return this.head == null ? null : this.head.getValue();
    }
}