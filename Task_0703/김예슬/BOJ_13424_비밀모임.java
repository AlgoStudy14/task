package algo0703;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 친구들의 이동 거리의 총합이 최소가 되도록 하는 모임 장소를 찾아 출력
 * 문제 유형 : 플로이드-워샬
 * 주의 사항 : 문제가 너무 길다... whyrano..
 * 		   	비밀통로는 반드시 하나씩/갈 수 없는 경우X/총합이 최소가 되는 방이 여러 개면 가장 작은 방 번호 출력
 * <풀이 요약>
 * 모든 정점에서 다른 정점으로의 최소 비용을 구해야 하므로 플로이드-워샬 사용
 */

public class BOJ_13424_비밀모임 {

	static int N, M, K, INF = 100002;
	static int[][] adj;
	static int[] friends;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 0; t < T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			adj = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) continue; 
					adj[i][j] = INF;
				}
			}
			
			for (int i = 0; i < M; i++) {
				int from = sc.nextInt() - 1;
				int to = sc.nextInt() - 1;
				adj[from][to] = adj[to][from] = sc.nextInt();
			}
			
			K = sc.nextInt();
			friends = new int[K];
			for (int i = 0; i < K; i++)
				friends[i] = sc.nextInt() - 1;
			
			floydWarshall();
			findAnswer();
		}
	}

	static void floydWarshall() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (adj[i][j] > adj[i][k] + adj[k][j])
						adj[i][j] = adj[i][k] + adj[k][j];
				}
			}
		}
	}
	
	static void findAnswer() {
		int min = INF;
		int room = -1;
		// 오늘의 모임 장소마다
		for (int j = 0; j < N; j++) {
			// 친구들이 갈 수 있는 곳에서 모임장소까지의 비용
			int sum = 0;
			for (int i = 0; i < K; i++) {
				sum += adj[friends[i]][j];
			}
			
			if (min > sum) {
				min = sum;
				room = j;
			}
		}
		
		System.out.println(room + 1);
	}
}
