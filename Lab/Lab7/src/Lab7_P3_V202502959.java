import java.util.ArrayList;
import java.util.Scanner;

public class Lab7_P3_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            int count = sc.nextInt();
            sc.nextLine();
            // edge case: empty tree -> print 3 zeros as specified
            if(count == 0){
                System.out.println(0);
                System.out.println(0);
                System.out.println(0);
            }else{
                int[] arr = new int[count];
                for(int i = 0; i < count; i++){
                    arr[i] = sc.nextInt();
                }
                BST bst = new Lab7_P3_V202502959().new BST();
                for(int i: arr) bst.add(i);

                // output order: preorder -> inorder -> postorder
                ArrayList<Integer> elements = bst.preOrderTraversal();
                for(int i = 0; i < elements.size(); i++){
                    if(i != elements.size() - 1){
                        System.out.print(elements.get(i) + " ");
                    }else{
                        System.out.print(elements.get(i));
                    }
                }
                System.out.println();
                elements = bst.inOrderTraversal();
                for(int i = 0; i < elements.size(); i++){
                    if(i != elements.size() - 1){
                        System.out.print(elements.get(i) + " ");
                    }else{
                        System.out.print(elements.get(i));
                    }
                }
                System.out.println();
                elements = bst.postOrderTraversal();
                for(int i = 0; i < elements.size(); i++){
                    if(i != elements.size() - 1){
                        System.out.print(elements.get(i) + " ");
                    }else{
                        System.out.print(elements.get(i));
                    }
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

        // preorder (root -> left -> right) — useful for reconstructing the tree later
        public ArrayList<Integer> preOrderTraversal(){
            ArrayList<Integer> arr = new ArrayList<>();
            this.preOrderTraversal(this.root, arr);
            return arr;
        }

        private void preOrderTraversal(Node n, ArrayList<Integer> arr){
            if(n == null){
                return;
            }else{
                arr.add(n.getValue());
                preOrderTraversal(n.getLeft(), arr);
                preOrderTraversal(n.getRight(), arr);
            }
        }

        // postorder (left -> right -> root) — children always appear before their parent
        public ArrayList<Integer> postOrderTraversal(){
            ArrayList<Integer> arr = new ArrayList<>();
            this.postOrderTraversal(this.root, arr);
            return arr;
        }

        private void postOrderTraversal(Node n, ArrayList<Integer> arr){
            if(n == null){
                return;
            }else{
                postOrderTraversal(n.getLeft(), arr);
                postOrderTraversal(n.getRight(), arr);
                arr.add(n.getValue());
            }
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