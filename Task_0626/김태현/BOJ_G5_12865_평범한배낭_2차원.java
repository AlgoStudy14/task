package s0626;

import java.util.Scanner;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 배낭에 넣을 수 있는 물건들의 가치의 최댓값.
 * 문제 유형 : DP.
 * 
 * <풀이 요약 : DP : 실패>
 * 1. 각 무게당 가질 수 있는 가치의 최대합을 저장할 1차원 dp테이블을 만든다.
 * 2. 점화식.
 * -> dp[k] = Math.max(dp[k], dp[k - W] + 물건의 가치)해당 점화식을 모든 물건에 대하여 계산해보며 값을 갱신한다.
 * 3. dp[K - 1]을 출력한다.
 * <피드백>
 * 해당 방법을 바탕으로 계산하면, 물건을 1개만 넣는 것이 아닌 2개 이상을 넣는 경우까지 고려하게 된다.
 * 1차원 dp테이블을 활용하는 경우에는 로직을 더 추가해주어야지 해당 문제를 해결 가능하다.
 * 
 * <풀이 요약 : DP>
 * 1. 각 무게와 각 물건의 개수에 따라 가질 수 있는 가치의 최대합을 저장할 2차원 dp테이블을 만든다.
 * -> 즉, dp[N, W]의 형태를 갖는다. 물건의 개수를 1개부터 고려하여, K의 무게까지 dp 테이블을 완성한뒤 순차적으로 문제를 해결하여 N개를 고려하는 경우까지 구해본다.
 * 2. 점화식.
 * -> dp[N, W] = Math.max(dp[N - 1, W], dp[N - 1, W - n번째 물건의 물건의 무게] + n번째 물건의 물건의 가치)로 dp테이블을 갱신한다.
 * -> dp[1, W] 즉, 물건을 1개만 고려하는 경우에는 이전에 물건이 없는 경우(기저 조건)가 있어야 점화식을 수행가능하다. 따라서, dp[0, W]의 모든 값은 0으로 초기화한다.
 * -> 만일, (W - n번째 물건의 무게)를 고려할 수 없는 경우라면(즉, 물건을 넣을 가용범위를 넘어선다면) 이전 물건까지 구한 최대 무게를 그대로 가져온다. 즉, dp[N, W] = dp[N -1][W]로 점화식을 구성할 수 있다.
 * <피드백>
 * 대상을 1개부터 고려하고, 대상에대한 모든 경우를 따져보며 그 대상을 N개까지 고려하는 방식은 플로이드-워셜 알고리즘에서도 사용되는 기법이다.
 * 특히, 2차원 dp테이블을 만드는 경우 해당 방식이 자주 사용되기 때문에 명확히 이해해두도록 하자.
 * 
 */

public class BOJ_G5_12865_평범한배낭_2차원 {
	static int N, K;
	static int[][] dp;
	static int[][] items;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		items = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			// 무게
			items[i][0] = sc.nextInt();
			// 가치
			items[i][1] = sc.nextInt();
		}
		// dp 테이블 선언(n번째 물건에 대한 모든 무게에서의 최대 물건의 가치).
		// 초기 배열 선언이 모든 값은 0으로 초기화 되어 있으므로, 물건이 0개인 경우는 굳이 초기화해주지 않아도 된다.
		dp = new int[N + 1][K + 1];

		// dp테이블 갱신.
		// 1번째 물건 부터, N번째 물건까지 모두 고려한다.
		for (int i = 1; i <= N; i++) {
			// 모든 무게에 대하여 값을 갱신해보아야 한다.
			for (int j = 0; j <= K; j++) {
				// 해당 무게에서 현재 물건의 무게를 담을 수 없는 경우
				if (items[i][0] > j) {
					// 이전 물건까지 구한 해당 무게에서의 최댓값을 그대로 가져온다.
					dp[i][j] = dp[i - 1][j];
				}
				// 해당 무게에서 현재 물건을 담아볼 수 있는 경우.
				else {
					// 이전 물건까지 구행 해당 무게에서의 최댓값과, 현재 무게를 포함시켜 만들 수 있는 가치 중 더 큰것을 선택한다.
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i][0]] + items[i][1]);
				}
			}
		}
		// 결과 출력
		System.out.println(dp[N][K]);
		sc.close();
	}
}