package algo0709;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : M개의 카드에 대해 상근이가 갖고 있는지 출력
 * 문제 유형 : 그리디..?
 * 주의 사항 : 
 * <풀이 요약>
 * 음수, 양수 나눠서 해당 숫자가 존재하는지 have 배열의 인덱스로 접근
 */

public class BOJ_S4_10815_숫자카드 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean[][] have = new boolean[2][10000000];
		
		for (int i = 0; i < N; i++) {
			int input = sc.nextInt();
			if (input < 0) {
				have[0][-input] = true;
			} else {
				have[1][input] = true;
			}
		}
		
		int M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int input = sc.nextInt();
			if (input < 0) {
				System.out.print(have[0][-input] ? "1 " : "0 ");
			} else {
				System.out.print(have[1][input] ? "1 " : "0 ");
			}
		}
	}

}
