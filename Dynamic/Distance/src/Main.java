import java.util.ArrayList;

public class Main {
	static class Node{
		public int vertex;
		public int value;
		public Node succ;
		
		public Node(int vertex) {
			this.vertex = vertex;
			this.value = 10000;
			
		}
		
		public Node(int vertex, int value) {
			this.vertex = vertex;
			this.value = value;
		}
		
		public boolean equals(Object o) {
			return o instanceof Node && ((Node)o).vertex == this.vertex;
		}
	}
	
	public static void main(String[] args) {
		Grafo<Integer, Integer> g = new Grafo<Integer, Integer>(10, true);
		for (int i = 1; i <= 10; i++) {
			g.nuevoVertice(i);
		}
		
		g.nuevoArco(1, 2, 5);
		g.nuevoArco(1, 3, 7);
		g.nuevoArco(1, 4, 2);
		g.nuevoArco(2, 5, 3);
		g.nuevoArco(2, 6, 1);
		g.nuevoArco(3, 5, 4);
		g.nuevoArco(4, 5, 5);
		g.nuevoArco(4, 6, 9);
		g.nuevoArco(5, 7, 8);
		g.nuevoArco(5, 8, 11);
		g.nuevoArco(5, 9, 6);
		g.nuevoArco(6, 8, 4);
		g.nuevoArco(7, 10, 5);
		g.nuevoArco(8, 10, 9);
		g.nuevoArco(9, 10, 12);
		
		System.out.println(forward(g));
		
	}
	
	public static ArrayList<Integer> forward(Grafo<Integer, Integer> g){
		ArrayList<Node> nodelist = new ArrayList<Node>();
		Node initial = new Node(1, 0);
		nodelist.add(initial);
		int counter = 0;
		
		while (true) {
			Node current = nodelist.get(counter);
			if (current.vertex == 10) {
				break;
			}
			ArrayList<Integer> foo = g.adyacentes(current.vertex);
			ArrayList<Node> adjacents = new ArrayList<Node>();
			for (int i : foo) {
				adjacents.add(new Node(i));
			}
			
			for (Node adjacent : adjacents) {
				if (nodelist.contains(adjacent)) {
					adjacent = nodelist.get(nodelist.indexOf(adjacent));
				}
				else {
					nodelist.add(adjacent);
				}
				if (current.value + g.peso(current.vertex, adjacent.vertex) < adjacent.value) {
					adjacent.value = current.value + g.peso(current.vertex, adjacent.vertex);
					adjacent.succ = current;
				}
			}
			counter++;
		}
		
		ArrayList<Integer> solution = new ArrayList<Integer>();
		initial = nodelist.get(nodelist.indexOf(new Node(10)));
		solution.add(initial.vertex);
		Node next = initial.succ;
		while (next.succ != null) {
			solution.add(next.vertex);
			next = next.succ;
		}
		solution.add(next.vertex);
		return solution;
	}
	
	public static ArrayList<Integer> backward(Grafo<Integer, Integer> g){
		
		ArrayList<Node> nodelist = new ArrayList<Node>();
		Node initial = new Node(1);
		nodelist.add(initial);
		back(nodelist, initial, g);
		
		ArrayList<Integer> solution = new ArrayList<Integer>();
		solution.add(initial.vertex);
		
		Node next = initial.succ;
		while (next.succ != null) {
			solution.add(next.vertex);
			next = next.succ;
		}
		solution.add(next.vertex);
		return solution;
	}
	
	public static void back(ArrayList<Node> nodelist, Node current, Grafo<Integer, Integer> g) {
		if (current.vertex == 10) {
			return;
		}
		else {
			int best = -1;
			ArrayList<Integer> foo = g.adyacentes(current.vertex);
			ArrayList<Node> adjacents = new ArrayList<Node>();
			for (int i : foo) {
				adjacents.add(new Node(i));
			}
			
			for (Node adjacent : adjacents) {
				if (nodelist.contains(adjacent)) {
					adjacent = nodelist.get(nodelist.indexOf(adjacent));
				}
				else {
					nodelist.add(adjacent);
					back(nodelist, adjacent, g);
				}
				
				if (g.peso(current.vertex, adjacent.vertex) + adjacent.value < current.value) {
					current.value = g.peso(current.vertex, adjacent.vertex) + adjacent.value;
					current.succ = adjacent;
				}
				
				
			}
		}
		
	}
}
