package algo0717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int cheeseCnt;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M];

		int ans;
		for (ans = 0; isCheese(); ans++) {
			for (boolean[] arr : visited) {
				Arrays.fill(arr, false);
			}
			visited[0][0] = true;
			cheeseCnt = 0;

			DFS(0, 0);
		}
		System.out.println(ans);
		System.out.println(cheeseCnt);
	}

	public static boolean isCheese() {
		// 2이면 공기와 닿은 부분, 녹여녹여~
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					map[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	public static void DFS(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int dx = x + dr[i];
			int dy = y + dc[i];

			if (dx < 0 || dy < 0 || dx >= N || dy >= M) {
				continue;
			}

			if (!visited[dx][dy]) {
				visited[dx][dy] = true;
				if (map[dx][dy] == 1) {
					map[dx][dy] = 2;
					cheeseCnt++;
				} else {
					DFS(dx, dy);
				}
			}
		}
	}

}
