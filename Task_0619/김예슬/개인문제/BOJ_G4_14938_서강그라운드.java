package algo0611;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 예은이가 얻을 수 있는 아이템의 최대 개수를 출력
 * 문제 유형 : 플로이드-워샬
 * 주의 사항 : 
 * <풀이 요약>
 * 모든 정점에서 갈 수 있는 정점의 거리를 구해야 하므로 플로이드-워샬 사용
 * 모든 정점에 대해 얻을 수 있는 아이템의 개수를 구하고 그중 가장 많이 얻을 수 있는 곳의 개수를 출력
 */

public class BOJ_G4_14938_서강그라운드 {

	static int N, M, R, INF = 16;
	static int[][] adj;
	static int[] item;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		adj = new int[N + 1][N + 1];
		item = new int[N + 1];
		
		// 해당 지역의 아이템 입력
		for (int i = 1; i <= N; i++)
			item[i] = sc.nextInt();

		// 인접행렬 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				adj[i][j] = INF;
			}
		}
		
		for (int i = 0; i < R; i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			adj[v1][v2] = adj[v2][v1] = sc.nextInt();
		}
		
		floydWarshall();
		print();
		
	}

	static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (adj[i][j] > adj[i][k] + adj[k][j])
						adj[i][j] = adj[i][k] + adj[k][j];
				}
			}
		}
	}
	
	static void print() {
		int max = 0;
		// 예은이가 떨어지게 될 모든 정점에 대해 얻을 수 있는 아이템의 개수 구하기 
		for (int i = 1; i <= N; i++) {
			int tmp = 0;
			for (int j = 1; j <= N; j++) {
				if (adj[i][j] <= M) tmp += item[j];
			}
			max = Math.max(max, tmp);
		}
		System.out.println(max);
	}
}
