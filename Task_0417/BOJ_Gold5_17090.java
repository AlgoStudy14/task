import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_Gold5_17090 {
	/*
	 * 시간초과.
	 */
	static int N, M, ans;
	static char[][] map;
	static boolean[][] dp, visited;
	static boolean flag;
	static ArrayList<int[]> arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		dp = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String s = sc.next();
			for(int j = 0; j < M; j++)
				map[i][j] = s.charAt(j);
		}
		
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
	
	static void find(int r, int c) {
		if(flag)
			return;
		
		if(r >= N || r < 0 || c >= M || c < 0) {
			for(int[] a : arr)
				dp[a[0]][a[1]] = true;
			ans++;
			flag = true;
			return;
		}
		
		if(visited[r][c])
			return;
		
		if(dp[r][c] == true) {
			flag = true;
			ans++;
			return;
		}		
		visited[r][c] = true;
		arr.add(new int[] {r, c});
		
		if(map[r][c] == 'D')
			find(r + 1, c);
		else if(map[r][c] == 'U')
			find(r - 1, c);
		else if(map[r][c] == 'L')
			find(r, c - 1);
		else if(map[r][c] == 'R')
			find(r, c + 1);
	}
}