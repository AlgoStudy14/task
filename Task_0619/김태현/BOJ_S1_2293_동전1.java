package s0619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : n가지 종류의 동전을 이용하여 k원을 만들 수 있는 경우의 수.
 * 문제 유형 : DP.
 * 주의 사항 : 사용한 동전의 순서만 다른 경우는 같은 경우이다.
 * <풀이 요약>
 * 1. 가치의 합을 완성시키는 경우의 수를 저장할 1차원 DP 테이블을 만든다.
 * 2. 가치가 가장 작은 동전부터 시작하여, 만들 수 있는 경우의 수를 차례차례 누적해 나간다.
 * -> 1원으로 만들 수 있는 경우의 수를 먼저 구한다.
 * -> 위 경우의 수를 기반으로, 2원으로 만들 수 있는 경우의 수를 구한다.
 * -> 위 경우의 수를 기반으로, 5원으로 만들 수 있는 경우의 수를 구한다.
 * -> 즉, 임의의 X원을 완성시키기 위한 경우의 수는, (X원 - 해당 동전의 가치)를 완성시키기 위한 경우의 수와 같다(점화식).
 * -> 따라서, 0원을 만드는 경우의 수는 1개라고 초기화 시켜주어야 한다(초기화).
 * 3. 최종적으로 dp[k]를 출력한다.
 * <피드백>
 * dp는 초기화된 값으로부터 점화식을 도출하여 다음 값을 계산하는 것이다. 즉, 규칙성을 먼저 발견해야 한다.
 * -> 해당 문제의 핵심 규칙은 {K원의 가치를 가진 동전으로 임의의 X원을 만들기 위한 경우의 수 = (X원 - K원)을 만들기 위한 경우의 수}이다.
 * dp테이블은 갱신과 누적이 모두 가능하다. dp테이블을 만드는 가장 중요한 이유는 '이전에 계산한 값들을 의미있게 사용하는 것'이다.
 * -> 이 규칙을 활용하여, 모든 K원의 경우의 수에 대하여 dp 테이블을 누적 시키는 것이 문제 풀이법이다. 
 */
public class BOJ_S1_2293_동전1 {
	static int n, k;
	static int[] coin, dp;

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
		dp[0] = 1;

		// 0원 부터 시작하여 k원을 완성할 때 마다, dp 테이블의 값을 + 1씩 갱신한다.
		for (int i = 0; i < n; i++) {
			for (int j = coin[i]; j <= k; j++) {
				dp[j] = dp[j] + dp[j - coin[i]];
			}
		}
		System.out.println(dp[k]);
	}
}
