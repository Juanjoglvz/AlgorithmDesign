import java.util.ArrayList;
import java.util.TreeSet;


public class Main {
	static String[] alphabet = {"a", "b", "c"};
	static ArrayList<String> visited = new ArrayList<String>();
	
	static class Node{
		String value;
		Node succ;
		public Node(String value) {
			this.value = value;
		}
		
		public boolean equals(Object o) {
			return o instanceof Node && ((Node) o).value.equals(this.value);
		}
	}
	
	public static void main(String[] args) {
		String initial = "aaa";
		String objective = "abc";
		
		System.out.println(backward(initial, objective));
	}
	
	public static ArrayList<String> backward(String initial, String objective){
		ArrayList<Node> nodelist = new ArrayList<Node>();
		Node first = new Node(initial);
		nodelist.add(first);
		
		back(initial, objective, first, nodelist);
		
		ArrayList<String> solution = new ArrayList<String>();
		Node next = first;
		
		while (next != null) {
			solution.add(next.value);
			next = next.succ;
		}
		
		return solution;
	}
	
	public static void back(String initial, String objective, Node current, ArrayList<Node> nodelist) {
		if (current.value.equals(objective)) {
			return;
		}
		else {
			ArrayList<Node> adjacents = new ArrayList<Node>();
			TreeSet<String> foo = new TreeSet<String>();
			
			
			for (int i = 0; i < current.value.length(); i++) {
				for (int j = 0; j < alphabet.length; j++) {
					String foo1 = current.value.substring(0, i);
					String foo2 = current.value.substring(i + 1, current.value.length());
					
					String adjacent = foo1 + alphabet[j] + foo2;
					if (!adjacent.equals(current.value) )
						foo.add(adjacent);
				}
			}
			
			for (String fooString : foo) {
				adjacents.add(new Node(fooString));
			}
			
			
			for (Node adjacent : adjacents) {
				if (nodelist.contains(adjacent)) {
					adjacent = nodelist.get(nodelist.indexOf(adjacent));
				}
				else {
					nodelist.add(adjacent);
					back(initial, objective, adjacent, nodelist);
				}
				
				if (current.succ == null) {
					current.succ = adjacent;
				}
				else {
					if (isBetter(adjacent, current.succ, objective)) {
						current.succ = adjacent;
					}
					
				}
				
			}
			
			
		}
	}
	
	public static boolean isBetter(Node n1, Node n2, String objective) {
		if (n1.value.equals("")) {
			return false;
		}
		else if(n2.value.equals("")) {
			return true;
		}
		
		int c1 = 0;
		int c2 = 0;
		
		int k1 = objective.length() - n1.value.length();
		int k2 = objective.length() - n2.value.length();
		
		for (int i = 0; i < k1; i++) {
			String foo = objective.substring(i, i + (objective.length() - k1));
			int c1_s = 0;
			
			for (int j = 0; j < foo.length(); j++) {
				if (n1.value.charAt(j) == foo.charAt(j)) {
					c1_s++;
				}
			}
			
			c1 += c1_s;
			
		}
		for (int i = 0; i < k2; i++) {
			String foo = objective.substring(i, i + (objective.length() - k2));
			int c2_s = 0;
			
			for (int j = 0; j < foo.length(); j++) {
				if (n2.value.charAt(j) == foo.charAt(j)) {
					c2_s++;
				}
			}
			
			c2 += c2_s;
			
		}
	
		return c1 >= c2;
	}
}
