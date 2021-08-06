package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 좌 상단에서 우 하단까지 내릭막길로만 이동하는 경로의 개수는?
 * 문제 유형 : DFS + DP.
 * <풀이 요약>
 * 1. 각 위치로 이동할 수 있는 이동의 총 수를 저장할 2차원 dp 테이블을 만든다.
 * 2. DFS를 이용하여 탐색과 반환을 지속한다.
 * -> 최종 지점에 도착하면 1을 리턴해준다.
 * -> 값이 저장된 지점을 방문했다면 해당 값을 리턴해준다.
 * -> DFS를 탐색하는 과정에서 넘겨 받는 값을 현재 좌표에 계속하여 더한다.
 * 3. DFS 조건
 * -> 맵을 벗어나지 않는다.
 * -> 내리막 길로만 이동한다.
 * -> 방문한 지점은 방문하지 않는다.
 * 4. 최종으로 반환되는 값을 출력한다.
 * <피드백>
 * DP는 생각보다 여러가지로 응용될 수 있다. DFS의 가장 어려운 유형이 아닐까?
 */

public class BOJ_G4_1520_내리막길 {
	static int N, M;
	static int[][] map, dp;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(dfs(0, 0));
	}

	private static int dfs(int r, int c) {
		// 만일, 최종 지점에 도착하였다면 1을 리턴한다.
		if (r == N - 1 && c == M - 1)
			return 1;
		// 만일, 이미 방문한 지점에 도착하였다면 해당 값을 리턴한다.
		if (dp[r][c] != -1)
			return dp[r][c];

		// 4방 탐색을 진행한다.
		dp[r][c] = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 맵을 벗어나면 안된다.
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			// 내리막 길인 경우만 탐색한다
			if (map[nr][nc] < map[r][c]) {
				dp[r][c] += dfs(nr, nc);
			}
		}
		
		return dp[r][c];
	}
}
