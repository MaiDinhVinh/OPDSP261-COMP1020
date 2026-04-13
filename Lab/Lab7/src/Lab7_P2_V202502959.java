import java.util.ArrayList;
import java.util.Scanner;

public class Lab7_P2_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            int[] commands = new int[m];
            int[] keys = new int[m];

            BST tree = new Lab7_P2_V202502959().new BST();
            // build the initial BST with n elements
            for(int i = 0; i < n; i++){
                tree.add(sc.nextInt());
            }
            sc.nextLine(); // consume newline before reading commands

            for(int i = 0; i < m; i++){
                commands[i] = sc.nextInt();
                keys[i] = sc.nextInt();
            }

            // process commands: 1 = insert, 2 = search
            // print 1 on success/found, 0 on failure/not found
            for(int i = 0; i < m; i++){
                if(commands[i] == 1){
                    boolean success = tree.add(keys[i]);
                    System.out.println(success ? 1 : 0);
                }else if(commands[i] == 2){
                    ArrayList<Integer> arr = tree.inOrderTraversal();
                    if(arr.contains(keys[i])) System.out.println(1);
                    else System.out.println(0);
                }
            }

            // print final state of the BST as inorder traversal
            ArrayList<Integer> elements = tree.inOrderTraversal();
            for(int i = 0; i < elements.size(); i++){
                if(i != elements.size() - 1){
                    System.out.print(elements.get(i) + " ");
                }else{
                    System.out.print(elements.get(i));
                }
            }
        }
    }

    class BST{
        private Node root;
        private int height;
        private int leafNodeCount;

        // inserts at root if empty, otherwise delegates to recursive helper
        public boolean add(int input){
            if(this.root == null){
                this.root = new Node(input);
                return true;
            }else{
                return this.add(this.root, input);
            }
        }

        // standard BST insert: go right if greater, left if smaller, return false on duplicate
        private boolean add(Node n, int input){
            if(n.getRight() == null && input > n.getValue()){
                n.setRight(new Node(input));
                return true;
            }else if(n.getLeft() == null && input < n.getValue()){
                n.setLeft(new Node(input));
                return true;
            }else if(n.getRight() != null && input > n.getValue()){
                return add(n.getRight(), input);
            }else if(n.getLeft() != null && input < n.getValue()){
                return add(n.getLeft(), input);
            }else{
                return false;
            }
        }

        // inorder (left -> root -> right) gives sorted ascending order for BST
        public ArrayList<Integer> inOrderTraversal(){
            ArrayList<Integer> arr = new ArrayList<>();
            this.inOrderTraversal(this.root, arr);
            return arr;
        }

        private void inOrderTraversal(Node n, ArrayList<Integer> arr){
            if(n == null){
                return;
            }else{
                inOrderTraversal(n.getLeft(), arr);
                arr.add(n.getValue());
                inOrderTraversal(n.getRight(), arr);
            }
        }

        // uses instance field leafNodeCount as accumulator across recursive calls
        public int getLeafNodeCount(){
            this.getLeafNodeCount(this.root);
            return this.leafNodeCount;
        }

        // a node is a leaf if it has no children
        private void getLeafNodeCount(Node n){
            if(n == null){
                return;
            }else if(n.getRight() == null && n.getLeft() == null) {
                this.leafNodeCount++;
            }
            getLeafNodeCount(n.getLeft());
            getLeafNodeCount(n.getRight());
        }

        public Node getRoot() { return root; }

        public int treeHeight(){
            return this.treeHeight(this.root);
        }

        // height = longest path from node to a leaf, null node returns -1 so a single node gives 0
        private int treeHeight(Node n){
            if(n == null){
                return -1;
            }
            int heightLeft = treeHeight(n.getLeft());
            int heightRight = treeHeight(n.getRight());

            return Math.max(heightLeft, heightRight) + 1;
        }
    }

    class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value){
            this.value = value;
        }

        public Node getLeft() { return left; }
        public Node getRight() { return right; }
        public int getValue() { return value; }
        public void setLeft(Node left) { this.left = left; }
        public void setRight(Node right) { this.right = right; }
    }
}