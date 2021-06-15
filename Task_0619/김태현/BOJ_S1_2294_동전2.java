package s0619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : n가지 종류의 동전을 사용하여 가치의 합이 k원이 되도록 하는 최소 동전의 개수.
 * 문제 유형 : DP.
 * 주의 사항 : 동전의 순서만 다른 구성은 같은 경우이고, 가치가 같은 동전이 여러 번 주어질수도 있다.
 * <풀이 요약>
 * 1. 동전의 개수가 최소가 되는 경우를 저장해둘 1차원 DP 테이블을 만든다.
 * 2. 가치가 가장 작은 동전으로 만들 수 있는 경우부터 시작하여, 그 값을 갱신해나간다.
 * -> 1원으로 k원을 만들 수 있는 경우의 수를 먼저 구한다.
 * -> 위 경우를 기반으로, 5원으로 k원을 만들 수 있는 동전의 개수가 더 작다면 그 값을 갱신한다.
 * -> 위 경우를 기반으로, 12원으로 k원을 만들 수 있는 동전의 개수가 더 작다면 그 값을 갱신한다.
 * -> 즉, 임의의 X원을 완성 시키기 위한 현재 동전의 개수 > X - K원을 완성 시키기 위한 동전의 개수 + 1이라면 그 값을 갱신한다(점화식).
 * -> 즉, dp[0] = 0으로 초기화 가능하다. 이때, 나머지 dp테이블의 값은 만들 수 있는 최대 값으로 초기화 해두어야 갱신이 가능하다.
 * 3. dp[k]를 출력한다.
 * <피드백>
 * dp테이블을 초기에 Integer.MAX_VALUE로 초기화 하면, 오버 플로우로 인하여 값이 음수로 바뀌어 원치 않는 조건에서 값이 갱신될수도 있다.
 * 함부로 최대 정수 값을 dp테이블로 초기화 하는 실수를 하지 말자.
 */

public class BOJ_S1_2294_동전2 {
	static int n, k;
	static int[] dp, coin;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coin = new int[n];
		dp = new int[k + 1];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		Arrays.fill(dp, 10001);
		dp[0] = 0;

		// 가치가 가장 작은 동전부터 차례대로 반복한다.
		for (int i = 0; i < n; i++) {
			// 해당 동전으로 k원을 만들 수 있는 최소 개수를 갱신한다.
			for (int j = coin[i]; j <= k; j++) {
				// 임의의 X원을 완성 시키기 위한 현재 동전의 개수 > X - K원을 완성시키기 위한 동전의 개수 + 1이라면 값 갱신.
				dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
			}
		}
		if (dp[k] == 10001) {
			System.out.println(-1);
		} else {
			System.out.println(dp[k]);
		}
	}
}
