package study0227;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 별 찍기 연습.
 * 문제 유형 : 구현.
 * 요구 개념 : 반복문, 분기문.
 * <주의 사항>
 * INPUT ERROR! 뭔데..
 */

public class JUNGOL_1719 {
	static int n, m;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		if(n % 2 != 1 || m < 1 || m > 4) {
			System.out.println("INPUT ERROR!");
			sc.close();
			return;
		}

		switch (m) {
		case 1:
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < -Math.abs(i - n / 2) + n / 2 + 1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 2:
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < Math.abs(i - n / 2); j++) {
					System.out.print(" ");
				}
				for (int j = 0; j < -Math.abs(i - n / 2) + n / 2 + 1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 3:
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < -Math.abs(i - n / 2) + n / 2; j++) {
					System.out.print(" ");
				}
				for (int j = 0; j < Math.abs(2 * i - n + 1) + 1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		case 4:
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (j >= n / 2) {
						break;
					}
					System.out.print(" ");
				}
				for (int j = 0; j < Math.abs(i - n / 2) + 1; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			break;
		}
		sc.close();
	}
}
