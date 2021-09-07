package ss;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <풀이 : BFS>
 * 1. 입력을 받는다.
 * 2. 넘버링을 진행하며 가장 큰 크기를 구한다.
 */

public class BOJ_S1_1743_음식물피하기 {
	static int N, M, K, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			map[r][c] = 1;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(max);

		sc.close();
	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;

		int cnt = 1;
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (!visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
					cnt++;
				}
			}
		}

		max = Math.max(max, cnt);
	}
}
