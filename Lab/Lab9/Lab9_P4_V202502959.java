import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab9_P4_V202502959 {
	static class Vertex {
        private int value;
        private ArrayList<Integer> neighbors;
        private ArrayList<Integer> weights;

        public Vertex(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
            this.weights = new ArrayList<>();
        }

        public int getValue() {
            return value;
        }

        public ArrayList<Integer> getNeighbors() {
            return neighbors;
        }

        public void addEdge(Integer v, Integer w) {
            this.neighbors.add(v);
            this.weights.add(w);
        }
        
        public ArrayList<Integer> getWeights(){
        	return this.weights;
        }
        
        public void addWeight(Integer w) {
        	this.weights.add(w);
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

        public void addEdge(int i1, int i2, Integer weight) {
            if (this.hasVertex(i1) && this.hasVertex(i2)) {
                Vertex v1 = this.getVertex(i1);
                Vertex v2 = this.getVertex(i2);
                v1.addEdge(v2.getValue(), weight);
                if (!this.isDirected) // undirected graph — add the reverse direction too
                    v2.addEdge(v1.getValue(), weight);
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
                g.addEdge(u, v, 0);
            }
            ArrayList<Integer> bfs = new ArrayList<>();
            ArrayList<Integer> visited = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();
            int start = sc.nextInt();
            visited.add(start);
            queue.add(start);
            while(!queue.isEmpty()) {
            	Vertex v = g.getVertex(queue.poll());
            	for(int i: v.getNeighbors()) {
            		if(!visited.contains(i)) {
            			visited.add(i);
            			queue.add(i);
            		}
            	}
            	bfs.add(v.getValue());
            }
            System.out.println(bfs.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(" ")));
        }
	}
}
