package queue;

import java.util.Iterator;

public class ArrayQueue implements Iterable<Integer>{

    private Integer[] arr = new Integer[0];
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public void enqueue(int input){
        if(this.size == this.arr.length) {
            Integer[] newArr = this.arr.length == 0 ? new Integer[4] : new Integer[this.arr.length * 2];
            int newIndex = 0;
            if (this.size > 0) {
                if(this.tail < this.head){
                    for(int i = this.head; i < this.arr.length; i++, newIndex++){
                        newArr[newIndex] = this.arr[i];
                    }
                    for(int i = 0; i <= this.tail; i++, newIndex++){
                        newArr[newIndex] = this.arr[i];
                    }
                }else{
                    for(int i = head; i <= this.tail - 1; i++, newIndex++){
                        newArr[newIndex] = this.arr[i];
                    }
                }
            }
            this.arr = newArr;
            this.head = 0;
            this.tail = newIndex;
        }
        this.arr[this.tail++] = input;
        size++;
    }

    public void dequeue(){
        if(this.size == 0){
            throw new IllegalStateException("bro it is empty");
        }else if(this.head == this.arr.length){
            this.head = 0;
            this.arr[this.head] = null;
        }else{
            this.arr[this.head++] = null;
        }
        size--;
    }

    public int peek(){
        return this.arr[this.head];
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>(){

            private int currentIndex = ArrayQueue.this.head;

            @Override
            public boolean hasNext() {
                return this.currentIndex != ArrayQueue.this.tail;
            }

            @Override
            public Integer next() {
                if(this.currentIndex == ArrayQueue.this.arr.length){
                    this.currentIndex = 0;
                }
                Integer a = ArrayQueue.this.arr[this.currentIndex];
                this.currentIndex++;
                return a;
            }
        };
    }
}
