import java.util.ArrayList;
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
	
	public static boolean isEven(int n) {
		return (n % 2 == 0);
	}
	
	public static int recursive(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		else {
			if (isEven(n)) {
				return recursive(n + 1);
			}
			else {
				int k = (int) n / 2;
				return recursive(k) + recursive(k + 1);
			}
		}
	}
	
	public static int forward(int n) {
		int[] list = new int[n + 2];
		ArrayList<Integer> toCalculate = new ArrayList<Integer>();
		list[1] = 1;
		list[2] = 1;
		
		int index = 3;
		while (index <= n + 2) {
			//Adjacents
			if (isEven(index)) {
				int k = index * 2 + 1;
				if (!toCalculate.contains(k))
					toCalculate.add(k);
			}
			else {
				int k = (index - 1) * 2 + 1;
				if (!toCalculate.contains(k))
					toCalculate.add(k);
				k = index * 2 + 1;
				if (!toCalculate.contains(k))
					toCalculate.add(k);
			}
			
			//Calculate
			int x = (int) index / 2;
			list[index] = list[x] + list[x + 1];
			if (index > 3) {
				list[index - 1] = list[index];
			}
			if (index == n || index - 1 == n) {
				break;
			}
			index = toCalculate.remove(0);
			
		}
		
		if (isEven(n)) {
			list[n] = list[n + 1];
		}
		
		return list[n];
	}
	
	public static int backward(int n) {
		int[] list = new int[n + 2];
		back(n, list);
		
		return list[n];
	}
	
	public static void back(int n, int[] list) {
		if (n == 1 || n == 2) {
			list[n] = 1;
		}
		else if (isEven(n)) {
			back(n+1, list);
			list[n] = list[n + 1];
		}
		else {
			int k = (int) (n) / 2;
			back(k, list);
			back(k + 1, list);
			list[n] = list[k] + list[k + 1];
		}
		
	}

}
