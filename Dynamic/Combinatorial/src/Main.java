import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("n: ");
		int n = sc.nextInt();
		System.out.println("k: ");
		int k = sc.nextInt();
		
		int cosa = combFact(n, k);
		System.out.println("Resultado factorial: " + cosa);
		cosa = combBack(n, k);
		System.out.println("Resultado Backward: " + cosa);
		cosa = combForw(n, k);
		System.out.println("Resultado Forward: " + cosa);
		
		sc.close();
	}
	
	public static int factorial(int n) {
		int retval = 1;
		for (int i = 1; i <= n; i++) {
			retval *= i;
		}
		return retval;
	}

	public static int combFact(int n, int k) {
		
		return factorial(n)/(factorial(k) * factorial(n-k));
	}
	
	public static int combForw(int n, int k) {
		int[][] pascal = new int[n+1][k+1];
		pascal[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				if (j <= i) {
					if ( j == 0 || j == i) {
						pascal[i][j] = 1;
					}
					else if(j == 1 || j == i - 1) {
						pascal[i][j] = i;
					}
					else {
						pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
					}
				}
			}
		}
		
		return pascal[n][k];
	}
	
	public static int combBack(int n, int k) {
		int[][] pascal = new int[n+1][k+1];
		pascal[0][0] = 1;
		backward(n, k, pascal);
		return pascal[n][k];
	}
	
	public static void backward(int n, int k, int[][] pascal) {
		if (k == 0 || k == n) {
			pascal[n][k] = 1;
		}
		if (k == 1 || k == n - 1) {
			pascal[n][k] = n;
		}
		else {	
			backward(n-1, k-1, pascal);
			backward(n-1, k, pascal);
			pascal[n][k] = pascal[n-1][k-1] + pascal[n-1][k];
		}
	}
}
