package s0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * <문제 요약>
 * 구해야 하는 것 : 연결된 단지의 개수, 각 단지별 집의 개수.
 * 요구 개념/문제 유형 : BFS.
 * <풀이법>
 * 1. 지도의 좌 상단부터 우 하단 까지 우선 집이있는곳(=1)을 찾고, 단지의 수를 늘린다.
 * 2. 그 곳을 찾았으면, BFS 탐색을 진행하며 주변 집의 방문 체크를 진행한다.
 * -> 상하좌우로 탐색하며, 방문 체크 하기(다음 집을 찾을때 이미 선택된 단지는 포함하면 안되기 때문에).
 * -> 탐색이 종료되면, 해당 탐색으로 찾은 집의 개수를 우선 순위큐에 넣기.
 * 3. 단지의수, 집의 개수를 차례로 출력한다.
 */

public class BOJ_S1_2667_BFS {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int estate;
	static PriorityQueue<Integer> home;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		estate = 0;
		home = new PriorityQueue<>();

		// 지도의 좌 상단부터 우 하단 까지 우선 집이있는곳을 찾고, 단지의 수를 늘린다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					estate++;
					visited[i][j] = true;
					// 주변 모든 집을 찾기 위해 bfs 탐색 시작.
					bfs(i, j);
				}
			}
		}

		System.out.println(estate);
		while (!home.isEmpty()) {
			System.out.println(home.poll());
		}
	}

	private static void bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, col });
		int cnt = 1;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 지도를 벗어나지 않고, 방문한 적 없으며, 해당 위치가 집이라면
				if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					cnt++;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		// 해당 단지의 집의 개수를 우선순위큐에 추가.
		home.offer(cnt);
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
