package s0619;

import java.util.Scanner;

/*
 * <문제 요약>
 * 문제 정의 : 정수 N을 3가지 연산을 통해 1로 만들기 위한 최소 연산 횟수.
 * 문제 유형 : DP(Top-down), DP(Bottom-up)
 * 
 * <풀이 요약 : DP(Top-down) : 실패>
 * 1. 해당 숫자에 도달할 최솟값을 저장할 1차원 DP 테이블을 만든다.
 * 2. X에 대하여 다음 세 가지 재귀를 수행한다.
 * -> 1을 뺀다 : 현재 연산의 개수 + 1 < 해당 숫자로 가는 최소 횟수 라면, 현재 연산의 개수 + 1로 dp 테이블을 갱신한다.
 * -> 3으로 나눈다 : 현재 연산의 개수 + 1 < 해당 숫자로 가는 최소 횟수 라면, 현재 연산의 개수 + 1로 dp 테이블을 갱신한다.
 * -> 2로 나눈다 : 현재 연산의 개수 + 1 < 해당 숫자로 가는 최소 횟수 라면, 현재 연산의 개수 + 1로 dp 테이블을 갱신한다.
 * 3. 이때, 다음 조건에 해당 된다면 해당 재귀는 탈출한다.
 * -> 위의 모든 경우에 대하여 현재 연산의 개수 + 1 >= 해당 숫자로 가는 최소 횟수인 경우.
 * -> 숫자가 0이하가 되는 경우.
 * 4. 모든 경우를 계산하고, dp[1]의 값을 구한다.
 * <피드백>
 * Top-down 방식은 설계하기 쉬운 장점이 있지만, 재귀가 조금만 깊어져도 StackOverflow가 발생한다.
 *
 * <풀이 요약 : DP(Bottom-up)>
 * 1. 해당 숫자에 도달할 최솟값을 저장할 1차원 DP 테이블을 만든다. 
 * 2. 1부터 시작하여, N까지 다음 조건에 따라 dp 테이블을 갱신한다.
 * -> 1을 더한다 : 현재 연산의 개수 + 1 < 해당 숫자로 가는 최소 횟수 라면, 현재 연산의 개수 + 1로 dp 테이블을 갱신한다.
 * -> 2를 곱한다 : 현재 연산의 개수 + 1 < 해당 숫자로 가는 최소 횟수 라면, 현재 연산의 개수 + 1로 dp 테이블을 갱신한다.
 * -> 3을 곱한다 : 현재 연산의 개수 + 1 < 해당 숫자로 가는 최소 횟수 라면, 현재 연산의 개수 + 1로 dp 테이블을 갱신한다.
 * 3. 반복문이 종료되면, dp[N]의 값을 구한다.
 */

public class BOJ_S3_1463_1로만들기 {
	static int N;
	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// dp 테이블 만들기
		dp = new int[N + 1];
		// N을 제외한 모든 영역을 매우 큰 수로 초기화
		for (int i = 0; i < dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[1] = 0;

		// 1부터 시작하여 N까지 dp테이블을 갱신한다.
		for (int i = 1; i <= N; i++) {
			if (i + 1 <= N && dp[i + 1] > dp[i] + 1) {
				dp[i + 1] = dp[i] + 1;
			}
			if (i * 2 <= N && dp[i * 2] > dp[i] + 1) {
				dp[i * 2] = dp[i] + 1;
			}
			if (i * 3 <= N && dp[i * 3] > dp[i] + 1) {
				dp[i * 3] = dp[i] + 1;
			}
		}
		System.out.println(dp[N]);
		sc.close();
	}

//	private static void dfs(int X) {
//		// 0이하가 되는 경우 재귀 종료.
//		if (X <= 0) {
//			return;
//		}
//		// 3으로 나누는 경우
//		if (X % 3 == 0 && dp[X / 3] > dp[X] + 1) {
//			dp[X / 3] = dp[X] + 1;
//			dfs(X / 3);
//		}
//		// 2로 나누는 경우
//		if (X % 2 == 0 && dp[X / 2] > dp[X] + 1) {
//			dp[X / 2] = dp[X] + 1;
//			dfs(X / 2);
//		}
//		// 1을 빼는 경우
//		if (dp[X - 1] > dp[X] + 1) {
//			dp[X - 1] = dp[X] + 1;
//			dfs(X - 1);
//		}
//	}
}
