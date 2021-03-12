import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : A에서 B로 가는데 필요한 비용의 최솟값
 * 문제 핵심 요약 : 플로이드 와샬
 * 
 * <풀이법 요약>
 * 플로이드 와샬
 * 모든 정점에서 모든 정점으로의 최단 경로 
 * "거처가는 정점"을 기준으로 알고리즘을 수행 -> 더 작은 비용으로 갱신
 * ex) 1의 입장에서 (2->3)=9 vs (2->1) + (1->3)=7 => 7로 갱신!
 * 2차원 배열을 사용
 * 
 * 아래 코드는 나동빈님의 강의를 참고하여 작성하였습니다!
 * 
 * 세상엔 정말 많은 알고리즘이 있구나,,, 주말에 정리해야겠다!
 * 
 * 대충 정리하자면
 * <용도>
 * 다익스트라  : 각 정점간 최단경로를 구할 때
 * 플로이드 : 각 정점간 최단경로를 구할 때
 * 
 * 간선 수가 많다면 플로이드가 더 빠를 수 있다.
 * 시작점으로부터 각 정점까지 최단거리만 구해도 될 때, 보통 다익스트라 알고리즘이 앞도적으로 빠르다고 한다..
 */

public class BOJ_G4_11404_플로이드 {

	public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
	// 노드의 개수(N), 간선의 개수(M)
	public static int n, m;
	// 2차원 배열(그래프 표현)를 만들기
	public static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		graph = new int[n + 1][n + 1];

		// 최단 거리 테이블을 모두 무한으로 초기화
		for (int i = 0; i <= n; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j)
					graph[i][j] = 0;
			}
		}

		// 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 가장 짧은 간선 정보만 저장
			if (c < graph[a][b])
				graph[a][b] = c;
		}

		// 점화식에 따라 플로이드 워셜 알고리즘을 수행
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}

		// 수행된 결과를 출력
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// 도달할 수 없는 경우 INF 출력
				if (graph[i][j] == INF) {
					System.out.print(0 + " ");
				}
				// 도달할 수 있는 경우 거리를 출력
				else {
					System.out.print(graph[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

}
