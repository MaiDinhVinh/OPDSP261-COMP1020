package binarytree;

public class BinaryNode {
    private BinaryNode left;
    private BinaryNode right;
    private int value;

    public BinaryNode(int value){
        this.value = value;
    }

    public BinaryNode getRight() {
        return right;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }
}
