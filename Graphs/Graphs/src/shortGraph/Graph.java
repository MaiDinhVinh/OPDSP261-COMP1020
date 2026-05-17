package shortGraph;

import java.util.*;
import java.util.function.Function;

public class Graph {
    //new int[]{vertex, weight}
    private final ArrayList<ArrayList<int[]>> adjacencyList;
    private final boolean isDirected;

    public Graph(boolean isDirected){
        this.adjacencyList = new ArrayList<>();
        this.isDirected = isDirected;
    }

    public void addEdge(int initial, int terminal, int weight){
        this.adjacencyList.get(initial).add(new int[]{terminal, weight});
        if(!this.isDirected){
            this.adjacencyList.get(terminal).add(new int[]{initial, weight});
        }
    }

    public int vertexInDegree(int vertex, ArrayList<ArrayList<int[]>> graph){
        int n = graph.size();
        int inDegree = 0;
        if(graph.get(vertex) == null) inDegree = -1;
        else{
            for(ArrayList<int[]> v: graph){
                if(v != null){
                    for(int[] arr: v) if(arr[0] == vertex) inDegree++;
                }
            }
        }
        return inDegree;
    }

    public int vertexOutDegree(int vertex, ArrayList<ArrayList<int[]>> graph){
        return graph.get(vertex).size();
    }

    public ArrayList<Integer> bfs(int start){
        boolean[] visited = new boolean[this.adjacencyList.size()];
        ArrayList<Integer> bfs = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while(!queue.isEmpty()){
            int vertex = queue.poll();
            for(int[] pair: this.adjacencyList.get(vertex)){
                if(!visited[pair[0]]){
                    visited[pair[0]] = true;
                    queue.add(pair[0]);
                }
            }
            bfs.add(vertex);
        }
        return bfs;
    }

    public ArrayList<Integer> dfs(int start){
        boolean[] visited = new boolean[this.adjacencyList.size()];
        ArrayList<Integer> dfs = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        dfs.add(start);
        visited[start] = true;
        while(!stack.isEmpty()){
            int vertex = stack.peek();
            Integer notVisited = null;
            for(int[] pair: this.adjacencyList.get(vertex)){
                if(!visited[pair[0]]){
                    visited[pair[0]] = true;
                    notVisited = pair[0];
                    break;
                }
            }
            if(notVisited != null){
                stack.push(notVisited);
                dfs.add(notVisited);
            }else{
                stack.pop();
            }
        }
        return dfs;
    }

