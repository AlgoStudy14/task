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
 * 요구 개념/문제 유형 : BFS, 완전 탐색.
 * <풀이법>
 * 1. 모든 섬을 넘버링한다.
 * 2. 좌 상단부터 우 하단까지, 섬을 만날 때 마다 bfs 최단거리 시뮬레이션을 진행한다.
 * -> 지도 범위 밖이면 무시.
 * -> 방문한 적이 있으면 무시.
 * -> 바다를 만난 경우만 이동거리 갱신.
 * -> 다른 섬을 만나는 순간 종료(최단거리 종료조건).
 * <참고사항>
 * 무려 4가지의 각기 조금씩 다른 아이디어로 푸는 방법을 발견하였다!
 * 4가지 모두 배울만 한 아이디어라고 생각하여 모두 구현해 보았다.
 * 이 코드는 BFS를 넘버링할 때 한번, 최단 거리를 구할 때 한 번 총 2번 사용하는 기본적인 풀이법이다.
 */

public class BOJ_G3_2146_다리만들기_BFSBFS {
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

		// 섬을 만날때마다 bfs 최단거리 시뮬레이션을 진행한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 바다가 아니라면(즉 임의의 섬이라면)
				if (map[i][j] != 0) {
					bfs2(i, j);
				}
			}
		}

		System.out.println(min);
	}

	private static void bfs2(int row, int col) {
		// 이동 거리를 저장할 맵
		dist_map = new int[N][N];
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, col });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 지도를 벗어나면 무시
				if (!checkBoundary(nr, nc)) {
					continue;
				}
				// 다른 섬을 만나는 순간 최단거리 탐색 종료
				if (map[nr][nc] != map[row][col] && map[nr][nc] != 0) {
					min = Math.min(min, dist_map[cur[0]][cur[1]]);
					break;
				}
				// 방문한적 없으며, 바다라면 이동거리를 갱신한다.
				if (dist_map[nr][nc] == 0 && map[nr][nc] == 0) {
					dist_map[nr][nc] = dist_map[cur[0]][cur[1]] + 1;
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
