import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 가장 길게 만들 수 있는 등산로의 길이.
 * 문제 유형/요구 개념 : DFS.
 * <풀이법 요약>
 * 1. 값을 입력 받으며 가장 높은 봉우리의 높이를 기억한다.
 * 2. 가장 높은 봉우리의 높이를 바탕으로 높은 봉우리를 모두 찾는다.
 * 3. 해당 봉우리에 대하여 DFS를 진행한다.
 * -> 다음 좌표는 주어진 좌표(맵)를 벗어나지 않는다.
 * -> 다음 좌표는 방문한 상태가 아니다.
 * -> 다음 좌표의 높이가 현재 높이에 비하여 낮은 경우만 이동할 수 있다.
 * -> 다음 좌표의 높이가 현재 높이에 비하여 높거나 같다면 '딱 한번'만 높이를 깎아 이동 가능하다(단, 깎을 수 있는 높이는 최대 K).
 * -> 이동은 상하좌우로만 가능하다.
 * <주의점>
 * dfs 방문에 대한 조건을 꼼꼼히 체크 해주어야 하는 문제이다. 
 * 특히 조건이 많은 경우, 모든 조건을 꼼꼼히 고려하였는지 파악하여 디버깅하는데 낭비하는 시간을 줄이도록 하자.
 */
public class Solution_1949 {
	static int T, N, K;
	static int[][] map;
	static boolean[][] visited;
	static boolean flag;
	static int max_length;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			visited = new boolean[N][N];
			flag = false;
			max_length = 1;
			int max_height = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (max_height < map[i][j]) {
						max_height = map[i][j];
					}
				}
			}

			// 가장 높은 봉우리에 대하여 dfs 진행.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max_height) {
						visited[i][j] = true;
						dfs(1, i, j);
						visited[i][j] = false;
					}
				}
			}

			System.out.printf("#%d %d\n", t, max_length);
		}
		sc.close();
	}

	private static void dfs(int length, int r, int c) {
		// 현재 길이가 최대 공사 길이보다 길다면, 길이 갱신
		if (max_length < length) {
			max_length = length;
		}
		// 4방향에 대하여 탐색한다.
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			// 범위 안에 존재하고
			if (checkRange(nr, nc)) {
				// 다음 좌표의 높이가 현재 좌표의 높이보다 낮고, 방문한적 없으면
				if (map[r][c] > map[nr][nc]) {
					if (!visited[nr][nc]) {
						// dfs 탐색 진행
						visited[nr][nc] = true;
						dfs(length + 1, nr, nc);
						visited[nr][nc] = false;
					}
				}
				// 다음 좌표의 높이가 현재 좌표의 높이보다 작거나 같다면
				// 1회에 한해서, 허용 범위를 충족 시키는 경우만 깎고 진입을 허용한다.
				else {
					// 허용 범위인지 체크
					if (map[r][c] > map[nr][nc] - K && !flag && !visited[nr][nc]) {
						// 깎은 여부 체크, 실제로 높이 깎아주기
						flag = true;
						int temp = map[nr][nc];
						map[nr][nc] = map[r][c] - 1;
						visited[nr][nc] = true;
						// dfs 탐색
						dfs(length + 1, nr, nc);
						// 탐색을 끝냈으면 원래대로 돌려 놓기
						visited[nr][nc] = false;
						map[nr][nc] = temp;
						flag = false;
					}
				}
			}
		}
	}

	private static boolean checkRange(int cur_r, int cur_c) {
		if (cur_r >= 0 && cur_r < N && cur_c >= 0 && cur_c < N) {
			return true;
		}
		return false;
	}
}
