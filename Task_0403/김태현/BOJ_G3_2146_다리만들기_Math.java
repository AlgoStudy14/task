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
 * 요구 개념/문제 유형 : BFS, 완전 탐색, 맨해튼 거리.
 * <풀이법>
 * 1. 모든 섬을 넘버링한다.
 * 2. 각 섬의 모든 좌표에서, 다른 섬으로의 모든 맨해튼 거리를 구하며 최소 거리를 갱신한다.
 */

public class BOJ_G3_2146_다리만들기_Math {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int number;
	static int min;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

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

		// 각 모든 섬에서 다른 섬까지 맨해튼 거리 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 만일 해당 좌표가 섬이라면
				if (map[i][j] != 0) {
					// 다른 섬으로의 모든 맨해튼 거리 구하기.
					simulation(i, j, map[i][j]);
				}
			}
		}
		System.out.println(min);
	}

	private static void simulation(int row, int col, int num) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && map[i][j] != num) {
					min = Math.min(min, (Math.abs(row - i) + Math.abs(col - j) - 1));
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
