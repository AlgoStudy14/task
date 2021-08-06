package algo0807;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_G4_1520_내리막길 {
	
	static int N, M, cnt;
	static int[][] mountain, dp;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mountain = new int[N][M];
		dp = new int[N][M];
		
		// 입력(N, M <= 500 / 각 지점 <= 10,000)
		for (int i = 0; i < N * M; i++)
			mountain[i / M][i % M] = sc.nextInt();

		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], -1);

		System.out.println(dfs(0, 0));
	}
	
	// -1: 방문하기 전, 0: 현재 위치에서 도착지까지의 경로의 개수를 초기화
	static int dfs(int x, int y) {
		if (x == N - 1 && y == M - 1) return 1;
		if (dp[x][y] != -1) return dp[x][y];
		
		dp[x][y] = 0;
		for (int dir = 0; dir < 4; dir++) {		// 상하좌우로 이동
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;	// 배열을 벗어났다면
			if (mountain[x][y] <= mountain[nx][ny]) continue;		// 오르막길이라면 패스
			
			dp[x][y] += dfs(nx, ny);
		}
		return dp[x][y];
	}
}