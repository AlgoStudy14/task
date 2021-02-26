package imprepare.problems;

import java.util.Scanner;

public class JO_1719_별삼각형2 {

	static int n,m;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		if(n>100||n%2==0) {
			System.out.println("INPUT ERROR!");
			System.exit(0);
		}
		triangle(n,m);

	}
	private static void triangle(int height, int num) {
		switch (num) {
		case 1: 
			for (int i = 0; i <height; i++) {
				for (int j = 0; j <height/2-Math.abs(i-height/2)+1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 2:
			for (int i = 0; i <height; i++) {
				for (int j =0; j<Math.abs(i-height/2); j++) {
					System.out.print(" ");
				}
				for (int j = 0; j <height/2-Math.abs(i-height/2)+1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 3:
			for (int i = 0; i <height; i++) {
				for (int j = 0; j <height/2-Math.abs(i-height/2); j++) {
					System.out.print(" ");
				}
				for (int j =0; j<height-2*(height/2-Math.abs(i-height/2)); j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 4:
			for (int i = 0; i <height/2; i++) {
				for (int j = 0; j <i; j++) {
					System.out.print(" ");
				}
				for (int k = 0; k < height/2-i+1; k++) {
					System.out.print("*");
				}
				System.out.println();
			}
			for (int i = height/2; i <height; i++) {
				for (int j = 0; j < height/2; j++) {
					System.out.print(" ");
				}
				for (int j = 0; j < i-height/2+1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		
		default: 
			System.out.println("INPUT ERROR!");
			System.exit(0);
			break;
		}
		
	}

}
