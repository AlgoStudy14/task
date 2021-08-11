package algo0808;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 용사가 제한 시간 이내에 공주에게 도달할 수 있다면, 최단 시간을 출력
 * 문제 유형 : BFS
 * <풀이 요약>
 * 빈 공간(0), 벽(1), 그람(2)
 * 1. 최단 시간을 구해야 하므로 BFS를 사용
 * 2. 그람을 얻었을 때와 얻지 못했을 때의 방문기록을 다르게 저장해야 하므로 3차원배열 visited 사용
 * 3. 도착지에 도착했을 경우 최단 시간이므로 return
 */

public class BOJ_G5_17836_공주님을구해라 {

	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Pos {
		int x, y, time, haveGram;

		public Pos(int x, int y, int time, int haveGram) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.haveGram = haveGram;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int ans = bfs();
		System.out.println(ans != -1 ? ans : "Fail");
	}

	static int bfs() {
		Queue<Pos> Q = new LinkedList<>();
		Q.add(new Pos(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			int x = cur.x;
			int y = cur.y;
			int haveGram = cur.haveGram;
			
			if (cur.time > T) continue;
			if (x == N - 1 && y == M - 1) return cur.time;
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][haveGram]) continue;
				
				// 그람이 없는 상태
				if (haveGram == 0) {
					if (map[nx][ny] == 1) continue;
					
					visited[nx][ny][haveGram] = true;
					
					// 그람을 얻으면
					if (map[nx][ny] == 2) 
						Q.add(new Pos(nx, ny, cur.time + 1, 1));
					else
						Q.add(new Pos(nx, ny, cur.time + 1, haveGram));
				}
				// 그람이 있는 상태
				else {
					visited[nx][ny][haveGram] = true;
					Q.add(new Pos(nx, ny, cur.time + 1, haveGram));
				}				
			}
		}
		
		return -1;
	}
}
