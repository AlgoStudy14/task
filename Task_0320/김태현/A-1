import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	static int T, N;
	static int res;
	static int[][] map;
	// 남아있는 코어를 넣을 배열.
	static ArrayList<int[]> rest;
	// 현재 전선의 길이
	static int len;
	// 현재 연결된 코어
	static int max_core;
	static int core;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화
			N = sc.nextInt();
			map = new int[N][N];
			rest = new ArrayList<int[]>();
			res = Integer.MAX_VALUE;
			max_core = Integer.MIN_VALUE;
			len = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (i != 0 && i != N - 1 && j != 0 && j != N - 1 && map[i][j] == 1) {
						// 가장자리에 있는 코어는 제외한다.
						rest.add(new int[] { i, j });
					}
				}
			}

			// 남은 코어에 대하여 DFS 탐색을 진행한다.
			dfs(0);
			// DFS에서 탈출하면 놓았던 선을 회수한다.
			System.out.printf("#%d %d\n", t, res);
		}
		sc.close();
	}

	private static void dfs(int depth) {
		// 만일, 모든 코어를 탐색하였다면 현재 전선의 길이를 결과와 비교하여 갱신한다.
		if (depth == rest.size()) {
			if (max_core < core) {
				max_core = core;
				res = len;
			} else if (max_core == core) {
				res = Math.min(res, len);
			}
			return;
		}
		// 코어의 상하좌우순으로 전선 놓고 dfs.
		for (int i = 0; i < 4; i++) {
			// 현재 코어
			int[] cur = rest.get(depth);
			int nr = cur[0] + dr[i];
			int nc = cur[1] + dc[i];

			// 전선을 중간에 회수하는지 체크
			boolean flag = false;
			// 다음 좌표가 맵을 벗어날 때 까지 반복.
			while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				// 만일 가는 도중 코어나 전선을 만난다면 깔린방향 반대로 전선 다시 회수 하고 다음 dfs 진행.
				if (map[nr][nc] == 1 || map[nr][nc] == -1) {
					// 반대방향 결정
					int dir = i;
					if (dir == 0) {
						dir = 1;
					} else if (dir == 1) {
						dir = 0;
					} else if (dir == 2) {
						dir = 3;
					} else {
						dir = 2;
					}
					nr += dr[dir];
					nc += dc[dir];
					// 전선회수(시작했던 코어를 다시 만날때 까지).
					while (map[nr][nc] != 1) {
						map[nr][nc] = 0;
						nr += dr[dir];
						nc += dc[dir];
						len--;
					}
					flag = true;
					break;
				}
				// 그렇지 않다면 전선 깔기
				else {
					map[nr][nc] = -1;
					len++;
					nr += dr[i];
					nc += dc[i];
				}
			}
			// 연결에 성공했는지
			if (!flag) {
				core++;
			}

			dfs(depth + 1);

			// dfs를 종료했다면, 마찬가지로 전선 회수.
			// 만일, 이전에 회수한 기록이 없는 경우에만 전선 회수.
			if (!flag) {
				// 반대방향 결정
				int dir = i;
				if (dir == 0) {
					dir = 1;
				} else if (dir == 1) {
					dir = 0;
				} else if (dir == 2) {
					dir = 3;
				} else {
					dir = 2;
				}
				nr += dr[dir];
				nc += dc[dir];
				// 전선회수(시작했던 코어를 다시 만날때 까지).
				while (map[nr][nc] != 1) {
					map[nr][nc] = 0;
					nr += dr[dir];
					nc += dc[dir];
					len--;
				}
				core--;
				flag = true;
			}
		}
	}
}
