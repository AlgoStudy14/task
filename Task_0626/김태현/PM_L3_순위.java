package s0626;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 주어진 정보내에서 순위를 알 수 있는 선수의 수.
 * 문제 유형 : 플로이드-워셜 알고리즘.
 * <풀이 요약 : 플로이드-워셜>
 * 1. 모든 선수의 관계성을 나타내기 위하여 인접 행렬을 선언한다.
 * 2. 주어진 정보에 따라 정보가 있는 선수들은 간선이 1의 비용을 갖도록 초기화한다. 이외의 선수는 INF로 초기화한다.
 * -> 즉, 이긴 선수는 상대방에 대하여 비용이 1이상의 값을 갖게 된다.
 * 3. 플로이드-워셜 알고리즘을 이용하여 모든 간선으로의 최소 비용을 초기화한다.
 * 4. 각 선수에 대하여 그래프를 순회하며 모든 연결성이 보장되었다면, answer를 + 1한다.
 * -> 해당 선수의 가로열은 이긴 선수를 의미하고, 세로 열은 진 선수를 의미한다. 해당 정보가 n - 1개 이상이라면, 순위를 알 수 있다.
 * <피드백>
 * 백준에 풀었던 키순서와 같은 문제이다. 플로이드에서 중요한 점은, 플로이드는 모든 각 점에서 각 점까지의 최단 경로를 알려준다는 것인데
 * 이 말은 다르게 말하면, 각 점까지의 최단 경로가 구해지지 않는다면 두 점은 연결되어 있지 않다는 것이다. 이 점을 이용하면 해당 문제를 풀 수 있다.
 */

public class PM_L3_순위 {
	public static void main(String[] args) {
		int n = 5;
		int[][] result = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
		solution(n, result);
	}

	public static int solution(int n, int[][] results) {
		int answer = 0;

		// 인접 행렬 선언
		int[][] graph = new int[n][n];
		// 인접행렬 초기화
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					graph[i][j] = 0;
					continue;
				}
				graph[i][j] = 5000;
			}
		}

		// 그래프 초기화
		for (int i = 0; i < results.length; i++) {
			// 이겼다면, 상대방에 대하여 간선이 1의 비용을 갖는다.
			graph[results[i][0] - 1][results[i][1] - 1] = 1;
		}

		// 플로이드-워셜 알고리즘
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 해당 dp테이블의 값이 k를 거쳐서 가는 비용보다 크다면, 최소 값으로 갱신.
					if (graph[i][j] > graph[i][k] + graph[k][j])
						graph[i][j] = graph[i][k] + graph[k][j];
				}
			}
		}

		// 순위를 알 수 있는 선수 찾기
		for (int i = 0; i < n; i++) {
			int cnt = 0;
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				// 가로열에서 본인을 제외하고 승패을 알 수 있는 경우 계산하기.
				if (graph[i][j] != 5000)
					cnt++;
				if (graph[j][i] != 5000)
					cnt++;
			}
			if (cnt == n - 1)
				answer++;

		}

		return answer;
	}
}
