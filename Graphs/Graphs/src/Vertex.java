import java.util.ArrayList;

public class Vertex<T extends Comparable<T>>{
    private T value;
    private ArrayList<Edge<T>> edges = new ArrayList<>();

    //FOR BFS AND DFS ONLY !!! (idk, could be usable for other things)
    private boolean isIdentified;

    public Vertex(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public ArrayList<Edge<T>> getEdges() {
        return edges;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setEdges(ArrayList<Edge<T>> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge<T> edge){
        this.edges.add(edge);
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }
}
