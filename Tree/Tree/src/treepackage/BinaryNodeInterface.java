package treepackage;

interface BinaryNodeInterface<T>{
    public T getData();

    public void setData(T newData);

    public BinaryNodeInterface<T> getLeftChild();

    public BinaryNodeInterface<T> getRightChild();

    public void setLeftChild(BinaryNodeInterface<T> leftChild);

    public void setRightChild(BinaryNodeInterface<T> rightChild);

    public boolean hasLeftChild();

    public boolean hasRightChild();

    public boolean isLeaf();

    //get number of node in the subtree rooted at this node
    public int getNumberOfNodes();

    //get the height of the subtree rooted at this node
    public int getHeight();

    //make a copy of the subtree rooted at this node
    public BinaryNodeInterface<T> copy();
}
