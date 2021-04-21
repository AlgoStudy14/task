package s0424;

/*
 * <문제 요약>
 * 구해야 하는 것 : A, B 두 사람이 귀가 가능한 최소 비용(단, 합승도 가능).
 * 문제 유형 : 플로이드 워셜 알고리즘, 시뮬레이션.
 * <풀이법>
 * 1. 플로이드 워셜 알고리즘을 이용하여 각 지점간 최소 이동거리를 미리 구한다.
 * 2. A와 B가 시작점에서 각자 집으로 가는 비용부터 시작하여, N - 1개의 정점을 거쳐서 간 합 + 그 장소에서 A와 B가 집으로 가능 비용을 계산하며 갱신한다.
 * <주의사항>
 * 1. 그래프 인덱스는 늘 주의하자. 실수하기 쉽다.
 * 2. 또한, 방향 그래프인지 무방향 그래프인지도 항상 주의하자.
 */

public class PM_KAKAO2021_합승택시요금 {
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		// 입력
		int[][] dist = new int[n][n];
		final int INF = 700000000;
		// 비용 배열 초기화
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					dist[i][j] = 0;
					continue;
				}
				dist[i][j] = INF;
			}
		}

		// 초기 비용
		for (int i = 0; i < fares.length; i++) {
			dist[fares[i][0] - 1][fares[i][1] - 1] = fares[i][2];
			dist[fares[i][1] - 1][fares[i][0] - 1] = fares[i][2];
		}

		// 플로이드 워셜 알고리즘
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		// 최소 비용 계산
		// 해당 모든 정점을 거쳐서 이동해본다.
		for (int i = 0; i < n; i++) {
			int cost = 0;
			cost = dist[s - 1][i] + dist[i][a - 1] + dist[i][b - 1];
			answer = Math.min(answer, cost);
		}
		System.out.println(answer);

		return answer;
	}
}
