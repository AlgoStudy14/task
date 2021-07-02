import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_1937_욕심쟁이_판다 {

	static int N;
	static int[][] map, dp;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solve());
	}

	private static int solve() {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// dp값이 존재하지 않을 때만 시작
				if (dp[i][j] == 0) {
					// 최댓값으로 값 계속 체크!
					answer = Math.max(answer, dfs(i, j));
				}
			}
		}

		return answer;
	}

	private static int dfs(int x, int y) {
		// 만약 dp에 값이 존재하면 그 값을 return
		if (dp[x][y] != 0) {
			return dp[x][y];
		}

		// 값이 존재하지 않기 때문에 1로 해줌
		dp[x][y] = 1;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			// 대나무가 많을 때만 이동! 욕심쟁이~
			if (map[x][y] < map[nx][ny]) {
				// 현재 값과 다음 이동한 값 + 1(이동했으니까 1추가) 중에 더 큰값을 계속 저장해준다
				dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
			}
		}

		return dp[x][y];
	}

}
