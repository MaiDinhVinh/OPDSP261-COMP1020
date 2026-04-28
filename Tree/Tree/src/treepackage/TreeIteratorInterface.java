package treepackage;
import java.util.Iterator;
/**
 * The Iterator support interface for all kinds of Trees. This will
 * allow the usage of for-each or Iterator looping through the Tree
 * @param <T>
 */
public interface TreeIteratorInterface<T>{
    public Iterator<T> getPreOrderIterator();
    public Iterator<T> getInOrderIterator();
    public Iterator<T> getPostOrderIterator();
    public Iterator<T> getLevelOrderIterator();
}
