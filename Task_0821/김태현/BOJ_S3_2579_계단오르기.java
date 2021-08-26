package ss;

import java.util.Scanner;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 가장 높은 점수를 얻으며 계단을 밟는 방법은?
 * 문제 유형 : 다이나믹 프로그래밍.
 * <풀이 요약>
 * 1. 각 층에서 얻을 수 있는 최대 값을 저장할 1차원 dp 테이블을 만든다.
 * 2. 연속으로 두 계단을 밟은 경우와, 한 계단만 밟는 경우를 나누어서 계산하며 갱신한다.
 * 
 */

public class BOJ_S3_2579_계단오르기 {
	static int N;
	static int[] stair, dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		stair = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			stair[i] = sc.nextInt();
		}

		dp = new int[N + 1];
		if (N >= 1) {
			// 최초의 계단은 자신의 값으로 초기화
			dp[1] = stair[1];
		}
		if (N >= 2) {
			// 두 번째 계단은 직접 계산
			dp[2] = Math.max(dp[0], dp[1]) + stair[2];
		}
		if (N >= 3) {
			// 두 번째 계단부터 dp 진행.
			for (int i = 3; i <= N; i++) {
				dp[i] = Math.max(dp[i - 2] + stair[i], dp[i - 3] + stair[i - 1] + stair[i]);
			}
		}

		// 최종 계단의 값 출력
		System.out.println(dp[N]);
		sc.close();
	}
}
