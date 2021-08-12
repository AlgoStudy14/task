package algo0813;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 최소 사이클의 도로 길이의 합을 출력
 * 문제 유형 : 플로이드-워샬 
 * 주의 사항 : 
 * <풀이 요약>
 * 사이클을 찾기 위해 모든 정점에서 다른 모든 정점으로 가는 비용이 필요하므로 플로이드-워샬 사용
 * 1. 사이클을 찾기 위해 시작점→...→시작점으로 가는 경로가 있어야 되므로 adj를 모두 INF로 초기화 후 플로이드-워샬 진행
 * 2. 이때, adj[x][x]가 최소 사이클
 *  2-2. INF가 아닌 정점을 시작점으로 하는 최소 사이클을 찾으면 됨
 *  2-1. adj[x][x]가 INF인 경우 운동 경로를 찾는 것이 불가능하므로 -1 출력
 */

public class BOJ_G4_1956_운동 {

	static int N, M, INF = 4000002;
	static int[][] adj;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adj = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(adj[i], INF);
		}
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt() - 1;
			int to = sc.nextInt() - 1;
			adj[from][to] = sc.nextInt();
		}
		
		floydWarshall();
		find();
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
	
	static void find() {
		int min = INF;
		for (int i = 0; i < N; i++) {
			if (adj[i][i] == INF) continue;
			
			min = Math.min(min, adj[i][i]);
		}
		System.out.println(min == INF ? -1 : min);
	}
}
