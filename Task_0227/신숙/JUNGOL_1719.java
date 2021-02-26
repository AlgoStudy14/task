package Jungol;

import java.util.Scanner;

public class JUNGOL_1719 {
	static int N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		if(N > 100 || N % 2 == 0 || M < 1 || M > 4)
			System.out.println("INPUT ERROR!");
		else {
			switch(M) {
				case 1 :
					for(int j = 0; j < N/2 + 1; j++) {
						for(int i = 0; i <= j; i++)
							System.out.print("*");
						System.out.println();
					}
					for(int i = N/2 + 1; i < N; i++) {
						for(int j = N - i; j > 0; j--)
							System.out.print("*");
						System.out.println();
					}
					break;
				case 2:
					for(int I = 0; I < N/2 + 1; I++) {
						for(int J = N/2; J > I; J--)
							System.out.print(" ");
						for(int j = 0; j <= I; j++)
							System.out.print("*");
						System.out.println();
					}
					for(int i = 0; i < N/2; i++) {
						for(int j = 0; j <= i; j++)
							System.out.print(" ");
						for(int j = N/2; j > i; j--)
							System.out.print("*");
						System.out.println();
					}
					break;
				case 3:
					for(int i = 0; i < N/2 + 1; i++) {
						for(int j = 0; j < i; j++)
							System.out.print(" ");
						for(int j = N; j > 2*i; j--)
							System.out.print("*");
						System.out.println();
					}
					for(int i = 0; i < N/2; i++) {
						for(int J = N/2; J > i + 1; J--)
							System.out.print(" ");
						for(int j = 0; j < 2*i + 3; j++)
							System.out.print("*");
						System.out.println();
					}
					break;
				case 4: 
					for(int i = 0; i < N/2 + 1; i++) {
						for(int j = 0; j < i; j++)
							System.out.print(" ");
						for(int j = N/2+1; j > i; j--)
							System.out.print("*");
						System.out.println();
					}
					for(int i = 0; i < N/2; i++) {
						for(int j = 0; j < N/2; j++)
							System.out.print(" ");
						for(int j = 0; j <= i + 1; j++)
							System.out.print("*");
						System.out.println();
					}
					break;
			}
		}
	}
}
