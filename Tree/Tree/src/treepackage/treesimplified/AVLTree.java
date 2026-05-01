package treepackage.treesimplified;

public class AVLTree<T extends Comparable<T>>
        extends BinarySearchTree<T>{

    //check the height difference between 2 subtrees
    private int checkHeight(Node<T> node){
        return Math.abs(this.getHeight(node.getLeft()) -
                this.getHeight(node.getRight()));
    }

    private Node<T> rotateRight(Node<T> node){
        Node<T> pivot = node.getLeft();
        node.setLeft(pivot.getRight());
        pivot.setRight(node);
        return pivot;
    }

    private Node<T> rotateLeft(Node<T> node){
        Node<T> pivot = node.getRight();
        node.setRight(pivot.getLeft());
        pivot.setLeft(node);
        return pivot;
    }

    private Node<T> rotateLeftRight(Node<T> node){
        // a naive implementation
//        Node<T> pivot = node.getLeft().getRight();
//        node.getLeft().setRight(pivot.getLeft());
//        pivot.setLeft(node.getLeft());
//        node.setLeft(pivot);
//        return this.rotateRight(node);

        // a shorter implementation
        Node<T> pivot = node.getLeft();
        node.setLeft(this.rotateLeft(pivot));
        return this.rotateRight(node);
    }

    private Node<T> rotateRightLeft(Node<T> node){
        Node<T> pivot = node.getRight();
        node.setRight(this.rotateRight(pivot));
        return this.rotateLeft(node);
    }

    private Node<T> rebalance(Node<T> node){
        int heightDifference = this.checkHeight(node);
        if(heightDifference > 1){
            if(this.getHeight(node.getLeft()) - this.getHeight(node.getRight()) > 1){
                //subtree difference in the same root must be the same
                if(this.getHeight(node.getLeft().getLeft()) - this.getHeight(node.getLeft().getRight()) > 0){
                    return this.rotateRight(node);
                }else{
                    //this.getHeight(node.getLeft().getLeft()) - this.getHeight(node.getLeft().getRight()) < -1
                    return this.rotateLeftRight(node);
                }
            }else{//this.getHeight(node.getLeft()) - this.getHeight(node.getRight()) < -1

                //subtree difference in the same root must be the same
                if(this.getHeight(node.getRight().getRight()) - this.getHeight(node.getRight().getLeft()) > 0){
                    return this.rotateLeft(node);
                }else{
                    //this.getHeight(node.getRight().getRight()) - this.getHeight(node.getRight().getLeft()) < -1
                    return this.rotateRightLeft(node);
                }
            }
        }else{
            return node;
        }
    }

    public boolean add(T value){
        if(this.root == null){
            Node<T> n = new Node<>();
            n.setValue(value);
            this.root = n;
            return true;
        }else{
            try{
                this.add(value, this.root);

                //for the case where the tree is the same as a linkedlist (e.g: 1 -> 2 -> 3)
                //this is the case where all 3 element in on the same branch and also
                //the number of element is so special that without this line
                //the tree won't be balanced
                this.root = this.rebalance(this.root);
                return true;
            }catch(IllegalStateException e){
                e.printStackTrace();
                return false;
            }
        }
    }

    private void add(T value, Node<T> node){
        if(value.compareTo(node.getValue()) == 0){
            throw new IllegalStateException("Duplicate Element");
        }else if(value.compareTo(node.getValue()) > 0 && node.getRight() != null){
            add(value, node.getRight());
            node.setRight(this.rebalance(node.getRight()));
        }else if(value.compareTo(node.getValue()) < 0 && node.getLeft() != null){
            add(value, node.getLeft());
            node.setLeft(this.rebalance(node.getLeft()));
        }else if(value.compareTo(node.getValue()) > 0 && node.getRight() == null){
            Node<T> n = new Node<>();
            n.setValue(value);
            node.setRight(n);
        }else{
            Node<T> n = new Node<>();
            n.setValue(value);
            node.setLeft(n);
        }
    }
}