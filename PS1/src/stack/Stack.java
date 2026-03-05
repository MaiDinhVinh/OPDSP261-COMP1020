package stack;

import linkedlist.LinkedList;
import linkedlist.Node;

import java.util.Iterator;

public class Stack implements Iterable<Integer>{

    private LinkedList ll = new LinkedList();

    public void push(Integer i){
        Node n = new Node(i);
        this.ll.addToFront(n);
    }

    public Integer peek(){
        return this.ll.head.value;
    }

    public void pop(){
        this.ll.removeFirst();
    }

    @Override
    public Iterator<Integer> iterator() {
        return this.ll.iterator();
    }
}
