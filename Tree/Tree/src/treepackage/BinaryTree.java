package treepackage;

import java.util.Iterator;

public class BinaryTree<T> implements BinaryTreeInterface<T>{

    private BinaryNodeInterface<T> root;

    public BinaryTree(){
        this.root = null;
    }

    public BinaryTree(T rootData){
        this.root = new BinaryNode<T>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree,
                      BinaryTree<T> rightTree){
        this.setTree(rootData, leftTree, rightTree);
    }

    @Override
    public void setTree(T rootData) {
        //used to replace the old value of the root in the tree
        //or used to initalize a new value to the rot in the tree
        this.root = new BinaryNode<T>(rootData);
    }

    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        this.setTree(rootData, leftTree, rightTree);
    }

    private void setTree(T rootData, BinaryTree<T> leftTree,
                         BinaryTree<T> rightTree){
        //First solution - unoptimized
        /*
        this.root = new BinaryNode<T>(rootData);
        if(leftTree != null){
            //since we are assigning all subtrees to get a
            //final combined binary tree => we will assign the root of
            //each subtree to the parent (essentially assigning all nodes
            //together)
            this.root.setLeftChild(leftTree.root);
        }
        if(rightTree != null){
            //since we are assigning all subtrees to get a
            //final combined binary tree => we will assign the root of
            //each subtree to the parent (essentially assigning all nodes
            //together)
            this.root.setRightChild(rightTree.root);
        }
        */

        //Second solution - partially optimized (are we deadass ?)
        this.root = new BinaryNode<>(rootData);
        if(leftTree != null && !(leftTree.isEmpty())){
            this.root.setLeftChild(leftTree.root.copy());
        }
        if(rightTree != null && !(rightTree.isEmpty())){
            this.root.setRightChild(rightTree.root.copy());
        }
    }

    @Override
    public T getRootData() {
        //IMPLEMENTATION LATER ON!
        return null;
    }

    @Override
    public int getHeight() {
        //IMPLEMENTATION LATER ON!
        return 0;
    }

    @Override
    public int getNumberOfNodes() {
        //IMPLEMENTATION LATER ON!
        return 0;
    }

    @Override
    public boolean isEmpty() {
        //IMPLEMENTATION LATER ON!
        return false;
    }

    @Override
    public Iterator<T> getPreOrderIterator() {
        //IMPLEMENTATION LATER ON!
        return null;
    }

    @Override
    public Iterator<T> getInOrderIterator() {
        //IMPLEMENTATION LATER ON!
        return null;
    }

    @Override
    public Iterator<T> getPostOrderIterator() {
        //IMPLEMENTATION LATER ON!
        return null;
    }

    @Override
    public Iterator<T> getLevelOrderIterator() {
        //IMPLEMENTATION LATER ON!
        return null;
    }
}
