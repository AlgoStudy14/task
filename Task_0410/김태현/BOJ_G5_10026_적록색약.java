package s0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * <문제 요약>
 * 구해야 하는 것 : 적록색약일때 볼 수 있는 구역의 수와 아닌 경우 볼 수 있는 구역의 수.
 * 문제 유형 : BFS(Flood fill)
 * <풀이법>
 * 1. R, G, B를 만날 때 마다 BFS를 진행하며 영역의 개수를 센다.
 * 2. 적록 색약인 경우를 고려하기 위하여, R, G, B를 모두 R로 변환한다.
 * 3. 1번을 반복한다.
 */
public class BOJ_G5_10026_적록색약 {
	static int N;
	static int[][] map; // 빨강 : 1, 초록 : 2, 파랑 : 3
	static boolean[][] visited;
	static int n, y;
	// 상하좌우.
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 초기화.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char letter = line.charAt(j);
				if (letter == 'R') {
					map[i][j] = 1;
				} else if (letter == 'G') {
					map[i][j] = 2;
				} else {
					map[i][j] = 3;
				}
			}
		}
		visited = new boolean[N][N];
		n = 0;
		y = 0;

		// 적록색약이 아닌 경우 영역의 개수 세기.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					n++;
					bfs(i, j, map[i][j]);
				}
			}
		}

		// 적록색약이 맞는 경우.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					map[i][j] = 1;
				}
			}
		}

		// 방문 배열을 초기화하고 다시 세기.
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					y++;
					bfs(i, j, map[i][j]);
				}
			}
		}

		System.out.printf("%d %d", n, y);
	}

	private static void bfs(int row, int col, int color) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, col });
		visited[row][col] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == color) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}
