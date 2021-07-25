package algo0725;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력
 * 문제 유형 : 다익스트라
 * 주의 사항 : O(10억) 
 * <풀이 요약>
 * 도로는 단방향 / 시작점=도착점 도로 X / A->B 도로 무조건 1개
 * 1~N마다 X로의 비용과 X에서 1~N로의 비용이 필요하므로
 * 모든 정점으로 갈 수 있는 비용이 필요하다고 생각해 플로이드-워샬 사용
 * 
 * 1. 인접행렬 adj 초기화
 * 2. floydWarshall() 함수
 *   플로이드-워샬을 사용해 각 정점에서 모든 정점으로 가는데 필요한 최소 비용을 adj에 저장
 * 3. findBigCost() 함수 
 *   모든 경로로의 최소 비용을 저장한 행렬 adj에서 (1~N에서 X로의 비용) + (X에서 1~N로의 비용) 중 가장 큰 값을 출력
 * <피드백>
 * 1. 처음에 인접행렬을 모두 INF로 초기화 해
 *    시작점=도착점인 케이스에서 최소 비용인 0이 아닌 돌아서 가는 비용이 나오게 됨
 *    
 * 2. 가지치기를 하지 않아도 이 문제의 경우에서는 통과가 되나
 *    O(10억)=약 10초이므로 가지치기를 하는 것이 바람직
 *    → i에서 k로 가는 도로가 없다면 가지치기(2144ms → 1112ms) 
 */

public class BOJ_G3_1238_파티 {

	static int N, M, X, INF = 1000002;
	static int[][] adj;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt() - 1;
		adj = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				adj[i][j] = INF;
			}
		}

		for (int i = 0; i < M; i++)
			adj[sc.nextInt() - 1][sc.nextInt() - 1] = sc.nextInt();
		
		floydWarshall();
		System.out.println(findBigCost());
	}

	
	static int findBigCost() {
		int max = 0;
		
		for (int i = 0; i < N; i++)
			max = Math.max(max, adj[i][X] + adj[X][i]);
		
		return max;
	}
	
	static void floydWarshall() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (adj[i][k] == INF) continue;
				
				for (int j = 0; j < N; j++) {
					if (adj[i][j] > adj[i][k] + adj[k][j])
						adj[i][j] = adj[i][k] + adj[k][j];
				}
			}
		}
	}
}
