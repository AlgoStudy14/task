package s0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 임의의 섬을 잇는 가장 짧은 다리의 길이.
 * 요구 개념/문제 유형 : BFS, BFS 응용.
 * <풀이법>
 * 1. 모든 섬을 넘버링한다.
 * 2. 넘버링된 모든 섬을 초기 큐에 넣은 상태로, BFS 최단거리 탐색을 진행한다.
 * -> 바다를 만난다면, 이동 거리를 증가 시킨다.
 * -> 이동 거리를 기록할 때, 어떤 섬으로부터 최소 거리인지 표시해둔다(넘버링을 확장).
 * -> 다른 섬을 만난다면, 탐색이 종료된다.
 * 3. 맵의 섬과 섬의 경계의 합이 최소인 부분을 구한다.
 * <참고사항>
 * BFS를 응용한 두 번째 풀이법이다.
 * 어떤 대상과 대상의 거리를 조금 더 스마트한 BFS 시뮬레이션으로 구할 수 있다.
 */

public class BOJ_G3_2146_다리만들기_BFSBFS2 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int number;
	static int min;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	// 이동거리를 저장할 맵
	static int[][] dist_map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		number = 0;
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 넘버링
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					number++;
					visited[i][j] = true;
					map[i][j] = number;
					bfs(i, j);
				}
			}
		}

		// 섬 넘버링2 + 최단거리 탐색
		dist_map = new int[N][N];
		bfs2();

		// 섬의 경계의 합이 최소인 부분을 찾기.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				findMin(i, j);
			}
		}

		System.out.println(min);

		// 이해 안되면 주석 풀고 맵 상황 찍어보기
//		System.out.println("-------------섬의영역----------------");
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("-------------각 섬으로부터 해당 바다까지 이동거리----------------");
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(dist_map[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	private static void findMin(int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			// 지도를 벗어나지 않고, 다른 섬이라면
			if (checkBoundary(nr, nc) && map[nr][nc] != map[row][col]) {
				// 최솟값 갱신.
				min = Math.min(min, dist_map[nr][nc] + dist_map[row][col]);
			}
		}

	}

	private static void bfs2() {
		// 모든 넘버링된 섬을 큐에 넣는다.
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					q.offer(new int[] { i, j });
				}
			}
		}
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 맵을 벗어나지 않고, 바다를 만난다면 이동거리를 증가시킨다.
				if (checkBoundary(nr, nc) && map[nr][nc] == 0) {
					dist_map[nr][nc] = dist_map[cur[0]][cur[1]] + 1;
					// 해당 영역은 출발한 섬으로 기록해둔다.
					map[nr][nc] = map[cur[0]][cur[1]];
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

	private static void bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, col });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 지도를 벗어나지 않고, 방문한적 없으며, 섬이라면 각 섬을 넘버링.
				if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					map[nr][nc] = number;
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
