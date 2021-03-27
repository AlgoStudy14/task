package s0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * <문제 요약>
 * 구해야 하는 것 : 연결된 단지의 개수, 각 단지별 집의 개수.
 * 요구 개념/문제 유형 : DFS.
 * <풀이법>
 * 1. 지도의 좌 상단부터 우 하단 까지 우선 집이있는곳(=1)을 찾고, 단지의 수를 늘린다.
 * 2. 그 곳을 찾았으면, DFS 탐색을 진행하며 주변 집의 방문 체크를 진행한다.
 * -> 상하좌우로 탐색하며, 방문 체크 하기(다음 집을 찾을때 이미 선택된 단지는 포함하면 안되기 때문에).
 * -> dfs 탐색이 모두 종료되면, 현재 집의 개수를 우선순위 큐에 추가하고 현재 최대 집의 개수를 초기화한다.
 * 3. 단지의수, 집의 개수를 차례로 출력한다.
 * <주의점>
 * 집의 개수를 인자에 넣고 체크했는데, 그렇게 하면 총 집의 개수를 구할 수 없다(dfs의 특성상..).
 * 따라서, 전역 변수로 빼서 집의 개수를 체크해 주는 것이 맞다.
 */

public class BOJ_S1_2667_DFS {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int estate;
	static PriorityQueue<Integer> home;
	static int cur_home;
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
					visited[i][j] = true;
					estate++;
					// dfs 탐색을 진행하면서 주변 집을 체크한다.
					cur_home = 1;
					dfs(i, j);
					// dfs 탐색이 모두 종료되면, 집의 개수를 우선순위 큐에 추가하고 현재 집의 개수를 초기화한다.
					home.offer(cur_home);
				}
			}
		}

		System.out.println(estate);
		while (!home.isEmpty()) {
			System.out.println(home.poll());
		}
	}

	private static void dfs(int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			// 지도를 벗어나지 않고, 방문한 적 없으며, 그 위치가 집이라면
			if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
				visited[nr][nc] = true;
				cur_home++;
				dfs(nr, nc);
			}
		}
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
