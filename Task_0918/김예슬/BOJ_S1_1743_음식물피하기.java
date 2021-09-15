package 김예슬;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 문제 정의: 음식물 중 가장 큰 음식물의 크기를 출력
 * 문제 유형: BFS
 * <풀이 요약>
 * 1. 모든 통로에 대해 음식물이 떨어져 있는지 체크
 * 2. map 자체에 방문체크를 해줌
 *  2-1. map[x][y]라면 방문할 필요 없음
 * 3. 음식물의 크기를 bfs를 사용해 체크
 *  3-1. size라는 변수를 사용해 bfs를 할 수 있으면 증가시켜줌
*/

public class BOJ_S1_1743_음식물피하기 {

	static int N, M, K, max;
	static boolean[][] map;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new boolean[N + 1][M + 1];
		
		for (int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = true;
		}
		
		solve();
		System.out.println(max);
	}

	static void solve() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!map[i][j]) continue;
				
				bfs(i, j);
			}
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Pos> Q = new LinkedList<>();
		Q.add(new Pos(x, y));
		map[x][y] = false;
		int size = 1;
		
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
				if (!map[nx][ny]) continue;
				
				Q.add(new Pos(nx, ny));
				map[nx][ny] = false;
				++size;
			}
		}
		
		max = Math.max(max, size);
	}
}
