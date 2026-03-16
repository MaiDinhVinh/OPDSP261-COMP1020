import linkedlist.LinkedList;
import linkedlist.Node;
import queue.ArrayQueue;
import queue.LinkedQueue;
import queue.PriorityQueue;
import stack.StackArray;

public class Main {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.enqueue(3);
        pq.enqueue(1);
        pq.enqueue(5);
        pq.enqueue(2);
        pq.enqueue(4);
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        for(int i: pq){
            System.out.println(i);
        }
    }
}
