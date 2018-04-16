import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static class Node{
		public int value;
		public int[] currentPlay;
		public Node succ;
		public boolean exactSol = false;
		
		public Node(int value) {
			this.value = value;
			this.currentPlay = new int[4];
		}
		
		public Node(int value, int[] currentPlay) {
			this.value = value;
			this.currentPlay = currentPlay;
		}
		
		public boolean equals(Object o) {
			return o instanceof Node && ((Node) o).value == this.value && Arrays.equals(((Node) o).currentPlay, this.currentPlay);
		}
	}
	
	public static void main(String[] args) {
		int[] zones = {10, 25, 50, 75};
		int score = 65;
		
		int[] foo = forward(score, zones);
		for (int i = 0; i < foo.length; i++) {
			System.out.print(foo[i]);
		}
	}
	
	public static int[] forward(int score, int[] zones) {
		ArrayList<Node> nodelist = new ArrayList<Node>();
		Node initial = new Node(score);
		nodelist.add(initial);
		
		int index = 0;
		
		while (true) {
			Node current = nodelist.get(index);
			
			if (current.value < 10) {
				break;
			}
			
			ArrayList<Node> adjacents = new ArrayList<Node>();
			
			for (int i = 0; i < zones.length; i++) {
				int[] foo = current.currentPlay.clone();
				foo[i]++;
				Node cosa = new Node(current.value - zones[i], foo);
				if (cosa.value >= 0) {
					adjacents.add(cosa);
				}
			}
			
			for (Node adjacent : adjacents) {
				if (nodelist.contains(adjacent)) {
					adjacent = nodelist.get(nodelist.indexOf(adjacent));
				}
				else {
					nodelist.add(adjacent);
				}
				
				if (adjacent.succ == null) {
					adjacent.succ = current;
				}
				else {
					if (isBetter(current,adjacent.succ)) {
						adjacent.succ = current;
					}
				}
			}
			
			index++;
			
		}
		
		
		
		return nodelist.get(index).currentPlay;
	}
	
	
	public static int[] backward(int score, int[] zones) {
		ArrayList<Node> nodelist = new ArrayList<Node>();
		Node initial = new Node(score);
		nodelist.add(initial);
		
		back(score, zones, initial, nodelist);
		
		Node next = initial.succ;
		while (next.succ != null) {
			next = next.succ;
		}
		
		return next.currentPlay;
		
	}
	
	public static void back(int score, int[] zones, Node current, ArrayList<Node> nodelist) {
		if (current.value < zones[0]) {
			return;
		}
		else{
			ArrayList<Node> adjacents = new ArrayList<Node>();
			
			for (int i = 0; i < zones.length; i++) {
				int[] foo = current.currentPlay.clone();
				foo[i]++;
				Node cosa = new Node(current.value - zones[i], foo);
				if (cosa.value >= 0) {
					adjacents.add(cosa);
				}
			}
			
			for (Node adjacent : adjacents) {
				if (nodelist.contains(adjacent)) {
					adjacent = nodelist.get(nodelist.indexOf(adjacent));
				}
				else {
					nodelist.add(adjacent);
					back(score, zones, adjacent, nodelist);
				}
				if (current.succ == null) {
					current.succ = adjacent;
				}
				else {
					if (isBetter(adjacent, current.succ)) {
						current.succ = adjacent;
					}
				}
				if (current.succ.exactSol || current.succ.value == 0) {
					current.exactSol = true;
				}
				
				
			}
			
		}
	
		
	}
	
	public static boolean isBetter(Node n1, Node n2) {
		if (n2.exactSol && !n1.exactSol) {
			return false;
		}
		else if(n2.exactSol && n1.exactSol){
			int c1 = 0;
			int c2 = 0;
			for (int i = 0; i < n1.currentPlay.length; i++) {
				c1 += n1.currentPlay[i];
				c2 += n2.currentPlay[i];
			}
			
			return c1 <= c2;
		}
		
		return true;
	}
}
