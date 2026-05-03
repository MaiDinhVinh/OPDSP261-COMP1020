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

    //Graph building operations
    public boolean addVertex(T vValue){
        Vertex<T> v = new Vertex<>(vValue);
        for(Vertex<T> ve: this.vertices){
            if(ve.getValue().compareTo(vValue) == 0){
                return false;
            }
        }
        this.vertices.add(v);
        return true;
    }

    public boolean addEdge(T init, T terminal){
        boolean initExist = false;
        boolean terminalExist = false;
        Vertex<T> ve = null;
        for(Vertex<T> v: this.vertices){
            if(v.getValue().compareTo(init) == 0){
                initExist = true;
            }
            if(v.getValue().compareTo(terminal) == 0){
                terminalExist = true;
                ve = v;
            }
        }
        if(initExist && terminalExist){
            if(!this.hasEdge(init, terminal)){
                for(Vertex<T> v: this.vertices){
                    if(v.getValue().compareTo(init) == 0){
                        Edge<T> e = new Edge<>(ve);
                        v.addEdge(e);
                        //add another edge in the list of the opposite node for undirected graph
                        if(!this.isDirected){
                            Edge<T> opposite = new Edge<>(v);
                            ve.addEdge(opposite);
                        }
                        return true;
                    }
                }
            }
            return false;
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

        //using Handshaking theorem to calculate total edges in an Undirected Graph
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
        throw new NoSuchElementException("No Edge exists");
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
     * Used for the case of Disconnected Graph, finding all unidentified nodes
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


    //Dijkstra's Shortest Path Algorithm
    public LinkedHashMap<Vertex<T>, Vertex<T>> dijkstra(Vertex<T> startVertex){
        LinkedHashMap<Vertex<T>, Integer> distances = new LinkedHashMap<>();
        LinkedHashMap<Vertex<T>, Vertex<T>> predecessors = new LinkedHashMap<>();

        //class used just for the algorithm only
        class ShortestDistVertex<V extends Comparable<V>>{
            private Vertex<V> vertex;
            private int shortestDistance;

            public ShortestDistVertex(Vertex<V> vertex, int shortestDistance){
                this.vertex = vertex;
                this.shortestDistance = shortestDistance;
            }

            public int getShortestDistance() {
                return shortestDistance;
            }

            public Vertex<V> getVertex() {
                return vertex;
            }
        }

        PriorityQueue<ShortestDistVertex<T>> queue = new PriorityQueue<>(
                Comparator.comparingInt(ShortestDistVertex::getShortestDistance)
        );

        ///BEGIN ALGORITHM

        if(this.vertices.isEmpty()){
            return predecessors;
        }
        distances.put(startVertex, 0);
        for(Vertex<T> v: this.vertices){
            if(v.getValue().compareTo(startVertex.getValue()) == 0){
                continue;
            }
            distances.put(startVertex, null);
        }
        queue.add(new ShortestDistVertex<>(startVertex, 0));
        while(!queue.isEmpty()){
            ShortestDistVertex<T> temp = queue.poll();
            Vertex<T> currentVertex = temp.getVertex();
            for(Edge<T> e: currentVertex.getEdges()){

            }
        }
        return predecessors;
    }
}
