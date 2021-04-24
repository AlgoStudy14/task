package algo0424;

import java.util.Arrays;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 1번 정점에서 N번 정점으로 갈 때, v1과 v2를 반드시 거쳐가는 최단 경로의 길이를 출력
 * 문제 유형 : 플로이드 와샬 사용(800^3=약 5억)
 * <풀이법 요약>
 * 1. 플로이드 와샬로 현재 위치에서 모든 경로로 갈 수 있는 최단 경로의 최소 비용을 구함
 * 2. (1->v1->v2->N) vs (1->v2->v1->N)
 *  2-1. 만약 INF의 값을 초과할 경우 경로가 없는 경우이므로 -1을 출력
 */

public class BOJ_G4_1504_특정한최단경로 {

	static final int INF = 800001;
	static int N, E;
	static int[][] adj;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		adj = new int[N][N];
		
		for (int i = 0; i < N; i++)
			Arrays.fill(adj[i], INF);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) 
				if (i == j) adj[i][j] = 0;

		for (int i = 0; i < E; i++) {
			int e1 = sc.nextInt() - 1;
			int e2 = sc.nextInt() - 1;
			int cost = sc.nextInt();
			adj[e1][e2] = adj[e2][e1] = cost;
		}
		
		floyd();
		
		int v1 = sc.nextInt() - 1;
		int v2 = sc.nextInt() - 1;
		int onetotwo = adj[0][v1] + adj[v1][v2] + adj[v2][N - 1];
		int twotoone = adj[0][v2] + adj[v2][v1] + adj[v1][N - 1];
		int result = Math.min(onetotwo, twotoone);
		
		System.out.println(result >= INF ? -1 : result);
	}
	
	static void floyd() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
	}
}
