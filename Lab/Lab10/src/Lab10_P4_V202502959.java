import java.util.*;

public class Lab10_P4_V202502959 {
	static int[] parent, rank;

	//path compression: flatten the tree while finding root so future finds are faster
	static int find(int x) {
		if (parent[x] != x) parent[x] = find(parent[x]);
		return parent[x];
	}

	static boolean union(int x, int y) {
		int px = find(x), py = find(y);
		if (px == py) return false; //already in the same component, skip
		//union by rank: attach the shorter tree under the taller one
		if (rank[px] < rank[py]) { int t = px; px = py; py = t; }
		parent[py] = px;
		if (rank[px] == rank[py]) rank[px]++; //only increase rank when both trees had the same height
		return true;
	}

	//simple find without path compression, used to query the SAVED parent state (snapshot)
	static int findArr(int[] par, int x) {
		while (par[x] != x) x = par[x];
		return x;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt();

		int[][] edges = new int[m][3];
		for (int i = 0; i < m; i++) {
			edges[i][0] = sc.nextInt();
			edges[i][1] = sc.nextInt();
			edges[i][2] = sc.nextInt();
		}
		//(a, b) -> a[2] - b[2] is a lambda comparator — sorts edges by weight ascending
		Arrays.sort(edges, (a, b) -> a[2] - b[2]);
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) parent[i] = i;

		boolean notUnique = false;
		int i = 0;
		while (i < m) {
			int w = edges[i][2];
			int j = i;
			//find the range [i, j) of all edges with the same weight
			while (j < m && edges[j][2] == w) j++;

			//snapshot the union-find state BEFORE processing this weight group
			//we need this to check if any skipped edge was actually an alternative MST edge
			int[] savedParent = parent.clone(); //.clone() is a Java array API — copies the array
			int[] savedRank   = rank.clone();
			boolean[] added = new boolean[j - i]; //tracks which edges in this batch actually got added

			//try to add all edges of this weight — standard Kruskal step
			for (int k = i; k < j; k++) {
				if (union(edges[k][0], edges[k][1])) {
					added[k - i] = true;
				}
			}

			if (!notUnique) {
				for (int k = i; k < j; k++) {
					if (!added[k - i]) {
						//this edge was NOT added (would create a cycle in the final MST)
						//but check: in the state BEFORE this batch, did it connect two different components?
						//if yes, it means another edge of equal weight was chosen over this one => multiple MSTs
						int pu = findArr(savedParent, edges[k][0]);
						int pv = findArr(savedParent, edges[k][1]);
						if (pu != pv) {
							notUnique = true;
							break;
						}
					}
				}
			}
			i = j; //move to the next weight group
		}

		//check connectivity: all nodes must share the same root after kruskal
		int root = find(0);
		for (int v = 1; v < n; v++) {
			if (find(v) != root) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(notUnique ? "NO" : "YES");
	}
}