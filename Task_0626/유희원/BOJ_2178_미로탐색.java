package algo0626;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색 {

	static int N,M;
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		bfs(0,0);
		System.out.println(map[N-1][M-1]);
	}
	private static void bfs(int x, int y) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {x,y});
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0];
			int c = cur[1];
			visit[r][c] = true;
			for (int d = 0; d <4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr>=0&&nc>=0&&nr<N&&nc<M 
						&& map[nr][nc]==1 
						&& !visit[nr][nc]) {
					que.add(new int[] {nr,nc});
					visit[nr][nc] = true;
					//map에 방문한 좌표 이동횟수로 변경.
					map[nr][nc] = map[r][c] + 1;
				}
			}
		}
		
	}

}
