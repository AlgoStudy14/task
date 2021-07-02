package s0703;

import java.util.Scanner;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 각 친구가 모임 장소로 이동할 수 있는 최소 비용의 합.
 * 문제 유형 : 플로이드 워셜.
 * 주의 사항 : 양 방향 그래프, 친구는 각각 독립된 방에 존재, 테스트 케이스 문제.
 * <풀이 요약>
 * 1. 플로이드 워셜 알고리즘을 통해 각 정점에서 정점까지의 최소 거리를 구한다. 
 * 2. 각 친구들이 모든 정점으로 가는 비용을 계산하며 최소 값을 갱신하고 결과를 출력한다.
 * <피드백>
 * 입력 크기부터 걍 플로이드 문제죠? 반박 불가죠?
 */

public class BOJ_G4_13424_비밀모임 {
	static int T, N, M, K;
	static int[][] graph;
	static int[] friends;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			graph = new int[N][N];
			// 그래프 초기화.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					graph[i][j] = 100_000_000;
				}
			}
			// 그래프 입력.
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int c = sc.nextInt();
				// 양 방향 그래프.
				graph[a][b] = c;
				graph[b][a] = c;
			}

			// 플로이드 워셜.
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						// 그냥 가는 비용 vs k를 거쳐서 간 비용
						graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}

			// 각 친구의 위치 입력
			K = sc.nextInt();
			friends = new int[K];
			for (int i = 0; i < K; i++) {
				friends[i] = sc.nextInt() - 1;
			}

			// N개의 방에 대하여 각 친구가 도달 가능한 비용 모두 계산해보기
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for (int i = 0; i < N; i++) {
				int cur = 0;
				for (int j = 0; j < K; j++) {
					cur += graph[friends[j]][i];
				}
				if (min > cur) {
					min = cur;
					idx = i;
				}
			}

			System.out.println(idx + 1);
		}
		sc.close();
	}
}
