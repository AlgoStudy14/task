import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 가장 긴 등산로의 길이
 * 문제 핵심 요약 : dfs
 * 
 * <풀이법 요약>
 * [main]
 * 1. 입력받을 때 지형의 최댓값을 기억한다.
 * 2. 최댓값일 때 dfs를 돌린다.
 * 
 * [dfs]
 * 1. 어디까지 돌려야할지 정해지지 않았기 때문에 계속 answer를 계산해 주었음
 * 
 * 2. 높은 지형에서 낮은 지형으로 이동 가능할 때
 * 2-1. 방문 표시
 * 2-2. 등산로의 길이를 +1해서 dfs
 * 2-3. 방문 취소
 * 
 * 3. 지금까지 산을 깎지 않았고 + 더 높은 지형으로 이동 가능할 때
 * 더 높은 지형으로 이동 가능 하다는 것은 지형의 차이가 K 미만임을 의미한다.
 * 3-1. 기존의 지형 높이를 tmp에 저장
 * 3-2. 지형을 깎았다고 표시
 * 3-3. 방문 표시
 * 3-4. 현재 지형보다 1작게 깎는다. (가장 긴 등산로를 찾는 것이기 때문에 1만 작게 해주면 된다)
 * 3-5. 등산로의 길이를 +1애서 dfs
 * 3-6. 기존의 지형 높이를 3-1에서 저장한 tmp로 바꾸기
 * 3-7. 방문 취소
 * 3-8. 지형 깎은거 취소 
 * 
 */

public class Solution_1949_등산로_조성 {

	static int[][] map;
	static boolean[][] visited;
	static int max = -1;
	static int N, K, answer;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			max = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (max < map[i][j]) { // 최대 높이 기억
						max = map[i][j];
					}
				}
			}
			answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) { // 최대 높이에서만 시작
						visited[i][j] = true; // 시작점 방문 했음
						dfs(i, j, 1, false);
						visited[i][j] = false; // 시작점 방문 취소
					}
				}
			}
			System.out.printf("#%d %d\n", t, answer);
		}
	}

	private static void dfs(int x, int y, int cnt, boolean check) {
		answer = Math.max(answer, cnt); // answer를 최댓값으로 계속 계산
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
				continue;
			}
			if (map[nx][ny] < map[x][y]) { // 높은 지형에서 낮은 지형으로 이동할 때
				visited[nx][ny] = true; // 방문 표시
				dfs(nx, ny, cnt + 1, check); // cnt + 1 해서 dfs
				visited[nx][ny] = false; // 방문 취소
			} else if (!check && map[nx][ny] - map[x][y] < K) { // 지형을 깍지 않았고 + 더 높은 지형으로 이동할 수 있을 때
				int tmp = map[nx][ny]; // 기존의 값을 tmp에 저장
				check = true; // 지형을 깎았다고 표시
				visited[nx][ny] = true; // 방문 표시
				map[nx][ny] = map[x][y] - 1; // 나보다 1의 높이만 작으면 된다.
				dfs(nx, ny, cnt + 1, check); // cnt + 1 해서 dfs
				map[nx][ny] = tmp; // 기존의 값으로 되돌리기
				visited[nx][ny] = false; // 방문 취소
				check = false; // 지형 깍은거 되돌리기
			}
		}
	}

}
