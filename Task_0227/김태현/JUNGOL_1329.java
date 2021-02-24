package study0227;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 별 찍기 연습.
 * 문제 유형 : 구현.
 * 요구 개념 : 반복문, 분기문.
 * <주의 사항>
 * INPUT ERROR.. 정올 문제 참..
 */

public class JUNGOL_1329 {
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		if (N % 2 != 1 || N < 1 || N > 99) {
			System.out.println("INPUT ERROR!");
			sc.close();
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < -Math.abs(i - N / 2) + N / 2; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < -Math.abs(2 * i - N + 1) + N; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		sc.close();
	}
}
