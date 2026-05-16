package fullGraph;

import java.util.*;

public class Graph<T extends Comparable<T>>{
    private ArrayList<Vertex<T>> vertices;
    private boolean isDirected;

    public Graph(){
        this.vertices = new ArrayList<>();
    }

    public Graph(boolean isDirected){
        this();
        this.isDirected = isDirected;
    }

    public ArrayList<Vertex<T>> getVertices() {
        return vertices;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void setVertices(ArrayList<Vertex<T>> vertices) {
        this.vertices = vertices;
    }

    //full.Graph building operations
    public boolean addVertex(T vValue){
        for(Vertex<T> ve: this.vertices){
            if(ve.getValue().compareTo(vValue) == 0){
                return false;
            }
        }
        this.vertices.add(new Vertex<>(vValue));
        return true;
    }

    public boolean addEdge(T init, T terminal, int weight){
        Vertex<T> initVertex = null;
        Vertex<T> terminalVertex = null;
        for(Vertex<T> v: this.vertices){
            if(v.getValue().compareTo(init) == 0){
                initVertex = v;
            }
            if(v.getValue().compareTo(terminal) == 0){
                terminalVertex = v;
            }
        }
        if(initVertex != null && terminalVertex != null){
            if(!this.hasEdge(init, terminal)){
                Edge<T> e = new Edge<>(terminalVertex, weight);
                initVertex.addEdge(e);
                if(this.isDirected){
                    return true;
                }
                Edge<T> opposite = new Edge<>(initVertex, weight);
                terminalVertex.addEdge(opposite);
                return true;
            }else{
                return false;
            }
        }
        throw new NoSuchElementException("Invalid vertex values");
    }


    public int getVertexCount(){
        return this.vertices.size();
    }

    //get total edges exist in this graph
    public int getEdgeCount(){
        int count = 0;
        for(Vertex<T> v: this.vertices){
            count += v.getEdges().size();
        }

        if(this.isDirected){
            return count;
        }

        //using Handshaking theorem to calculate total edges in an Undirected full.Graph
        return count / 2;
    }

    public boolean isEmpty(){
        return this.vertices.isEmpty();
    }

    public boolean hasEdge(T init, T terminal){
        for(Vertex<T> ve: this.vertices){
            if(ve.getValue().compareTo(init) == 0){
                for(Edge<T> e: ve.getEdges()){
                    if(e.getTerminalVertex().getValue().compareTo(terminal) == 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Edge<T> getEdge(T init, T terminal){
        if(this.hasEdge(init, terminal)){
            for(Vertex<T> ve: this.vertices){
                if(ve.getValue().compareTo(init) == 0){
                    for(Edge<T> e: ve.getEdges()){
                        if(e.getTerminalVertex().getValue().compareTo(terminal) == 0){
                            return e;
                        }
                    }
                }
            }
        }
        throw new NoSuchElementException("No full.Edge exists");
    }

    public void clear(){
        this.vertices.clear();
    }

    //BFS - Breadth First Search Traversal
    public ArrayList<Vertex<T>> breadthFirstSearch(){
        ArrayList<Vertex<T>> traversedVertices = new ArrayList<>();
        if(this.vertices.isEmpty()){
            return traversedVertices;
        }
        Vertex<T> first = this.getUnidentifiedVertex();
        while(first != null){
            Queue<Vertex<T>> queue = new LinkedList<>();
            first.setIdentified(true);
            queue.add(first);
            while(!queue.isEmpty()){
                Vertex<T> v = queue.poll();
                for(Edge<T> e: v.getEdges()){
                    if(!e.getTerminalVertex().isIdentified()){
                        Vertex<T> unidentified = e.getTerminalVertex();
                        unidentified.setIdentified(true);
                        queue.add(unidentified);
                    }
                }
                traversedVertices.add(v);
            }
            first = this.getUnidentifiedVertex();
        }

        //all this everytime we finish a BFS traversal, we have to call this after all iterations
        //are completed because if we call during the outer loop, we will repeat BFS again for
        //visited node
        this.resetVertexState();

        return traversedVertices;
    }

    /**
     * Used for the case of Disconnected full.Graph, finding all unidentified nodes
     */
    private Vertex<T> getUnidentifiedVertex(){
        for(Vertex<T> v: this.vertices){
            if(!v.isIdentified()){
                return v;
            }
        }
        return null;
    }

    public ArrayList<Vertex<T>> depthFirstSearch(){
        ArrayList<Vertex<T>> dfsTraversal = new ArrayList<>();
        Stack<Vertex<T>> vertexStack = new Stack<>();
        if(this.vertices.isEmpty()){
            return dfsTraversal;
        }
        Vertex<T> v = this.getUnidentifiedVertex();
        while(v != null){
            v.setIdentified(true);
            dfsTraversal.add(v);
            vertexStack.push(v);
            while(!vertexStack.isEmpty()){
                Vertex<T> topVertex = vertexStack.peek();
                Vertex<T> unidentified = null;
                for(Edge<T> e: topVertex.getEdges()){
                    if(!e.getTerminalVertex().isIdentified()){
                        unidentified = e.getTerminalVertex();
                        break;
                    }
                }
                if(unidentified != null){
                    unidentified.setIdentified(true);
                    dfsTraversal.add(unidentified);
                    vertexStack.push(unidentified);
                }else{
                    vertexStack.pop();
                }
            }
            v = this.getUnidentifiedVertex();
        }

        this.resetVertexState();

        return dfsTraversal;
    }

    /**
     * everytime BFS is run, the states of all nodes is updated without reset, so this method
     * is used to reset all states of all notes
     */
    private void resetVertexState(){
        for(Vertex<T> v: this.vertices){
            v.setIdentified(false);
        }
    }

    public LinkedHashMap<Vertex<T>, Vertex<T>> dijkstra(T startValue){
        for(Vertex<T> v: this.vertices){
            if(v.getValue().compareTo(startValue) == 0){
                return this.dijkstra(v);
            }
        }
        throw new NoSuchElementException("Invald vertex value");
    }


    //Dijkstra's Shortest Path Algorithm
    private LinkedHashMap<Vertex<T>, Vertex<T>> dijkstra(Vertex<T> startVertex) {
        ArrayList<Vertex<T>> unidentified = new ArrayList<>(this.vertices);
        LinkedHashMap<Vertex<T>, Vertex<T>> predecessors = new LinkedHashMap<>();
        LinkedHashMap<Vertex<T>, Integer> distances = new LinkedHashMap<>();

        ArrayList<Vertex<T>> vertices = new ArrayList<>(this.vertices);
        vertices.remove(startVertex);
        predecessors.put(startVertex, null);
        distances.put(startVertex, 0);

        for (Vertex<T> v : vertices) {
            predecessors.put(v, null);
            distances.put(v, null);// null = infinity
        }

        //BEGIN ALGORITHM
        while (!unidentified.isEmpty()) {
            Vertex<T> minUnidenVertex = this.findMinUnidenVertex(distances);

            if(minUnidenVertex == null) break; //disconnected graph, will end the algorithm

            for (Edge<T> e : minUnidenVertex.getEdges()) {
                if (!e.getTerminalVertex().isIdentified()) {
                    Vertex<T> tVertex = e.getTerminalVertex();
                    int previousDist = this.getVertexDis(distances, minUnidenVertex); //always work btw, ignore Pablo (warning)
                    if (checkSmallerDistance(distances, tVertex, previousDist + e.getWeight())) {
                        this.updateVertexDist(distances, tVertex, previousDist + e.getWeight());
                        this.updatePredecessor(predecessors, tVertex, minUnidenVertex);
                    }
                }
            }
            minUnidenVertex.setIdentified(true);
            unidentified.remove(minUnidenVertex);
        }

        this.resetVertexState();

        return predecessors;
    }

    private Vertex<T> findMinUnidenVertex(LinkedHashMap<Vertex<T>, Integer> distances){
        int minDist = Integer.MAX_VALUE;
        Vertex<T> currentMinVertex = null;
        for(Map.Entry<Vertex<T>, Integer> e: distances.entrySet()){
            if(e.getValue() != null && e.getValue() < minDist && !e.getKey().isIdentified()){
                minDist = e.getValue();
                currentMinVertex = e.getKey();
            }
        }
        return currentMinVertex;
    }

    //check if the newly updated distance is smaller than the current value in the map
    private boolean checkSmallerDistance(LinkedHashMap<Vertex<T>, Integer> distances,
                                      Vertex<T> vertex,
                                      int distance){
        for(Map.Entry<Vertex<T>, Integer> e: distances.entrySet()){
            if(e.getKey().equals(vertex)){
                if(e.getValue() == null){
                    return true;
                }else{
                    if(e.getValue() > distance){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //update the current shortest distance for each vertex in the "distances" map
    private void updateVertexDist(LinkedHashMap<Vertex<T>, Integer> distances,
                                  Vertex<T> vertex,
                                  int distance){
        for(Map.Entry<Vertex<T>, Integer> e: distances.entrySet()){
            if(e.getKey().equals(vertex)){
                distances.put(vertex, distance);
                break;
            }
        }
    }

    //get the distance value from each vertex from the "distances" map
    private Integer getVertexDis(LinkedHashMap<Vertex<T>, Integer> distances,
                                  Vertex<T> vertex){
        for(Map.Entry<Vertex<T>, Integer> e: distances.entrySet()){
            if(e.getKey().equals(vertex)){
                return e.getValue();
            }
        }
        return null;
    }

    //update the shortest predecessor in the "predecessors" map
    private void updatePredecessor(LinkedHashMap<Vertex<T>, Vertex<T>> predecessors,
                                   Vertex<T> child,
                                   Vertex<T> parent){
        for(Map.Entry<Vertex<T>, Vertex<T>> e: predecessors.entrySet()){
            if(e.getKey().equals(child)){
                predecessors.put(e.getKey(), parent);
                break;
            }
        }
    }
}
