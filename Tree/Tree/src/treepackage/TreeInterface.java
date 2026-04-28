package treepackage;
/**
 * The general interface for all kind of fucking Trees. This interface
 * contains general methods that all Trees need
 * @param <T> Type of object containing in the Tree
 */
public interface TreeInterface<T>{
    public T getRootData();
    public int getHeight();
    public int getNumberOfNodes();
    public boolean isEmpty();
}