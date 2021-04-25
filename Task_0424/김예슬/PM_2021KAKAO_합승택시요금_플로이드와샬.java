package algo0421;

import java.util.Arrays;

/***
 * <문제 요약>
 * 구해야 하는 것 : 로봇의 이동 경로가 단순할 확률을 출력
 * 제약 사항 : 
 * 문제 유형 : DFS
 * <풀이법 요약>
 * 0. 인접리스트
 * 1. 합승할 경우,
 * 2. 합승하지 않을 경우,
 * 1. S에서 합승하지 않고, A와 B까지의 최소비용 각각 구하기
 * 2. S~끝까지 합승할 경우
 */

public class PM_2021KAKAO_합승택시요금_플로이드와샬 {
	
	static int INF = 20000001;
	static int[][] adjMatrix;
	
	public static void main(String[] args) {
		int[][] fares = new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
			{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };
		System.out.println(solution(6, 4, 6, 2, fares));
	}
	
	public static int solution (int N, int S, int A, int B, int[][] fares) {
		adjMatrix = new int[N][N];
		
		for (int i = 0; i < N; i++)
			Arrays.fill(adjMatrix[i], INF);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) adjMatrix[i][j] = 0;
			}
		}
		
		for (int i = 0; i < fares.length; i++) {
			int e1 = fares[i][0] - 1;
			int e2 = fares[i][1] - 1;
			int cost = fares[i][2];
			adjMatrix[e1][e2] = adjMatrix[e2][e1] = cost;
		}
		
		floyd(N);

		for (int i = 0; i < N; i++)
			System.out.println(Arrays.toString(adjMatrix[i]));
		return getMin(N, S - 1, A - 1, B - 1);
	}
	
	static void floyd(int N) {	
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k) continue;				// 출발지 = 경유지
				for (int j = 0; j < N; j++) {
					if (i == j || j == k) continue;	// 출발지 = 목적지, 목적지 = 경유지
					
					if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j])
						adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
				}
			}
		}
	}
	
	static int getMin(int N, int S, int A, int B) {
		int ans = adjMatrix[S][A] + adjMatrix[S][B];
		for (int i = 0; i < N; i++) {
			ans = Math.min(ans, adjMatrix[S][i] + adjMatrix[i][A] + adjMatrix[i][B]);
		}
		return ans;
	}
	
}