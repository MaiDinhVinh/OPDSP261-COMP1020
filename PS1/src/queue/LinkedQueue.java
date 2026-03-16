package queue;
import linkedlist.LinkedList;
import linkedlist.Node;

import java.util.Iterator;

//Just the name of a Queue that is backed by a LinkedList
public class LinkedQueue implements Iterable<Integer>{
    private LinkedList ll = new LinkedList();

    public void enqueue(Integer i){
        Node n = new Node(i);
        this.ll.addToBack(n);
    }

    public void dequeue(){
        this.ll.removeFirst();
    }

    public Integer peek(){
        return this.ll.head.value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return this.ll.iterator();
    }
}