    public int[] dijkstra(int start) {
        int[] dists = new int[this.adjacencyList.size()];
        int[] parents = new int[this.adjacencyList.size()];
        Arrays.fill(dists, Integer.MAX_VALUE);
        Arrays.fill(parents, Integer.MAX_VALUE);
        dists[start] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        queue.add(new int[]{start, 0});
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int vertex = current[0], distance = current[1];
            if(distance > dists[vertex]) continue;
            for(int[] arr: this.adjacencyList.get(vertex)){
                if(distance + arr[1] < dists[arr[0]]){
                    dists[arr[0]] = distance + arr[1];
                    parents[arr[0]] = vertex;
                    queue.add(new int[]{arr[0], distance + arr[1]});
                }
            }
        }
        return dists;
    }

    public ArrayList<ArrayList<int[]>> primMst(int start){
        if(this.isDirected){
            throw new UnsupportedOperationException("ts doesnt work with directed graph you bitch ass");
        }
        int n = this.adjacencyList.size();
        boolean[] inMst = new boolean[n];
        ArrayList<ArrayList<int[]>> mst = new ArrayList<>();
        for(int i = 0; i < n; i++){
            mst.add(new ArrayList<>());
        }
        inMst[start] = true;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
        for(int[] arr: this.adjacencyList.get(start)){
            queue.add(new int[]{start, arr[0], arr[1]});
        }
        while(!queue.isEmpty()){
            int[] edge = queue.poll();
            int from = edge[0], to = edge[1], weight = edge[2];
            if(inMst[to]) continue;
            inMst[to] = true;
            mst.get(from).add(new int[]{to, weight});
            mst.get(to).add(new int[]{from, weight});
            for(int[] arr: this.adjacencyList.get(to)){
                if(!inMst[arr[0]]) queue.add(new int[]{to, arr[0], arr[1]});
            }
        }
        return mst;
    }

    public ArrayList<ArrayList<int[]>> kruskalMst(){
        if(this.isDirected){
            throw new UnsupportedOperationException("ts doesnt work with directed graph you bitch ass");
        }
        ArrayList<int[]> sortedEdges = new ArrayList<>();
        ArrayList<ArrayList<int[]>> mst = new ArrayList<>(this.adjacencyList.size());
        for(int i = 0; i < this.adjacencyList.size(); i++){
            for(int[] arr: this.adjacencyList.get(i)){
                sortedEdges.add(new int[]{i, arr[0], arr[1]});
            }
            mst.add(new ArrayList<>());
        }
        sortedEdges.sort(Comparator.comparingInt(arr -> arr[2]));
        class UnionFind{
            private ArrayList<ArrayList<Integer>> sets;
            private ArrayList<Integer> parents;
            public UnionFind(){
                this.sets = new ArrayList<>();
                this.parents = new ArrayList<>();
                for(int i = 0; i < Graph.this.adjacencyList.size(); i++){
                    this.parents.add(i);
                    this.sets.add(new ArrayList<>());
                }
            }
            public int findParent(int vertex){
                if(this.parents.get(vertex) == vertex){
                    return vertex;
                }else{
                    return this.findParent(this.parents.get(vertex));
                }
            }
            public boolean union(int init, int term){
                int initParent = this.findParent(init);
                int terminalParent = this.findParent(term);
                if(initParent != terminalParent){
                    if(this.sets.get(initParent).size() >=
                            this.sets.get(terminalParent).size()){
                        this.sets.get(initParent).addAll(this.sets.get(terminalParent));
                        this.sets.get(initParent).add(terminalParent);
                        this.parents.set(terminalParent, initParent);
                    }else{
                        this.sets.get(terminalParent).addAll(this.sets.get(initParent));
                        this.sets.get(terminalParent).add(initParent);
                        this.parents.set(initParent, terminalParent);
                    }
                    return true;
                }
                return false;
            }
        }
        UnionFind u = new UnionFind();
        for(int[] arr: sortedEdges){
            if(u.union(arr[0], arr[1])){
                mst.get(arr[0]).add(new int[]{arr[1], arr[2]});
                mst.get(arr[1]).add(new int[]{arr[0], arr[2]});
            }
        }
        return mst;
    }

    public ArrayList<Integer> kahnTopSort(){
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>(this.adjacencyList);
        for(int i = 0; i < graph.size(); i++){
            int degree = this.vertexInDegree(i, graph);
            if(degree == 0) queue.add(i);
        }
        int index = 0;
        ArrayList<Integer> topoOrder = new ArrayList<>();
        while(!queue.isEmpty()){
            int vertex = queue.poll();
            topoOrder.add(vertex);
            index++;
            ArrayList<int[]> arrayList = graph.get(vertex);
            graph.set(vertex, null);
            for(int[] arr: arrayList){
                int newInDegreeNeighbor = this.vertexInDegree(arr[0], graph);
                if(newInDegreeNeighbor == 0 && !queue.contains(arr[0])){
                    queue.add(arr[0]);
                }
            }
        }
        if(index != this.adjacencyList.size()) return null;
        return topoOrder;
    }

    public int greedyColoring(){
        ArrayList<Integer> vertices = new ArrayList<>();
        for(int i = 0; i < this.adjacencyList.size(); i++){
            vertices.add(i);
        }
        vertices.sort(Comparator.comparingInt(i -> -this.vertexOutDegree(i, this.adjacencyList)));
        int largestColor = 1;
        TreeSet<Integer> allColors = new TreeSet<>();
        allColors.add(1);
        ArrayList<Integer> colorizedVertices = new ArrayList<>();
        for(int i = 0; i < this.adjacencyList.size(); i++){
            colorizedVertices.add(-1);
        }
        for(int vertex: vertices){
            TreeSet<Integer> usedColors = new TreeSet<>();
            for(int[] arr: this.adjacencyList.get(vertex)){
                if(colorizedVertices.get(arr[0]) != -1){
                    usedColors.add(colorizedVertices.get(arr[0]));
                }
            }
            if(usedColors.equals(allColors)){
                largestColor++;
                allColors.add(largestColor);
                colorizedVertices.set(vertex, largestColor);
            }else{
                TreeSet<Integer> temp = new TreeSet<>(allColors);
                temp.removeIf(usedColors::contains);
                colorizedVertices.set(vertex, temp.first());
            }
        }
        return allColors.size();
    }
}