package Jungol;

import java.util.Scanner;

public class JUNGOL_1329 {
	/*
	 * 별찍기3
	 * 
	 * 별찍기가 제일 어렵다
	 */
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		if(N % 2 == 0 || N <= 0 || N > 100)
			System.out.println("INPUT ERROR!");
		else {
			for(int i = 0; i < N/2 + 1; i++) {
				for(int j = 0; j < i; j++)
					System.out.print(" ");
				for(int j = 0; j < 2*(i+1) - 1; j++)
					System.out.print("*");
				System.out.println();
			}
			for(int i = 0; i < N/2; i++) {
				for(int j = N/2; j > i + 1; j--)
					System.out.print(" ");
				for(int j = (N/2) * 2; j > 2*(i + 1) - 1; j--)
					System.out.print("*");
				System.out.println();
			}
		}
	}
}