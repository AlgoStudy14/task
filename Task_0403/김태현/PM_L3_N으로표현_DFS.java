package s0327;

/*
 * <문제 요약>
 * 구해야 하는 것 : 숫자 N과 사칙연산을 이용해 목표 숫자를 만드는 경우 최소로 N을 사용하는 횟수.
 * 요구 개념/문제 유형 : DFS.
 * <풀이법>
 * 1. 현재 수에서 N과 사칙 연산을 하면서 DFS를 진행한다.
 * -> 단, 현재수에서 N을 최대 8번까지 이어 붙인 수에 대하여 모두 연산해야한다.
 * -> 연산 횟수가 8이 넘어가면 종료한다(종료 조건).
 * -> 목표 숫자가 되면 몇개의 숫자로 도달 했는지 최소값을 갱신한다.
 * <주의점>
 * dfs에 N과 cnt를 1로 설정하고 넘겨주었는데, 그러면 N, NN, NNN등을 검사하기 못하기 때문에 0, 0을 넣어주어야한다.
 */

public class PM_L3_N으로표현_DFS {
	public static void main(String[] args) {
		int N = 5;
		int number = 12;
		solution(N, number);
	}

	static int ans;
	static int N, number;

	public static int solution(int N, int number) {
		PM_L3_N으로표현_DFS.ans = Integer.MAX_VALUE;
		PM_L3_N으로표현_DFS.N = N;
		PM_L3_N으로표현_DFS.number = number;
		dfs(0, 0);
		System.out.println(ans);
		if (ans == Integer.MAX_VALUE) {
			return -1;
		} else {
			return ans;
		}
	}

	private static void dfs(int num, int cnt) {
		// 종료조건
		if (cnt > 8) {
			ans = -1;
			return;
		}
		// 목표 숫자가 되면 값 갱신.
		if (num == number) {
			ans = Math.min(ans, cnt);
			return;
		}
		// 현재 수에 대하여, 최대 8번까지 이어 붙이며 dfs를 탐색한다.
		int cur_N = N;
		for (int i = 0; i < 8 - cnt; i++) { // 최대 8번 진행하므로, 현재까지 온 cnt의 개수를 뺀 만큼만 수를 이어 붙인다.
			int cur = cnt + i + 1; // 현재 총 N의 사용 횟수
			dfs(num + cur_N, cur);
			dfs(num - cur_N, cur);
			dfs(num * cur_N, cur);
			dfs(num / cur_N, cur);
			cur_N = Integer.parseInt(Integer.toString(N) + cur_N);
		}
	}
}
