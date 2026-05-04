import java.util.ArrayList;
import java.util.Objects;

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

    /**
     * This is used for Vertex comparison and Dijkstra's Algorithm
     * @param o   the reference object with which to compare.
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }else if(!(o instanceof Vertex)){
            return false;
        }
        //this shit always true because the graph is Homogenous
        //please ignore the "unchecked cast" ahh pussy :whilted_rose:
        Vertex<T> vertex = (Vertex<T>) o;
        return vertex.value.compareTo(this.value) == 0;
    }

    /**
     * This make sures that the hashcode of this object is always consistent
     * with the equals return object
     *
     * This is used for Vertex comparison and Dijkstra's Algorithm
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
