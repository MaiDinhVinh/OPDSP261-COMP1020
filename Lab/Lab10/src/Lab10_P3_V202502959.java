import java.util.*;

public class Lab10_P3_V202502959 {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
			int n = sc.nextInt();
			int m = sc.nextInt();

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
			ArrayList<ArrayList<int[]>> mst = kruskalMst(adjList);
			int minW = 0;
			int numEdges = 0;
			//count total edges and sum up weights from the MST adjacency list
			for(ArrayList<int[]> edges: mst) {
				numEdges += edges.size();
				for(int[] arr: edges) {
					minW += arr[1];
				}
			}
			//a valid spanning tree must have exactly n-1 edges; fewer means graph is disconnected
			if(numEdges == n - 1) {
				System.out.println(minW);
			}else {
				System.out.println(-1);
			}
		}
	}
	public static ArrayList<ArrayList<int[]>> kruskalMst(ArrayList<ArrayList<int[]>> graph) {
		ArrayList<int[]> sortedEdges = new ArrayList<>();
		ArrayList<ArrayList<int[]>> mst = new ArrayList<>();
		//flatten the adjacency list into a single edge list, storing {from, to, weight}
		for(int i = 0; i < graph.size(); i++) {
			for(int[] arr: graph.get(i)) {
				sortedEdges.add(new int[]{i, arr[0], arr[1]});
			}
			mst.add(new ArrayList<>());
		}
		//sort edges by weight ascending — Comparator.comparingInt is a Java API shorthand for a weight-based comparator
		sortedEdges.sort(Comparator.comparingInt(arr -> arr[2]));
		//UnionFind is defined as a local class here since it's only needed inside this method
		class UnionFind{
			private ArrayList<ArrayList<Integer>> set;
			private ArrayList<Integer> parents;

			public UnionFind(int size) {
				this.set = new ArrayList<>();
				this.parents = new ArrayList<>();
				//initially each node is its own parent (its own component)
				for(int i = 0; i < size; i++) {
					this.set.add(new ArrayList<>());
					this.parents.add(i);
				}
			}

			//walk up the parent chain until we hit a node that is its own parent (the root)
			public int findParent(int vertex) {
				if(this.parents.get(vertex) == vertex) return vertex;
				else return this.findParent(this.parents.get(vertex));
			}

			public boolean union(int initial, int terminal) {
				int initialParent = this.findParent(initial);
				int terminalParent = this.findParent(terminal);
				if(initialParent != terminalParent) {
					//union by size: merge the smaller component into the larger one to keep the tree shallow
					if(this.set.get(initialParent).size() >= this.set.get(terminalParent).size()) {
						this.set.get(initialParent).addAll(this.set.get(terminalParent));
						this.set.get(initialParent).add(terminalParent);
						this.parents.set(terminalParent, initialParent);
					}else {
						this.set.get(terminalParent).addAll(this.set.get(initialParent));
						this.set.get(terminalParent).add(initialParent);
						this.parents.set(initialParent, terminalParent);
					}
					return true;
				}
				return false; //both nodes share the same root => adding this edge would create a cycle
			}
		}
		UnionFind u = new UnionFind(graph.size());
		for(int[] arr: sortedEdges) {
			//only add this edge to the MST if it connects two different components (no cycle)
			if(u.union(arr[0], arr[1])) {
				mst.get(arr[0]).add(new int[]{arr[1], arr[2]});
			}
		}
		return mst;
	}
}