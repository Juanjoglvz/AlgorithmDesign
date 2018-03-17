import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("input base");
		double a = sc.nextDouble();
		System.out.println("input a");
		double c = sc.nextDouble();
		System.out.println("input linf");
		double linf = sc.nextDouble();
		System.out.println("input lsup");
		double lsup = sc.nextDouble();
		
		Function f = new Logarithmic(a, c, linf, lsup);
		
		System.out.println("input k");
		int k = sc.nextInt();
		f.calculate(k);
		
		sc.close();
	}

}
