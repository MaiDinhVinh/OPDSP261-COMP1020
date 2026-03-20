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

    private BinaryNode[] findParent(int i){
        BinaryNode bn = this.head;
        return this.findParent(bn, bn, i);
    }

    private BinaryNode[] findParent(BinaryNode bn, BinaryNode parent, int i){
        if (bn == null) {
            return null;
        }else if(i > bn.getValue()){
            return this.findParent(bn.getRight(), bn, i);
        }else if(i < bn.getValue()){
            return this.findParent(bn.getLeft(), bn, i);
        }else if(i == this.head.getValue()){
            return new BinaryNode[]{bn, null};
        }else{
            return new BinaryNode[]{bn, parent};
        }
    }

    public void remove(int i){
        BinaryNode remove = this.find(i);
        BinaryNode removeParent = this.findParent(i)[1];
        if(remove == null){
            throw new NoSuchElementException("nah nothing was found");
        }else{
            if(remove.getRight() == null){
                if(removeParent == null){
                    this.head = remove.getLeft();
                }else{
                    if(i >= removeParent.getValue()){
                        removeParent.setRight(remove.getLeft());
                    }else{
                        removeParent.setLeft(remove.getLeft());
                    }
                }
            }else if(remove.getRight().getLeft() == null){
                BinaryNode removeLeft = remove.getLeft();
                if(removeParent == null){
                    this.head = this.head.getRight();
                }else{
                    if(i >= removeParent.getValue()){
                        removeParent.setRight(remove.getRight());
                    }else{
                        removeParent.setLeft(remove.getRight());
                    }
                }
                this.add(removeLeft.getValue());
            }else if(remove.getRight().getLeft() != null){
                BinaryNode left = remove.getRight();
                BinaryNode leftMost = remove.getRight().getLeft();
                while(leftMost.getLeft() != null){
                    left = leftMost;
                    leftMost = leftMost.getLeft();
                }
                left.setLeft(leftMost.getRight());
                leftMost.setLeft(remove.getLeft());
                leftMost.setRight(remove.getRight());
                if(removeParent == null){
                    this.head = leftMost;
                }else{
                    if(i >= removeParent.getValue()){
                        removeParent.setRight(leftMost);
                    }else{
                        removeParent.setLeft(leftMost);
                    }
                }
            }
        }
    }

    public void clear(){
        this.head = null;
    }
}