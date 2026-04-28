package treepackage;

class BinaryNode<T> implements BinaryNodeInterface<T>{
    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;

    public BinaryNode(){
        this(null);
    }

    public BinaryNode(T data){
        this(data, null, null);
    }

    public BinaryNode(T data, BinaryNode<T> leftChild,
                      BinaryNode<T> rightChild){
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData(){
        return this.data;
    }

    @Override
    public void setData(T newData) {
        this.data = newData;
    }

    @Override
    public BinaryNodeInterface<T> getLeftChild() {
        return this.leftChild;
    }

    @Override
    public BinaryNodeInterface<T> getRightChild() {
        return this.rightChild;
    }

    @Override
    public void setLeftChild(BinaryNodeInterface<T> leftChild) {
        this.leftChild = (BinaryNode<T>) leftChild;
    }

    @Override
    public void setRightChild(BinaryNodeInterface<T> rightChild) {
        this.rightChild = (BinaryNode<T>) rightChild;
    }

    @Override
    public boolean hasLeftChild() {
        return this.leftChild != null;
    }

    @Override
    public boolean hasRightChild() {
        return this.rightChild != null;
    }

    @Override
    public boolean isLeaf() {
        return !this.hasLeftChild() && !this.hasRightChild();
    }

    @Override
    public int getNumberOfNodes() {
        //IMPLEMENTATION LATER ON!
        return 0;
    }

    @Override
    public int getHeight() {
        //IMPLEMENTATION LATER ON!
        return 0;
    }

    @Override
    public BinaryNodeInterface<T> copy() {
        //read the mindmap on why the fuck should i need this shit
        BinaryNode<T> newRoot = new BinaryNode<>(this.data);
        if(this.leftChild != null){
            //this code will call all left childs all the way down to
            //the leaf node
            newRoot.leftChild = (BinaryNode<T>)this.leftChild.copy();
        }
        if(this.rightChild != null){
            //this code will call all right childs all the way down to
            //the leaf node
            newRoot.rightChild = (BinaryNode<T>)this.rightChild.copy();
        }
        return newRoot;
    }
}
