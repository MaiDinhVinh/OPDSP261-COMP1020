import java.util.*;

public class Lab10_P1_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
            int n = sc.nextInt();
            int m = sc.nextInt();
            int start = sc.nextInt();
            for(int i = 0; i < n; i++){
                adjList.add(new ArrayList<>());
            }
            for(int i = 0; i < m; i++){
            	int u = sc.nextInt();
            	int v = sc.nextInt();
            	int w = sc.nextInt();
                adjList.get(u)
                        .add(new int[]{v, w});
                adjList.get(v)
                		.add(new int[]{u, w});
            }
            int[] dist = dijkstra(adjList, start);
            for(int i = 0; i < n; i++){
                //if the graph is disconnected or just doesn't have any valid path => the distance stays at \infty
                if(dist[i] == Integer.MAX_VALUE){
                    System.out.printf("%d INF%n", i);
                }else{
                    System.out.printf("%d %d%n", i, dist[i]);
                }
            }
        }
    }
    public static int[] dijkstra(ArrayList<ArrayList<int[]>> graph, int start){
        //this array stores the current distances of the starting node to all nodes in the graph
        int[] dist = new int[graph.size()];
        //we first initialize all distances to \infty except for the startng node
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0; //the distance of the starting node is always 0
        //this queue is used to store the current shortest distance of all nodes for processing
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        //we first add the starting node with dist = 0 to the queue
        queue.add(new int[]{start, 0});
        while(!queue.isEmpty()){
            //we get the current top-most vertex out of the queue
            int[] curr = queue.poll();
            //we get the terminal vertex + edge weight
            int vertex = curr[0], distance = curr[1];
            //lazy deletion, will be explained below
            if(distance > dist[vertex]) continue;
            for(int[] arr: graph.get(vertex)){
                //if the previous distance + new edge weight is smaller than the current shortest distance in the vertex
                //then we assign the latest shortest distance to the array
                if(distance + arr[1] < dist[arr[0]]){
                    dist[arr[0]] = distance + arr[1];
                    //then we add this distance along the current node to the queue for processing
                    //Note: the queue might contain old version of the current node but with larger distance
                    //value. That value will be ignored by the "continue" boolean condition above => lazy deletion
                    queue.add(new int[]{arr[0], distance + arr[1]});
                }
            }
        }
        return dist;
    }
}