package s0626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 맵의 좌상단부터 우하단까지 사탕을 획득하며 이동할 수 있을 때, 최대로 가져갈 수 있는 사탕의 개수.
 * 문제 유형 : DP.
 * <풀이 요약 : DP>
 * 1. 각 칸에서 가질 수 있는 최대 사탕의 개수를 저장할 2차원 dp테이블을 만든다.
 * 2. 점화식
 * -> 초기 dp[0][0]는 map[0][0]의 값으로 초기화한다.
 * -> dp[r][c] = map[r][c] + Math.max(dp[r - 1][c], dp[r][c - 1], dp[r - 1][c - 1])로 갱신한다.
 * 3. dp[N - 1][M - 1]을 출력한다.
 * <피드백>
 * dp 테이블의 각 원소는 최대 값을 가진다는 점을 활용하여 점화식을 도출하였다.
 * 해당 방식은 BFS와 함께 사용될수도 있으므로 잘 기억해두면 좋을 것 같다.
 */
public class BOJ_S1_11048_이동하기 {
	static int N, M;
	static int[][] map, dp;

	public static void main(String[] args) throws IOException {
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
		// dp 테이블 선언 및 초기화.
		dp = new int[N][M];
		dp[0][0] = map[0][0];
		// dp 테이블 갱신.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 초기값은 제외
				if (i == 0 && j == 0) {
					continue;
				}
				// 1행인 경우
				if (i == 0) {
					dp[i][j] = map[i][j] + dp[i][j - 1];
				}
				// 1열인 경우
				else if (j == 0) {
					dp[i][j] = map[i][j] + dp[i - 1][j];
				}
				// 그 외 모든 경우
				else {
					dp[i][j] = map[i][j] + Math.max(Math.max(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
				}
			}
		}

		// 결과 출력
		System.out.println(dp[N - 1][M - 1]);
	}
}
