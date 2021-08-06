import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G4_내리막길 {
	/*
	 * <문제>
	 * (0,0) -> (N, M)으로 내려가려고 한다. 상하좌우 이웃한 곳에 이동가능.
	 * 해당 칸의 숫자보다 이동하고자 하는 칸의 수가 더 작을때만 이동가능한데,
	 * 갈 수 있는 방법의 수는?
	 * 
	 * <문제풀이>
	 * N, M <= 500
	 * 각 지점의 높이 1만 이하.
	 * 	=> 최대 500 * 500 2차원 배열에서 상하좌우 전부 비교하게 생겻는데 시간초과 뜰듯.
	 * dp + bfs
	 */
	static int N, M, ans;
	static int[][] map, dp;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();
		}
		dp[0][0] = 1;
		bfs();
		System.out.println(dp[N - 1][M - 1]);
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[now[0]][now[1]] <= map[nr][nc])
					continue;
				dp[nr][nc] += dp[now[0]][now[1]];
				if(visited[nr][nc])
					continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
			}
		}
	}
}
