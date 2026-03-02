package linkedlist;

import linkedlist.doublylinkedlist.DoublyNode;

import java.util.*;

public class LinkedList implements Iterable<Integer>,
        List<Integer> {
    //Head node
    public Node head;

    //tail node
    public Node tail;

    public int size;

    public boolean addToFront(Node n){
        if(head == null && tail == null){
            head = tail = n;
        }else{
            n.next = head;
            head = n;
        }
        this.size++;
        return true;
    }

    public boolean addToBack(Node n){
        if(head == null && tail == null){
            head = tail = n;
        }else{
            tail.next = n;
            tail = n;
        }
        this.size++;
        return true;
    }

    public void removeFirst(){
        if(this.size != 0){
            if(this.size == 1){
                head = tail = null;
            }else{
                head = head.next;
            }
            this.size--;
        }
    }

    public void removeLast(){
        if(size != 0){
            if(this.size == 1){
                head = tail = null;
            }else{
                Node current = head;
                while(current.next != tail){
                    current = current.next;
                }
                current.next = null;
            }
            this.size--;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if(o instanceof Integer i){
            Node current = this.head;
            while(current != null){
                if(current.value == i){
                    return true;
                }else{
                    current = current.next;
                }
            }
            return false;
        }else{
            throw new IllegalArgumentException("Not an int !");
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){

            private Node n = LinkedList.this.head; //qualified this statement

            //for-each loop is essentially Iterator method but shorten => we need to implement these 2 methods
            @Override
            public boolean hasNext() {
                return this.n != null;
            }

            @Override
            public Integer next() {
                int value = this.n.value;
                this.n = this.n.next;
                return value;
            }
        };
    }

    @Override
    public Integer[] toArray() {
        Node current = this.head;
        Integer[] arr = new Integer[this.size];
        int index = 0;
        while(current != null){
            arr[index] = current.value;
            current = current.next;
        }
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Integer[] t = null;
        if(a.length == this.size){
             t = (Integer[])a;

        }else{
            t = new Integer[this.size];
        }
        Node current = null;
        for(int i = 0; i < this.size; i++){
            if(i == 0){
                t[i] = this.head.value;
                current = this.head.next;
            }else{
                t[i] = current.value;
                current = current.next;
            }
        }
        //we can be able to cast all types of arrays type (except primitives) to generic T[] because of type erasure
        return (T[])t;
    }

    @Override
    public boolean add(Integer integer) {
        Node n = new Node(integer);
        return this.addToFront(n);
    }

    @Override
    public boolean remove(Object o) {
        /*
        * There are 3 cases when deleting an object in LinkedList
        * 1: No element
        * 2: 1 element
        * 3: Many elements:
        *   a) Delete middle element
        *   b) Delete first or last element
        * */
        Node previous = null;
        Node current = this.head;

        while(current != null){
            if(o instanceof Integer integer){
                if(current.value == integer){
                    if(previous == null){
                        this.removeFirst();
                    }else{
                        if(current.next == null){
                            this.removeLast();
                        }else{
                            previous.next = current.next;
                            this.size--;
                        }
                    }
                    return true;
                }else{
                    previous = current;
                    current = current.next;
                }
            }else{
                throw new IllegalArgumentException("Not an int !");
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return (ListIterator<Integer>) this.iterator();
    }

    @Override
    public Integer get(int index) {
        Node current = this.getNodeAt(index);
        return current.value;
    }

    @Override
    public Integer set(int index, Integer element) {
        Node current = this.getNodeAt(index);
        Integer temp = current.value;
        current.value = element;
        return temp;
    }

    @Override
    public void add(int index, Integer element) {
        Node n = new Node(element);
        if(index == 0){
            this.addToFront(n);
        }else if(index == this.size - 1){
            this.addToBack(n);
        }else {
            Node before = this.getNodeAt(index - 1);
            Node after = this.getNodeAt(index + 1);
            before.next = n;
            n.next = after;
            this.size++;
        }
    }

    @Override
    public Integer remove(int index) {
        Node current = this.getNodeAt(index);
        Integer temp = current.value;
        if(index == 0){
            this.removeFirst();
        }else if(index == this.size - 1){
            this.removeLast();
        }else{
            Node before = this.getNodeAt(index - 1);
            before.next = current.next;
            this.size--;
        }
        return temp;
    }

    private Node getNodeAt(int index){
        Node current = null;
        for(int i = 0; i < index; i++){
            if(i == 0){
                current = this.head;
            }else{
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not implemented yet !");
    }
}
