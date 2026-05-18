import java.util.*;

public class Lab10_P2_V202502959 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
            int n = sc.nextInt();
            int m = sc.nextInt();
            int start = sc.nextInt();
            int target = sc.nextInt();

            for(int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for(int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                adjList.get(u).add(new int[]{v, w});
                adjList.get(v).add(new int[]{u, w});
            }
            ArrayList<int[]> results = dijkstra(adjList, start);
            if(results.get(0)[target] == Integer.MAX_VALUE) {
                System.out.println(-1);
            }else {
                System.out.println(results.get(0)[target]);
                int a = target;
                int[] par = results.get(1);
                ArrayList<Integer> order = new ArrayList<>();
                //trace back from target to start using the predecessor array
                while(a != start) {
                    order.add(a);
                    a = par[a]; //jump to the predecessor of the current node
                }
                order.add(a); //add the start node itself
                //the list is in reverse order (target -> start), so print it backwards
                for(int i = order.size() - 1; i >= 0; i--) {
                    System.out.print(i > 0 ? order.get(i) + " " : order.get(i));
                }
            }
        }
    }
    public static ArrayList<int[]> dijkstra(ArrayList<ArrayList<int[]>> graph, int start){
        //this array stores the current shortest distances from start to all other nodes
        int[] dist = new int[graph.size()];
        //par[i] stores which node we came from to reach node i on the shortest path
        int[] par = new int[graph.size()];
        //initialize all distances and predecessors to \infty except for the starting node
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(par, Integer.MAX_VALUE);
        dist[start] = 0; //distance to itself is always 0
        par[start] = start; //predecessor of start points to itself (used as the loop termination signal)
        //min-heap ordered by distance — always process the closest unvisited node first
        //Comparator.comparingInt is a Java API shorthand for writing a comparator that extracts an int key
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        queue.add(new int[]{start, 0});
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int vertex = curr[0], distance = curr[1];
            //lazy deletion: if we already found a shorter path to this vertex, skip this stale entry
            if(distance > dist[vertex]) continue;
            for(int[] arr: graph.get(vertex)){
                //relax edge: if going through current vertex gives a shorter path, update it
                if(distance + arr[1] < dist[arr[0]]){
                    dist[arr[0]] = distance + arr[1];
                    par[arr[0]] = vertex; //record that the best way to reach arr[0] is from vertex
                    //push the updated distance — old entry will be skipped by lazy deletion when popped
                    queue.add(new int[]{arr[0], distance + arr[1]});
                }
            }
        }
        //pack both arrays into one list so we can return both dist and par together
        ArrayList<int[]> results = new ArrayList<>();
        results.add(dist);
        results.add(par);
        return results;
    }
}