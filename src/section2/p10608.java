package section2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class p10608 {

	public static void main(String[] args) {
		p10608 obj = new p10608();
		obj.run();
	}	
	
	private BufferedReader stream;
	private List<Integer> visited;
	
	public void run() {
		stream = new BufferedReader(new InputStreamReader(System.in));
		try {
			int nbrOfTests = Integer.parseInt(stream.readLine());
			for (int i = 0; i < nbrOfTests; i++) {
				visited = new ArrayList<Integer>();
				Graph<Integer> graph = new Graph<Integer>();
				List<Integer> people = new ArrayList<Integer>(); // We are going to start a DFS from every unvisited vertex.
				String[] line = stream.readLine().split(" "); // N and M.
				int n = Integer.parseInt(line[0]); // This many people.
				int m = Integer.parseInt(line[1]); // This many lines/pairs of friends.
				for (int j = 0; j < m; j++) { // Build graph.
					String[] friends = stream.readLine().split(" ");
					int p1 = Integer.parseInt(friends[0]);
					int p2 = Integer.parseInt(friends[1]);
					graph.add(p1);
					graph.add(p2);
					graph.addEdge(p1, p2);
					if (!people.contains(p1)) {
						people.add(p1);
					}
					if (!people.contains(p2)) {
						people.add(p2);
					}
				}
				int count = 1;
				for (int j = 0; j < people.size(); j++) { // Do a DFS of all subgraphs (visit all nodes exactly once and count connections).
					int current = people.get(j);
					if (!visited.contains(current)) {
						count = Math.max(count, DFS(graph, people.get(j)));
					}
					if (count >= n / 2) { // Doesn't matter if we count the other graphs, this will be biggest.
						break;
					}
				}
				System.out.println(count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int DFS(Graph<Integer> graph, int current) {
		int count = 0;
		visited.add(current);
		List<Integer> adj = graph.getAdjacent(current);
		for (int person : adj) {
			if (!visited.contains(person)) {
				count += DFS(graph, person);
			}
		}
		return count + 1;
	}
	
	private class Graph<T> {
		
		private Map<T, List<T>> adjMap;

		public Graph() {
			adjMap = new HashMap<T, List<T>>();
		}
		
		public void add(T vertex) {
			if (!adjMap.containsKey(vertex)) {
				adjMap.put(vertex, new LinkedList<T>());
			}
		}
		
		public void addEdge(T vertex1, T vertex2) {
			List<T> adj1 = adjMap.get(vertex1);
			if (adj1 != null && !adj1.contains(vertex2)) {
				adj1.add(vertex2);
			}
			List<T> adj2 = adjMap.get(vertex2);
			if (adj2 != null && !adj2.contains(vertex1)) {
				adj2.add(vertex1);
			}
		}
		
		public List<T> getAdjacent(T vertex) {
			return adjMap.get(vertex);
		}
		
		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			for (T vertex : adjMap.keySet()) {
				res.append(vertex + ": ");
				List<T> adj = adjMap.get(vertex);
				for (int i = 0; i < adj.size(); i++) {
					res.append(adj.get(i) + (i == adj.size() - 1 ? "\n" : ", "));
				}
			}
			return res.toString();
		}
	}
}
