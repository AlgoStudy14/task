package s0703;

import java.util.Scanner;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : n가지 종류의 동전을 이용하여 k원을 만들 수 있는 경우의 수를 구하여라.
 * 문제 유형 : DP.
 * <풀이 요약>
 * 1. n번째 동전의 k원에서 완성 시킬 수 있는 경우의 수를 저장할 1차원 dp테이블을 만든다. 
 * 2. 첫 번째 동전부터 n번째 동전까지 1원 부터 k원까지 완성 시킬 수 있는 경우의 수를 차례차례 갱신해나간다.
 * ->dp[0]는 0원을 의미하는데, 첫 임의의 X원을 완성시키기 위하여 dp[0] = 1로 초기화한다.
 * <피드백>
 * dp의 갱신 과정은 2차원의 형태를 띄지만, dp테이블은 1차원 테이블 만으로 충분하다.
 */

public class BOJ_S1_2293_동전1_r2 {
	static int n, k;
	static int[] coins;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		coins = new int[n];
		for (int i = 0; i < n; i++) {
			coins[i] = sc.nextInt();
		}

		// k원 + 1만큼 dp테이블을 선언하고, dp[0]는 1로 초기화한다.
		int[] dp = new int[k + 1];
		dp[0] = 1;

		// 첫 번째 동전부터 n번째 동전까지 X원을 만드는 시뮬레이션을 반복한다.
		for (int i = 0; i < n; i++) {
			// 1원 부터 k원까지 동전을 만들 수 있는 경우의 수를 모두 따져본다.
			for (int j = coins[i]; j <= k; j++) {
				// 현재 k원을 만드는 경우의 수 = 현재 k원을 만드는 경우의 수 + 현재 동전의 가치를 뺀 X원을 만드는 경우의 수.
				dp[j] = dp[j] + dp[j - coins[i]];
			}
		}

		System.out.println(dp[k]);
		sc.close();
	}
}
