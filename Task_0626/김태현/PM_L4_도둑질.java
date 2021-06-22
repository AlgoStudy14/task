package s0626;

/* 
 * (미완)
 * <문제 요약>
 * 문제 정의 : 원형으로 이루어진 마을에서, 인접한 두 집이 아닌 모든 집을 털 수 있을 때 최대로 훔칠 수 있는 돈의 양.
 * 문제 유형 : DP.
 * <풀이 요약 : DP>
 * 1. 각 집에 도달할 때 까지 훔칠 수 있는 최대 비용을 저장할 일차원 dp 테이블을 만든다.
 * -> 단, 집이 원형의 형태를 가지고 있으므로 초기 출발 지점을 첫 집과 두 번째 집으로 나누어 2개의 dp 테이블을 만든다.
 * 2. 첫 집부터 시작하여, i번째 집이 (i번째 집에서 얻을 수 있는 비용 + i - 2번째 집까지의 최대 비용) 과  (i - 1번째 집까지의 최대 비용)
 * 중 어떤 것을 선택하는 것이 유리한지에 따라 dp 테이블의 값을 갱신한다. 
 * -> 즉, 어차피 순서대로 집을 털어가면서 최대 비용은 뒤에 어떤 집이 있는지와 상관이 없으므로 최대 비용을 갱신하면서 모두 탐색한다.
 * 3. 2개의 dp테이블을 완성시키고, dp[n - 1](첫 집을 선택한 경우)와 dp[n](첫 집을 선택하지 않은 경우)를 비교하여 더 큰 값을 출력한다.
 * <피드백>
 * n번째 집을 털었을때 최대 비용을 저장하는 테이블을 만들면 어떨까? 라는 물음부터 규칙성을 발견할 수 있다.
 */

public class PM_L4_도둑질 {
	public static void main(String[] args) {
		int[] money = { 1, 2, 3, 1 };
		solution(money);
	}

	public static int solution(int[] money) {
		// 2개의 dp테이블을 만든다.
		// 첫 번째 집부터 시작하는 경우.
		int[] dp = new int[money.length];
		// 첫 번째 집을 선택했으므로, dp[0]와 dp[1]을 money[0]로 초기화.
		dp[0] = money[0];
		dp[1] = money[0];
		// 두 번째 집부터 시작하는 경우.
		int[] dp2 = new int[money.length];
		// 두 번째 집을 선택했으므로, dp[0] = 0, dp[1] = money[1]로 초기화.
		dp2[0] = 0;
		dp2[1] = money[1];

		// 첫 번째 dp 테이블 완성
		// 세 번째 집부터 n - 1번째 집까지 점화식을 적용한다.
		for (int i = 2; i < money.length - 1; i++) {
			dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1]);
		}
		// 두 번째 dp 테이블 완성
		// 세 번째 집부터 n번째 집까지 점화식을 적용한다.
		for (int i = 2; i < money.length; i++) {
			dp2[i] = Math.max(dp2[i - 2] + money[i], dp2[i - 1]);
		}

		return Math.max(dp[money.length - 2], dp2[money.length - 1]);
	}
}
