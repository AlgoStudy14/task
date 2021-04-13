import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_Gold5_17090 {
	/*
	 * <미로탈출하기>
	 * map에 U D L R 대로 움직여서 범위 밖으로 움직이는 칸의 수를 세는 문제.
	 * 
	 * <풀이>
	 * dfs, dp.
	 * 
	 * 시간초과.
	 */
	static int N, M, ans;
	static char[][] map;
	static int[][] dp;
	static boolean[][] visited;
	static boolean flag;
	static ArrayList<int[]> arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		dp = new int[N][M];
		//입력
		for(int i = 0; i < N; i++) {
			String s = sc.next();
			for(int j = 0; j < M; j++)
				map[i][j] = s.charAt(j);
		}
		//각 칸마다 확인하기.
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				flag = false;
				arr = new ArrayList<int[]>();
				visited = new boolean[N][M];
				find(i, j);
			}
		}
		System.out.println(ans);
	}
	//int[] DP 0 : 방문하지 않은 상태. 1 방문하여 탈출성공 칸. 2 방문하여 탈출실패한 칸.
	static void find(int r, int c) {
		if(dp[r][c] == 1) {
			flag = true;
			ans++;
			return;
		}else if(dp[r][c] == 2)
			return;
		
		visited[r][c] = true;
		arr.add(new int[] {r, c});
		//다음 위치상태 확인하기
		int nr, nc;
		if(map[r][c] == 'D') {
			nr = r + 1;
			nc = c;
		}else if(map[r][c] == 'U') {
			nr = r - 1;
			nc = c;
		}else if(map[r][c] == 'L') {
			nr = r;
			nc = c - 1;
		}else{
			nr = r;
			nc = c + 1;
		}
		
		if(nr >= N || nr < 0 || nc >= M || nc < 0) {
			for(int[] a : arr)
				dp[a[0]][a[1]] = 1;
			ans++;
			flag = true;
			return;
		}
		if(!visited[nr][nc])
			find(nr, nc);
		if(!flag) {
			for(int[] a : arr)
				dp[a[0]][a[1]] = 2;
		}
	}
}