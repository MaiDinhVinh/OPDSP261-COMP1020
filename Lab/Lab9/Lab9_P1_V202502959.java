import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab9_P1_V202502959 {
    static class Vertex {
        private int value;
        private ArrayList<Integer> neighbors;

        public Vertex(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        public int getValue() {
            return value;
        }

        public ArrayList<Integer> getNeighbors() {
            return neighbors;
        }

        public void addEdge(Integer v) {
            if (!this.neighbors.contains(v)) // avoid adding duplicate neighbors
                this.neighbors.add(v);
        }
    }

    static class Graph {
        private ArrayList<Vertex> adjacencyList;
        private boolean isDirected;

        public Graph(boolean isDirected) {
            this.adjacencyList = new ArrayList<>();
            this.isDirected = isDirected;
        }

        public ArrayList<Vertex> getAdjacencyList() {
            return adjacencyList;
        }

        public boolean hasVertex(int i) {
            for (Vertex v : this.adjacencyList) { // linear scan since we store vertices in a list, not a map
                if (v.getValue() == i)
                    return true;
            }
            return false;
        }

        public boolean addVertex(int i) {
            if (this.hasVertex(i)) return false;
            else {
                Vertex v = new Vertex(i);
                this.adjacencyList.add(v);
            }
            return true;
        }

        public Vertex getVertex(int i) {
            for (Vertex v : this.adjacencyList) {
                if (v.getValue() == i)
                    return v;
            }
            return null;
        }

        public boolean hasEdge(int i1, int i2) {
            if (this.hasVertex(i1)) {
                Vertex v1 = this.getVertex(i1);
                return v1.getNeighbors().contains(i2);
            }
            return false;
        }

        public void addEdge(int i1, int i2) {
            if (!this.hasEdge(i1, i2) && this.hasVertex(i1) && this.hasVertex(i2)) {
                Vertex v1 = this.getVertex(i1);
                Vertex v2 = this.getVertex(i2);
                v1.addEdge(v2.getValue());
                if (!this.isDirected) // undirected graph — add the reverse direction too
                    v2.addEdge(v1.getValue());
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) { // try-with-resources so scanner closes automatically
            int numVertices = sc.nextInt();
            int numEdges = sc.nextInt();

            Graph g = new Graph(false); // undirected graph
            for(int i = 0; i < numVertices; i++) {
            	g.addVertex(i);
            }
            for (int i = 0; i < numEdges; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                g.addEdge(u, v);
            }
            for (Vertex v : g.getAdjacencyList()) {
            	// Stream: convert each neighbor int to string, then join them with a space into one line
            	String allNeighbors = v.getNeighbors()
                  .stream()
                  .map(i -> Integer.toString(i))
                  .collect(Collectors.joining(" "));
          		System.out.printf("%d: %s%n", v.getValue(), allNeighbors);
      		}
        }
    }
}