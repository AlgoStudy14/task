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
 * 요구 개념/문제 유형 : BFS, DP.
 * <풀이법>
 * 1. 모든 섬을 넘버링한다.
 * 2. 넘버링 된 섬을 기준으로 BFS 시뮬레이션을 진행한다.
 * -> 같은 섬을 만난다면 방문 체크를하고, 이동 거리는 그대로 유지한다.
 * -> 바다나 다른 섬을 만난다면 이동 거리를 비교하며 방문 여부를 판단하고, 이동 거리가 더 작다면 해당 좌표를 갱신한다(DP).
 * -> BFS가 끝나면 최소 다리 길이를 갱신한다. 
 * <참고 사항>
 * 섬을 찾았으면, 그 섬에 대하여 다른 섬까지 최소거리를 갱신하는 DP 방식을 사용해 보았다.
 * 이 방법에서 DP를 사용하는 이유는, 바다를 만나는 시점이 BFS가 동일한 레벨에서 진행된다는 보장이 없기 때문이다.
 */

public class BOJ_G3_2146_다리만들기_BFSDP {
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

		// 각 넘버링된 섬에서 다른섬으로의 최단거리를 BFS 시뮬레이션.
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 만일 해당 좌표가 섬이라면
				if (!visited[i][j] && map[i][j] != 0) {
					// 해당 섬을 기준으로 다른 섬들까지의 최단거리를 BFS를 이용하여 시뮬레이션한다.
					visited[i][j] = true;
					bfs2(i, j, map[i][j]);
				}
			}
		}
		System.out.println(min - 1);
	}

	private static void bfs2(int row, int col, int num) {
		// 이동거리를 저장할 맵 초기화
		dist_map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 현재 섬을 제외한 다른곳으로 가는 비용은 매우 크게 초기화.
				if (map[i][j] != num) {
					dist_map[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		// 같은섬 방문을 체크할 임시 배열
		boolean[][] visited2 = new boolean[N][N];

		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, col });
		visited2[row][col] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 지도를 벗어나거나, 같은 섬을 재방문하면 무시
				if (!checkBoundary(nr, nc) || visited2[nr][nc]) {
					continue;
				}
				// 같은 섬을 만나면 방문 체크 후 이동 거리는 유지
				if (map[nr][nc] == num) {
					visited[nr][nc] = true;
					visited2[nr][nc] = true;
					q.offer(new int[] { nr, nc });
					continue;
				}
				// 같은 섬이 아니면(바다, 다른 섬이라면) 현재 그 좌표까지의 거리와
				// 지금 좌표에서 해당 바다로가는 거리를 비교하여 dist테이블 갱신.
				if (dist_map[nr][nc] > dist_map[cur[0]][cur[1]] + 1) {
					dist_map[nr][nc] = dist_map[cur[0]][cur[1]] + 1;
					q.offer(new int[] { nr, nc });
				}
			}
		}

		// bfs가 끝나면 다른 섬으로 가는 최소 다리 거리 갱신
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 해당 좌표가 같은 섬이 아니고, 바다가 아니면(즉, 다른 섬이라면).
				if (map[i][j] != 0 && map[i][j] != num) {
					// 현재 최솟값과 비교하며 갱신(그 최솟값이 최소 다리길이).
					min = Math.min(min, dist_map[i][j]);
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
