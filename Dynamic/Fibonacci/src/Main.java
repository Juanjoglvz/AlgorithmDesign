import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("n: ");
		int n = sc.nextInt();
		
		int cosa = recursive(n);
		System.out.println("Resultado factorial: " + cosa);
		cosa = backward(n);
		System.out.println("Resultado Backward: " + cosa);
		cosa = forward(n);
		System.out.println("Resultado Forward: " + cosa);
		
		sc.close();
		
	}
	
	public static int recursive(int n) {
		if (n == 0) {
			return 0;
		}
		else if (n == 1) {
			return 1;
		}
		else {
			return recursive(n - 1) + recursive(n - 2);
		}
		
	}
	
	public static int forward(int n) {
		int[] list = new int[n + 1];
		list[0] = 0;
		list[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			list[i] = list[i - 1] + list[i - 2];
		}
		
		
		return list[n];
	}
	
	public static int backward(int n) {
		int[] list = new int[n + 1];
		back(n, list);
		return list[n];
	}
	
	public static void back(int n, int[] list) {
		if (list[n] != 0) {
			return;
		}
		if (n == 0) {
			list[n] = 0;
		}
		else if (n == 1) {
			list[n] = 1;
		}
		else {
			back(n - 1, list);
			back(n - 2, list);
			list[n] = list[n - 1] + list[n - 2];
		}
	}

}
