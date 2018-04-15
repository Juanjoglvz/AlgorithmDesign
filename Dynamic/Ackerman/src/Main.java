
public class Main {
	public static void main(String[] args) {
		int cosa = backward(3, 4);
		System.out.println(cosa);
		
	}
	
	public static int recursive(int m, int n) {
		if (m == 0) {
			return n + 1;
		}
		else if (n == 0) {
			return recursive(m - 1, 1);
		}
		else {
			return recursive(m - 1, recursive(m, n - 1));
		}
	}
	
	public static int backward(int m, int n) {
		int[][] list = new int[1000][1000];
		for (int i = 0; i < list.length; i++) {
			list[0][i] = i + 1;
		}
		back(m, n, list);
		
		return list[m][n];
	}
	
	public static void back(int m, int n, int[][] list) {
		if (list[m][n] != 0) {
			return;
		}
		if (n == 0) {
			back(m - 1, 1, list);
			list[m][n] = list[m - 1][1];
		}
		else {
			back(m, n - 1, list);
			int k = list[m][n - 1];
			back(m - 1, k, list);
			list[m][n] = list[m - 1][k];
		}
	}
}
