package queue;

import linkedlist.doublylinkedlist.DoublyLinkedList;
import linkedlist.doublylinkedlist.DoublyNode;

import java.util.Iterator;

public class PriorityQueue implements Iterable<Integer>{

    private DoublyLinkedList dl = new DoublyLinkedList();

    public void enqueue(int i){
        DoublyNode dn = new DoublyNode(i);
        if(this.dl.size == 0){
            this.dl.addToFront(dn);
        }else{
            DoublyNode current = this.dl.head;
            while(current != null && current.value > i){
                current = current.next;
            }
            if(current == null){
                this.dl.addToBack(dn);
            }else if(current.previous == null){
                this.dl.addToFront(dn);
            }else{
                dn.previous = current.previous;
                current.previous.next = dn;
                dn.next = current;
                current.previous = dn;
                this.dl.size++;
            }
        }
    }

    public void dequeue(){
        this.dl.removeFirst();
    }

    public int peek(){
        return this.dl.head.value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return this.dl.iterator();
    }
}
