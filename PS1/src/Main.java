import binarytree.BinaryTree;
import linkedlist.LinkedList;
import linkedlist.Node;
import queue.ArrayQueue;
import queue.LinkedQueue;
import queue.PriorityQueue;
import stack.StackArray;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Integer[] i = {1, null, 2, 3, null};
        i = Arrays.copyOf(i, i.length*2);
        System.out.println(Arrays.toString(i));
    }
}
