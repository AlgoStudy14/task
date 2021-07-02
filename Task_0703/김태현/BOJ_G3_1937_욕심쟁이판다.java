package s0703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* (미완)
 * <문제 요약>
 * 문제 정의 : 판다는 현재 이동 지점에서 다음 지점에 대나무가 많은 경우만 이동이 가능할 때, 판다가 생존할 수 있는 최대 일 수.
 * 문제 유형 : (BFS + 백트래킹), (DP + DFS)
 * 
 * <풀이 요약 : BFS + 백트래킹 : 실패>
 * 1. 맵의 좌 상단부터 우 하단까지 BFS 탐색을 진행한다.
 * 2. BFS는 다음과 같은 방식으로 탐색을 진행한다.
 * -> 우선, 현재 생존 일수를 1일로 초기화한다.
 * -> 상하좌우 지점이 이동가능하면, 생존 일수를 + 1하고 최대 생존일수를 갱신한다.
 * -> 단, 맵 밖으로는 이동하지 않는다.
 * -> 단, 방문한 지점은 방문하지 않는다.
 * -> 단, 다음 대나무의 개수가 큰 경우에만 이동한다.
 * <피드백>
 * BFS를 이용하면 시간 초과가 발생한다. 백 트래킹을 적용해보았지만 여전히 40퍼 쯤에서 시간 초과!
 * 
 * <풀이 요약 : DP + DFS>
 * 1. 판다가 해당 위치에서 이동할 수 있는 최대 길이를 저장할 2차원 dp테이블을 선언한다.
 * 2. DFS를 이용하여 좌상단 부터 우하단까지 해당 위치부터 대나무가 더 큰 경우로만 이동하면서 dp테이블을 갱신한다.
 * -> 단, dp테이블이 이미 갱신된 경우에는 해당 부분은 DFS를 진행하지 않고 건너뛴다.
 * (DFS 탐색)
 * -> 현재 위치를 방문한 적 있다면 현재 위치의 dp테이블 값을 return한다.
 * -> 현재 위치를 방문한 적 없다면 현재 위치의 dp테이블 값을 1로 초기화한다.
 * -> 맵 밖으로는 이동하지 않는다.
 * -> 방문한 지역은 재방문하지 않는다.
 * -> 다음 위치의 대나무의 개수가 많은 경우만 방문한다.
 * -> 모든 탐색이 종료되면 해당 위치의 dp테이블의 값을 넘겨준다.
 * (테이블 갱신)
 * -> 4방 탐색에서 넘어온 값은 현재 위치의 dp테이블 값과 해당 위치에서 넘어온 dp테이블의 값 + 1을 비교하고 더 큰 값을 갱신한다.
 * <피드백>
 * DFS의 로직을 굉장히 꼼꼼하게 설계해야 한다. dp아이디어는 생각보다 쉽지만 실상 DFS 구현이 복잡한 문제!
 */

public class BOJ_G3_1937_욕심쟁이판다 {
	static int n;
	static int[][] map;
	static int[][] dp;
	static int max;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		// 해당 위치에서 판다가 이동할 수 있는 최대 일 수를 저장할 배열.
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = Integer.MIN_VALUE;

		// 맵의 좌 상단부터 우 하단까지 모두 BFS탐색을 진행해본다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 방문했던 지점은 방문하지 않는다.
				if (dp[i][j] > 0)
					continue;
				// 만일, 방문하지 않았다면, DFS탐색을 진행하며 dp테이블을 갱신한다.
				max = Math.max(max, dfs(i, j));
			}
		}

		System.out.println(max);
	}

	private static int dfs(int r, int c) {
		// 만일, 현재 방문한 지점의 값이 이미 기록된 값이 있다면 그대로 return
		if (dp[r][c] > 0) {
			return dp[r][c];
		}
		// 현재 위치의 값을 1로 초기화한다.
		dp[r][c] = 1;

		// 현재 위치에서 4방 탐색을 진행한다.
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 맵 밖으로 이동한다면 제외한다.
			if (checkBoundary(nr, nc))
				continue;
			// 다음 대나무의 수가 더 크다면, 해당 위치에서 반환된 값 + 1과 현재 위치의 값을 비교하여 갱신한다.
			if (map[r][c] < map[nr][nc])
				dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
		}

		// 모든 탐색이 종료되었다면 해당 위치의 값을 return한다.
		return dp[r][c];
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr < 0 || nr >= n || nc < 0 || nc >= n;
	}
}
