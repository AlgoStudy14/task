package s0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 물에 잠기지 않는 안전 영역의 개수.
 * 문제 유형 : BFS(Flood fill), 완전 탐색.
 * <풀이법>
 * 1. 높이가 0 ~ 100인 경우 모두에 대해서 2번을 수행한다.
 * 2. 맵의 좌상단부터 우하단까지 탐색하면서, 잠기지 않는 영역을 만날때마다 BFS 탐색을 진행한다.
 * -> 영역의 개수를 하나 증가시킨다.
 * -> 방문한 영역(잠긴 지역 혹은 방문한 지역)은 방문 처리.
 * -> 맵을 벗어나지 않는다.
 * -> 방문하는 영역은 현재 높이보다 높은 곳만 방문한다.
 * <주의점>
 * 아예 잠기지 않는 영역도 있었다(즉, 물의 높이 0인 경우). 문제 조건을 항상 유의하도록 하자.
 */
public class BOJ_S1_2468 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int max;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = Integer.MIN_VALUE;

		// 높이 0 ~ 100 모두 고려.
		for (int height = 0; height <= 100; height++) {
			// 방문 배열 초기화
			visited = new boolean[N][N];
			// 맵 좌상단 ~ 우하단 탐색.
			int area = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 잠기지 않는 영역을 만나면 BFS 진행.
					if (map[i][j] > height && !visited[i][j]) {
						area++;
						bfs(i, j, height);
					}
				}
			}
			max = Math.max(max, area);
		}
		System.out.println(max);
	}

	private static void bfs(int row, int col, int height) {
		// 초기 위치를 넣는다.
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, col });
		visited[row][col] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			// 상하좌우 잠기지 않은 영역 탐색.
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] > height) {
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
