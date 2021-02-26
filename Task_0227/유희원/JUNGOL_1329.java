package imprepare.problems;

import java.util.Scanner;

public class JO_1329_별삼각형3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if(N>100||N%2==0) {
			System.out.println("INPUT ERROR!");
			System.exit(0);
		}
		for (int i = 0; i <N; i++) {
			for (int j = 0; j <N/2-Math.abs(i-N/2); j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*(N/2-Math.abs(i-N/2))+1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}

}
