import java.util.ArrayList;
import java.util.Scanner;

public class Lab9_P2_V202502959 {
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
            if (this.hasVertex(i1) && this.hasVertex(i2)) {
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

            Graph g = null;
            String isDirected = sc.next();
            if(isDirected.equals("D")) {
                g = new Graph(true);
            } else {
                g = new Graph(false);
            }
            for(int i = 0; i < numVertices; i++) {
                g.addVertex(i);
            }
            for (int i = 0; i < numEdges; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                g.addEdge(u, v);
            }
            if(g.isDirected) {
                for(Vertex v: g.getAdjacencyList()) {
                    int outDegree = v.getNeighbors().size(); // out-degree = number of edges going out from this vertex
                    int inDegree = 0;
                    for(Vertex ve: g.getAdjacencyList()) { // scan all vertices to count how many point to v
                        if(ve.getNeighbors().contains(v.getValue())) inDegree++;
                    }
                    System.out.printf("%d: %d %d%n", v.getValue(), inDegree, outDegree);
                }
            } else {
                for(Vertex v: g.getAdjacencyList()) {
                    System.out.printf("%d: %d%n", v.getValue(), v.getNeighbors().size()); // degree = neighbor count for undirected
                }
            }
        }
    }
}