package algo0703;

import java.util.Scanner;

public class BOJ_1937_욕심쟁이판다 {
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N,ans;
	static int[][] map,dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = Math.max(ans, DFS(i,j));
			}
		}
		System.out.println(ans);
	}
	private static int DFS(int i, int j) {
		if(dp[i][j] != 0) return dp[i][j];
		//그 위치에서 일단 1일?만큼은 살수있으니까 방문한적 없으면 1로 넣어둠
		dp[i][j] = 1;
		//상하좌우 탐색
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(nr<0||nc<0||nr>=N||nc>=N) continue;
			//현재 대나무보다 더 많은 양의 대나무 발견! -> 가장 오래 생존할수있는걸로 갱신
			if(map[i][j]<map[nr][nc]) {
				dp[i][j] = Math.max(dp[i][j], DFS(nr,nc)+1);
				DFS(nr,nc);
			}
		}
		return dp[i][j];
	}
}
