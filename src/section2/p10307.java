package section2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/*
 * First challenge is converting the input to a graph (arguably the most difficult part).
 * Second challenge is building an MST of that graph.
 * Lastly, we simply return the sum of all the edge lengths in the MST (i.e. the cost).
 */
public class p10307 {

	public static void main(String[] args) {
		p10307 obj = new p10307();
		obj.run();
	}
	
	private BufferedReader stream;
	
	private void run() {
		stream = new BufferedReader(new InputStreamReader(System.in));
		try {
			int nbrOfTests = Integer.parseInt(stream.readLine());
			for (int i = 0; i < nbrOfTests; i++) {
				String[] size = stream.readLine().split(" ");
				int nbrOfCols = Integer.parseInt(size[0]); // x
				int nbrOfRows = Integer.parseInt(size[1]); // y
				int[][] maze = new int[nbrOfCols][nbrOfRows];
				int id = 2; // Each alien gets a unique ID.
				// Start by reading the input.
				for (int row = 0; row < nbrOfRows; row++) { // There are y lines.
					String line = stream.readLine();
					for (int col = 0; col < nbrOfCols; col++) { // x characters per line.
						switch (line.charAt(col)) {
							case '#':
								maze[col][row] = -2;
								break;
							case ' ':
								maze[col][row] = -1;
								break;
							case 'A':
								maze[col][row] = id++;
								break;
							case 'S':
								maze[col][row] = 1;
								break;
						}
					}
				}
				// Build a graph from the maze, then an MST from that graph.
				System.out.println(MST(buildGraph(maze)).getTotalWeight());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Graph<Integer> buildGraph(int[][] maze) {
		Graph<Integer> g = new Graph<Integer>();
		int nbrOfCols = maze.length;
		for (int col = 0; col < nbrOfCols; col++) {
			int nbrOfRows = maze[col].length;
			for (int row = 0; row < nbrOfRows; row++) {
				if (maze[col][row] > 0) { // Do a BFS from the start point and all aliens.
					int startVertex = maze[col][row];
					int[][] visited = new int[nbrOfCols][nbrOfRows];
					LinkedList<Cell> queue = new LinkedList<Cell>();
					int weight = -1;
					Cell currentCell = new Cell(col, row);
					queue.add(currentCell);
					while (!queue.isEmpty()) {
						int originalSize = queue.size();
						weight++; // Increase weight only once all adjacent cells have been examined (since these all have the same weight).
						for (int i = 0; i < originalSize; i++) {
							currentCell = queue.poll();
							int x = currentCell.getX();
							int y = currentCell.getY();
							if (maze[x + 1][y] != -2 && maze[x + 1][y] != visited[x + 1][y]) { // Cell to the right is unvisited and not a wall.
								queue.add(new Cell(x + 1, y));
								visited[x + 1][y] = maze[x + 1][y];
							}
							if (maze[x - 1][y] != -2 && maze[x - 1][y] != visited[x - 1][y]) { // Cell to the left is unvisited and not a wall.
								queue.add(new Cell(x - 1, y));
								visited[x - 1][y] = maze[x - 1][y];
							}
							if (maze[x][y + 1] != -2 && maze[x][y + 1] != visited[x][y + 1]) { // Cell below is unvisited and not a wall.
								queue.add(new Cell(x, y + 1));
								visited[x][y + 1] = maze[x][y + 1];
							}
							if (maze[x][y - 1] != -2 && maze[x][y - 1] != visited[x][y - 1]) { // Cell above is unvisited and not a wall.
								queue.add(new Cell(x, y - 1));
								visited[x][y - 1] = maze[x][y - 1];
							}
							if (maze[x][y] > 0) {
								g.add(maze[x][y]); // Since this is either the start or an alien, add it to the graph.
								g.addEdge(startVertex, maze[x][y], weight, false); // Add edge between them.
							}
						}
					}
				}
			}
		}
		return g;
	}
	
	private Graph<Integer> MST(Graph<Integer> g) {
		// Prim's.
		Graph<Integer> mst = new Graph<Integer>();
		List<Integer> visited = new LinkedList<Integer>();
		visited.add(1);
		mst.add(1);
		while (mst.size() < g.size()) { // When we have visited all vertices, we're done.
			Edge<Integer> best = null;
			for (int i = 0; i < visited.size(); i++) { // For all visited vertices, examine all edges.
				try {
					int currentVertex = visited.get(i);
					List<Edge<Integer>> adj = g.getAdjacent(currentVertex);
					int count = 0;
					while (count < adj.size()) {
						Edge<Integer> currentEdge = adj.get(count++);
						if (!visited.contains(currentEdge.getChild()) && (best == null || currentEdge.weight < best.weight)) { // If the child vertex isn't already in the MST.
							best = currentEdge;
						}
					}
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
			}
			int vertexToAdd = best.getChild();
			mst.add(vertexToAdd);
			mst.addEdge(best.getSource(), vertexToAdd, best.getWeight(), false);
			visited.add(vertexToAdd);
		}
		return mst;
	}
	
	private class Cell {
		
		private int x;
		private int y;
		
		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	private class Graph<T> {
		
		private Map<T, List<Edge<T>>> adjMap;
		private int size = 0;
		private int totalWeight = 0;

		public Graph() {
			adjMap = new HashMap<T, List<Edge<T>>>();
		}
		
		public int size() {
			return size;
		}
		
		public int getTotalWeight() {
			return totalWeight;
		}
		
		public void add(T vertex) {
			if (!adjMap.containsKey(vertex)) {
				size++;
				adjMap.put(vertex, new LinkedList<Edge<T>>());
			}
		}
		
		public void addEdge(T vertex1, T vertex2, int weight, boolean directed) {
			if (vertex1 == vertex2) {
				return;
			}
			totalWeight += weight;
			List<Edge<T>> adj1 = adjMap.get(vertex1);
			if (adj1 != null && !adj1.contains(vertex2)) {
				adj1.add(new Edge<T>(vertex1, vertex2, weight));
			}
			if (!directed) { // If it's not a directed edge, we use the same weight both ways.
				List<Edge<T>> adj2 = adjMap.get(vertex2);
				if (adj2 != null && !adj2.contains(vertex1)) {
					adj2.add(new Edge<T>(vertex2, vertex1, weight));
				}
			}
		}
		
		public List<Edge<T>> getAdjacent(T vertex) {
			return adjMap.get(vertex);
		}
		
		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			res.append("source: {child (w: edgeWeight)}\n-----------------------\n");
			for (T vertex : adjMap.keySet()) {
				res.append(vertex + ": ");
				List<Edge<T>> adj = adjMap.get(vertex);
				for (int i = 0; i < adj.size(); i++) {
					Edge<T> edge = adj.get(i);
					res.append(edge.getChild() + " (w: " + edge.weight + ')' + (i == adj.size() - 1 ? "\n" : ", "));
				}
			}
			return res.toString();
		}
	}
	
	private class Edge<T> {
		
		private T source;
		private T child;
		private int weight;
		
		public Edge(T source, T child, int weight) {
			this.source = source;
			this.child = child;
			this.weight = weight;
		}
		
		public T getSource() {
			return source;
		}
		
		public T getChild() {
			return child;
		}
		
		public int getWeight() {
			return weight;
		}
		
		public void setWeight(int weight) {
			this.weight = weight;
		}
	}
}
