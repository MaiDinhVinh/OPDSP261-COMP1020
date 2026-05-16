package fullGraph;

public class Edge<T extends Comparable<T>>{
    /**
     * Q: Why full.Edge<T> only stores terminalVertex only ? What happen if we use Undirected full.Graph
     * instead of Directed full.Graph ?
     *
     * A: If we use Undirected full.Graph, then each node will have a list of edges, which contains
     * its opposite node and vice versa
     */
    private Vertex<T> terminalVertex;
    private int weight;

    public Edge(Vertex<T> terminalVertex){
        this.terminalVertex = terminalVertex;
    }

    public Edge(Vertex<T> terminalVertex, int weight){
        this.terminalVertex = terminalVertex;
        this.weight = weight;
    }

    public Vertex<T> getTerminalVertex() {
        return terminalVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setTerminalVertex(Vertex<T> terminalVertex) {
        this.terminalVertex = terminalVertex;
    }
}