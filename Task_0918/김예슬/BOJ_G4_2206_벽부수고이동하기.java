package algo0918;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의: 도착지까지의 최단 거리를 출력(불가능 -1)
 * 문제 유형: BFS
 * <풀이 요약>
 * 1. visited[N][M][벽 부숨 체크]
 *  1-1. 벽을 부쉈을 경우: 1, 벽을 아직 부수지 않았을 경우: 0
 * 2. bfs로 최단 거리 찾기
 *  2-1. (N - 1, M - 1)을 찾을 경우 거리 출력
 *  2-2. 불가능 하다면 -1 출력
*/

public class BOJ_G4_2206_벽부수고이동하기 {

	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static class Pos {
		int x, y, d, destory;

		public Pos(int x, int y, int d, int destory) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.destory = destory;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][2];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Pos> Q = new LinkedList<>();
		Q.add(new Pos(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			
			if (cur.x == N - 1 && cur.y == M - 1) return cur.d;
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (visited[nx][ny][cur.destory]) continue;
				
				if (map[nx][ny] == '1') {
					// 이동할 곳이 벽인 경우
					// 벽을 깰 수 없다면
					if (cur.destory == 1) continue;
					
					Q.add(new Pos(nx, ny, cur.d + 1, 1));
					visited[nx][ny][1] = true;
				} else {
					Q.add(new Pos(nx, ny, cur.d + 1, cur.destory));
					visited[nx][ny][cur.destory] = true;
				}				
			}
		}
		
		return -1;
	}
}
