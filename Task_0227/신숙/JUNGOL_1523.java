package Jungol;

import java.util.Scanner;

public class JUNGOL_1523 {
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		if(N > 100 || N <= 0 || M > 3 || M < 1)
			System.out.println("INPUT ERROR!");
		else{
			switch(M) {
				case 1:
					for(int i = 0; i < N; i++) {
						for(int j = 0; j <= i; j++)
							System.out.print("*");
						System.out.println();
					}
					break;
				case 2:
					for(int i = 0; i < N; i++) {
						for(int j = N; j > i; j--)
							System.out.print("*");
						System.out.println();
					}
					break;
				case 3:
					for(int i = 0; i < N; i++) {
						for(int j = N - 1; j > i; j--)
							System.out.print(" ");
						for(int j = 0; j < 2* (i+1) - 1; j++)
							System.out.print("*");
						System.out.println();
					}
					break;
			}
		}
	}
}
