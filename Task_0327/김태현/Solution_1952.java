import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 수영장을 가장 적은 비용으로 이용할 수 있는 방법(그 가장 적은 비용 구하기).
 * 문제 유형/요구 개념 : DFS, DP
 * 
 * <풀이법 요약 - DFS>
 * 풀이 요약 : 매 달을 기준으로, 어떤 이용권을 구매할 것인지 DFS탐색을 진행한다.
 * 1. 1년 이용권은 단 1번만 계산해도 되므로, 미리 계산하고 최소 비용으로 초기화 해둔다.
 * 2. 각 달에 대하여 dfs 탐색을 진행한다(부분 집합의 논리 이용).
 * -> 매달 세 달, 한 달, 1일 이용권 x 해당 달의 비용을 선택하여 구매한다.
 * 3. 가지 치기, 종료 조건
 * -> 현재 누적 금액이 현재 최소 금액보다 큰 경우(의미 없으므로 탐색 종료).
 * -> 현재 달이 13이상의 값을 갖는 경우.
 * <주의점>
 * 1, 4, 7, 10달에 대해서만 3달 이용권을 적용하는 가지치기를 진행하였는데,
 * 그 중간 달에 3달 이용권을 이용하는 경우의 수는 고려하지 못하기 때문에 올바른 가지치기가 아니었다.
 * 
 * <풀이법 요약 - DP>
 * 풀이 요약 : 1월에 대한 최적해 -> 2월에 대한 최적해 ... (동전 문제와 유사).
 * 1. 1년 단위는 미리 고려해둔다(1번만 계산하면 된다).
 * 2. 1월부터 시작하여 12월까지 dp 테이블을 완성한다.
 * -> 1월의 dp 테이블을 완성하기 위해서 0월이라는 존재가 필요하므로 dp[0]를 정의해둔다.
 * -> 1개월 단위에 대해서는 2가지 조건을 비교한다(1월 한달 이용권, 1일 이용권 x 해당 달).
 * -> 3개월 단위는 인덱스가 3이상인 경우에 대해서만 진행한다.
 */

public class Solution_1952 {
	static int T;
	static int[] cost, plan;
	static int min_cost;

	// DP를 이용한 풀이
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화
			cost = new int[4];
			for (int i = 0; i < 4; i++) {
				cost[i] = sc.nextInt();
			}
			plan = new int[12];
			for (int i = 0; i < 12; i++) {
				plan[i] = sc.nextInt();
			}

			// 인덱스 = 해당 달로 dp 테이블을 세팅한다. dp[0] = 0.
			int[] dp = new int[13];
			// 1월 부터 12월까지 dp 테이블을 계산한다.
			for (int i = 1; i < 13; i++) {
				// 최소 비용은 1년 이용권으로 초기화.
				int min = cost[3];
				// (1일 x 해당 달 총 이용일 수) 단위 계산
				if (dp[i - 1] + plan[i - 1] * cost[0] < min) {
					min = dp[i - 1] + plan[i - 1] * cost[0];
				}
				// 1달 단위 계산
				if (dp[i - 1] + cost[1] < min) {
					min = dp[i - 1] + cost[1];
				}
				// 3달 단위 계산
				if (i >= 3 && dp[i - 3] + cost[2] < min) {
					min = dp[i - 3] + cost[2];
				}
				dp[i] = min;
			}
			System.out.printf("#%d %d\n", t, dp[12]);
		}
		sc.close();
	}

	// DFS를 이용한 풀이
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		T = sc.nextInt();
//		for (int t = 1; t <= T; t++) {
//			// 초기화
//			cost = new int[4];
//			for (int i = 0; i < 4; i++) {
//				cost[i] = sc.nextInt();
//			}
//			plan = new int[12];
//			for (int i = 0; i < 12; i++) {
//				plan[i] = sc.nextInt();
//			}
//			// 최소 비용은 1년 이용권으로 초기화.
//			min_cost = cost[3];
//			// dfs탐색 진행
//			dfs(1, 0);
//
//			System.out.printf("#%d %d\n", t, min_cost);
//		}
//		sc.close();
//	}
//
//	private static void dfs(int month, int cur_cost) {
//		// 가지 치기 : 누적 금액이 현재 최소 비용보다 크거나 같다면, 재귀 종료.
//		if (cur_cost >= min_cost) {
//			return;
//		}
//		// 종료 조건 : 현재 달이 13이상의 값을 가지면, 최소 비용을 갱신하고 종료.
//		if (month >= 13) {
//			min_cost = Math.min(cur_cost, min_cost);
//			return;
//		}
//
//		// 세 달 이용권을 이용하는 경우.
//		dfs(month + 3, cur_cost + cost[2]);
//		// 한 달 이용권을 이용하는 경우
//		dfs(month + 1, cur_cost + cost[1]);
//		// 하루 이용권을 이용하는 경우
//		dfs(month + 1, cur_cost + cost[0] * plan[month - 1]);
//	}
}
