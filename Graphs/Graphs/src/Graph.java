import java.util.Iterator;

public interface Graph<T extends Comparable<T>>{
    int getVerticeCount();
    boolean isDirected();
    Iterator<Edge> edgeIterator(T value);
}
