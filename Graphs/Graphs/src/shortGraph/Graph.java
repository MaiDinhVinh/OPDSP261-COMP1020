package shortGraph;

import java.util.*;

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
        int n = this.adjacencyList.size();
        boolean[] inMst = new boolean[n];
        ArrayList<ArrayList<int[]>> mst = new ArrayList<>();
        for(int i = 0; i < n; i++){
            mst.add(new ArrayList<>());
        }
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

    public void kruskalMst(){
        ArrayList<int[]> sortedEdges = new ArrayList<>();
        for(int i = 0; i < this.adjacencyList.size(); i++){
            for(int[] arr: this.adjacencyList.get(i)){
                sortedEdges.add(new int[]{i, arr[0], arr[1]});
            }
        }
        sortedEdges.sort(Comparator.comparingInt(arr -> arr[2]));
        ArrayList<ArrayList<int[]>> mst = new ArrayList<>(this.adjacencyList.size());
        for(int[] edge: sortedEdges){
            if(mst.get(edge[0]) == null){
                ArrayList<int[]> temp = new ArrayList<>();
                temp.add(new int[]{edge[1], edge[2]});
            }else{
                if(this.checkCycle(mst, edge)){
                    mst.get(edge[0]).add(new int[]{edge[1], edge[2]});
                }
            }
        }
    }


    private boolean checkCycle(ArrayList<ArrayList<int[]>> mst, int[] edge){
        
    }
}