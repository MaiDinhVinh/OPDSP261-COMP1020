package treepackage.treesimplified;

//a much more simplified version from the book
public class BinaryTree<T extends Comparable<T>> {
    Node<T> root;

    public void inOrderTraversal(){
        this.inOrderTraversal(this.root);
    }

    private void inOrderTraversal(Node<T> node){
        if(node != null){
            this.inOrderTraversal(node.getLeft());
            System.out.println(node.getValue());
            this.inOrderTraversal(node.getRight());
        }
    }

    public void preOrderTraversal(){
        this.preOrderTraversal(this.root);
    }

    private void preOrderTraversal(Node<T> node){
        if(node != null){
            System.out.println(node.getValue());
            this.preOrderTraversal(node.getLeft());
            this.preOrderTraversal(node.getRight());
        }
    }

    public void postOrderTraversal(){
        this.postOrderTraversal(this.root);
    }

    private void postOrderTraversal(Node<T> node){
        if(node != null){
            this.postOrderTraversal(node.getLeft());
            this.postOrderTraversal(node.getRight());
            System.out.println(node.getValue());
        }
    }

    public void clear(){
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int getNodeCount(){
        return getNodeCount(this.root);
    }

    private int getNodeCount(Node<T> node){
        int leftNodeCount = 0;
        int rightNodeCount = 0;
        if(node.getLeft() != null){
            leftNodeCount = this.getNodeCount(node.getLeft());
        }
        if(node.getRight() != null){
            rightNodeCount = this.getNodeCount(node.getRight());
        }
        return 1 + leftNodeCount + rightNodeCount;
    }

    public int getHeight(Node<T> node){
        int height = 0;

        if(node != null){
            height = 1 + Math.max(this.getHeight(node.getLeft()),
                    this.getHeight(node.getRight()));
        }
        return height;
    }
}