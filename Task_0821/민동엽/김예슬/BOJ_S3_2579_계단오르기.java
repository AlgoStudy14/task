package algo0713;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 계단을 밟아 얻을 수 있는 총 점수의 최댓값 출력
 * 문제 유형 : DP
 * <풀이 요약>
 * D[n][1]: 한 칸씩 오를 경우
 * D[n][2]: 두 칸씩 오를 경우 
 */

public class BOJ_2579_계단오르기_2차원배열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stairs = new int[N + 1];
		int[][] D = new int[N + 1][3];
		
		for (int i = 0; i < N; i++)
			stairs[i] = sc.nextInt();
		
		D[0][1] = stairs[0];
		D[0][2] = 0;
		D[1][1] = stairs[0] + stairs[1];		
		D[1][2] = stairs[1];
		for (int i = 2; i < N; i++) {
			// 이전에 두 칸 올랐을 경우만 한 칸 이동 가능
			D[i][1] = D[i - 1][2] + stairs[i];
			// 두 칸을 이동할 경우 이전 조건 상관X
			D[i][2] = Math.max(D[i - 2][1], D[i - 2][2]) + stairs[i];
		}
		
		System.out.println(Math.max(D[N - 1][1], D[N - 1][2]));
	}
}
