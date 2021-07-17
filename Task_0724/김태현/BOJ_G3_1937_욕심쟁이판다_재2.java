import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 판다가 최대한 많은 칸을 이동하는 경우의 경우의 수.
 * 문제 유형 : 2차원 DP.
 * 주의 사항 : 판다는 대나무가 더 많은 경우에만 다음 지역으로 이동한다.
 * <풀이 요약>
 * 1. 해당 위치에서 판다가 이동할 수 있는 최대 이동 가능 횟수를 저장할 2차원 dp테이블을 선언한다.
 * 2. 좌상단부터 우하단까지 판다가 해당 위치를 방문하지 않은 경우만 방문하며, dp테이블을 갱신한다.
 * -> 해당 위치를 방문한 적이 있다면, 해당 위치의 값을 그대로 return한다.
 * -> 처음 방문하는 위치는 우선 1로 초기화한다(기저 조건)(해당 위치에 방문하였으므로 그 자체로 1일).
 * -> DFS 탐색을 진행하며 다음 지역을 탐색한다(맵 밖 X, 더 큰 경우만 이동).
 * -> 탐색을 종료하는 시점에서 현재 자신의 위치의 dp테이블 값을 return한다.
 * -> DFS 탐색을 진행하는 시점에서, 받은 값과 현재 자신의 값 중 더 큰 값을 비교하여 현재 위치의 값을 dp테이블에 갱신한다.
 * 단, 이때 넘어오는 시점에서 1일이 지난 것이므로 + 1하여 비교한다.
 */

public class BOJ_G3_1937_욕심쟁이판다_재2 {
	static int n;
	static int[][] map, dp;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 1;
		// 좌상단 우하단 탐색
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, dfs(i, j));
			}
		}
		System.out.println(max);
	}

	private static int dfs(int r, int c) {
		// 방문한 적 있다면 그 지역의 값을 그대로 반환
		if (dp[r][c] != 0)
			return dp[r][c];
		// 최초 방문이라면, 해당 지역의 값을 1로 초기화(기저 조건).
		dp[r][c] = 1;

		// 판다가 이동할 수 있는 지역을 탐색
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 맵 밖이라면 스킵
			if(nr < 0 || nr >= n || nc < 0 || nc >= n)
				continue;
			// 다음 방문 위치가 더 크다면 탐색하고, 탐색으로 넘겨 받은 값 + 1과 현재 위치의 값을 비교하며 dp테이블 갱신.
			if(map[r][c] < map[nr][nc])
				dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
		}
		
		return dp[r][c];
	}
}
