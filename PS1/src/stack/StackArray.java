package stack;

import java.util.Iterator;
import java.util.Arrays;

public class StackArray implements Iterable<Integer>{

    private int[] arr = new int[0];
    private int topPointer;

    public void push(Integer i){
        if(this.arr.length == 0){
            arr = new int[4];
            arr[0] = i;
            return; //no need to do topPointer = 0 since topPointer has default value = 0
        }else if(this.topPointer == this.arr.length - 1) {
            this.arr = Arrays.copyOf(this.arr, this.arr.length * 2);
        }
        this.topPointer++;
        this.arr[this.topPointer] = i;
    }

    public Integer peek(){
        return this.arr[this.topPointer];
    }

    public void pop(){
        this.arr[this.topPointer] = 0;
        if(this.topPointer != 0){
            this.topPointer--;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){

            private int currentPointer = StackArray.this.topPointer;

            @Override
            public boolean hasNext() {
                return !(this.currentPointer < 0);
            }

            @Override
            public Integer next() {
                int a = StackArray.this.arr[currentPointer];
                this.currentPointer--;
                return a;
            }
        };
    }
}
