package treepackage;

public interface BinaryTreeInterface<T> extends
        TreeInterface<T>, TreeIteratorInterface<T>{
    /**
     * Creating a Binary Tree with a single root element
     * This method is created so that we can combine multiple subtrees
     * into a single tree
     * @param rootData
     */
    public void setTree(T rootData);

    /**
     * This method will create a new tree with 1 root element and other
     * subtrees represented by leftTree and subTree
     * @param rootData
     * @param leftTree
     * @param rightTree
     */
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                        BinaryTreeInterface<T> rightTree);
}