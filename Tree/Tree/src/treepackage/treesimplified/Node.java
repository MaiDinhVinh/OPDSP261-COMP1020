package treepackage.treesimplified;

class Node<V extends Comparable<V>>{
    private V value;
    private Node<V> left;
    private Node<V> right;

    public Node<V> getLeft(){
        return this.left;
    }

    public Node<V> getRight(){
        return this.right;
    }

    public V getValue(){
        return this.value;
    }

    public void setLeft(Node<V> left){
        this.left = left;
    }

    public void setRight(Node<V> right){
        this.right = right;
    }

    public void setValue(V value){
        this.value = value;
    }
}