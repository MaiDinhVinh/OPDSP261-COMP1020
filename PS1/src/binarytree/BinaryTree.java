package binarytree;

import java.util.NoSuchElementException;

public class BinaryTree {
    private BinaryNode head;

    public void add(int input){
        if(this.head == null){
            this.head = new BinaryNode(input);
        }else{
            this.add(this.head, input);
        }
    }

    private void add(BinaryNode bn, int input){
        if(input >= bn.getValue() && bn.getRight() == null){
            bn.setRight(new BinaryNode(input));
        }else if(input < bn.getValue() && bn.getLeft() == null){
            bn.setLeft(new BinaryNode(input));
        }else if(input >= bn.getValue() && bn.getRight() != null){
            this.add(bn.getRight(), input);
        }else{
            this.add(bn.getLeft(), input);
        }
    }

    public BinaryNode find(int input){
        return this.find(this.head, input);
    }

    private BinaryNode find(BinaryNode bn, int input){
        if (bn == null) {
            return null;
        }else if(input > bn.getValue()){
            return this.find(bn.getRight(), input);
        }else if(input < bn.getValue()){
            return this.find(bn.getLeft(), input);
        }else{
            return bn;
        }
    }

    public void preOrderTraverse(){
        this.preOrderTraverse(this.head);
    }

    private void preOrderTraverse(BinaryNode bn){
        if(bn != null){
            System.out.println(bn.getValue());
            this.preOrderTraverse(bn.getLeft());
            this.preOrderTraverse(bn.getRight());
        }
    }

    public void inOrderTraverse(){
        this.inOrderTraverse(this.head);
    }

    private void inOrderTraverse(BinaryNode bn){
        if(bn != null){
            this.inOrderTraverse(bn.getLeft());
            System.out.println(bn.getValue());
            this.inOrderTraverse(bn.getRight());
        }
    }

    public void postOrderTraverse(){
        this.postOrderTraverse(this.head);
    }

    private void postOrderTraverse(BinaryNode bn){
        if(bn != null){
            this.postOrderTraverse(bn.getLeft());
            this.postOrderTraverse(bn.getRight());
            System.out.println(bn.getValue());
        }
    }

    private BinaryNode findParent(int i){
        BinaryNode bn = this.head;
        return this.findParent(bn, i);
    }

    private BinaryNode findParent(BinaryNode bn, int i){
        if(bn == null){
            throw new NoSuchElementException("bro your parent went to buy sum milk");
        }else if(bn.getLeft().getValue() == i || bn.getRight().getValue() == i){
            return bn;
        }else if()
    }

    public void remove(int i){
        BinaryNode remove = this.find(i);
        if(remove == null){
            throw new NoSuchElementException("nah nothing was found");
        }else{
            if(remove.getRight() == null){
                if(this.head.getValue() == remove.getValue()){
                    this.head = remove.getLeft();
                }else{

                }
            }
        }
    }
}
