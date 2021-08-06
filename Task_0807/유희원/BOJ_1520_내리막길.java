package algo0806;

import java.util.Scanner;

public class BOJ_1520_내리막길 {

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] map, dp;
	static int M,N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		
		map = new int[M][N];
		dp = new int[M][N];
		
		//입력 & dp 배열 초기화
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				dp[i][j] = -1;
			}
		}
		
		System.out.println(DFS(0,0));

	}
	private static int DFS(int r, int c) {
		//끝 지점 도달!
		if(r==M-1 && c==N-1) return 1;
		//방문이력 (경우의 수 기록된거) 있으면 그 값 return -> 이거 안하고 dfs만 하면 시초!
		if(dp[r][c]!=-1) return dp[r][c];
		
		dp[r][c] = 0;
		//4방탐색
		for (int d = 0; d < 4; d++) {
			int nr = r +dr[d];
			int nc = c +dc[d];
			if(nr<0||nc<0||nr>=M||nc>=N) continue;
			//내리막길이면 nr nc에서 끝점까지 가는 경우의 수 더하기 (재귀)
			if(map[r][c]>map[nr][nc]) dp[r][c] += DFS(nr,nc);
		}
		return dp[r][c];
	}

}
