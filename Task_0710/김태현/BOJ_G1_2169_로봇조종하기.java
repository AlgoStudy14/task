import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 이동 방식이 제한된 로봇이 맵의 좌상단에서 우상단으로 내려갈 때, 조사할 수 있는 최대 가치의 합.
 * 문제 유형 : DP.
 * 주의 사항 : 로봇은 위로는 이동할 수 없다.
 * <풀이 요약 : DP>
 * 1. 각 위치로 갈 수 있는 최대 비용을 저장할 2차원 dp테이블을 만든다.
 * 2. 각 위치에 올 수 있는 최대 비용은 다음과 같이 결정된다.
 * -> 위에서 내려온다(단, 1번째 행은 오른쪽으로밖에 이동이 불가능하므로 제외한다).
 * -> 왼쪽에서 최댓값을 갱신하며 온다.
 * -> 오른쪽에서 최댓값을 갱신하며 온다.
 * -> 이때, 로봇은 방문한 지역은 재방문 할 수 없기 때문에 단순히 제일 좌측에서 온 최대값, 제일 우측에서 온 최대값을 기준으로 위에서 내려오는 값과 비교하여 갱신이 가능하다.
 * <피드백>
 * DP는 이전에 계산한 값을 데이터로 저장하고, 그 데이터를 바탕으로 현재까지의 최선을 저장하는 것이다.
 * (1) 위로는 이동할 수 없다는 점에서부터 한 행식 최선을 구한다는 것을 알았어야 한다.
 * (2) 해당 위치까지 오는 최대값을 어떻게 구할 수 있을지 생각해봤어야 한다. 결론적으로 위에서 오거나, 왼쪽에서 오거나, 오른쪽에서 오는 방법이 전부였다.
 */
public class BOJ_G1_2169_로봇조종하기 {
	static int N, M;
	static int[][] map;
	static int[][][] dp;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		// 왼쪽에서 부터 온 값과 위를 비교한 값, 오른쪽에서 부터 온 값과 위를 비교한 값을 저장하고 최종적으로 두 값을 비교한다.
		dp = new int[N][M][3];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 우선 첫 행을 누적합을 기반으로 채운다(오른쪽으로밖에 이동이 불가능하므로).
		dp[0][0][0] = map[0][0];
		for (int i = 1; i < M; i++) {
			dp[0][i][0] = dp[0][i - 1][0] + map[0][i];
		}

		// dp테이블을 갱신한다(한 행씩 갱신을 진행한다).
		for (int i = 1; i < N; i++) {
			// 왼쪽에서 온 값을 기반으로 dp테이블을 갱신한다.
			dp[i][0][1] = dp[i - 1][0][0] + map[i][0];
			for (int j = 1; j < M; j++) {
				// 위에서 내려온 값 vs 왼쪽에서 부터 갱신해온 최대 값.
				dp[i][j][1] = Math.max(dp[i - 1][j][0], dp[i][j - 1][1]) + map[i][j];
			}

			// 오른쪽에서 온 값을 기반으로 dp테이블을 갱신한다.
			dp[i][M - 1][2] = dp[i - 1][M - 1][0] + map[i][M - 1];
			for (int j = M - 2; j >= 0; j--) {
				// 위에서 내려온 값 vs 오른쪽에서 부터 갱신해온 최대 값.
				dp[i][j][2] = Math.max(dp[i - 1][j][0], dp[i][j + 1][2]) + map[i][j];
			}

			// 최종 최대 값
			for (int j = 0; j < M; j++) {
				// 위에서 비교한 값 중 더 큰 값.
				dp[i][j][0] = Math.max(dp[i][j][1], dp[i][j][2]);
				// 최종 최대값 갱신
				max = Math.max(max, dp[i][j][0]);
			}
		}
		System.out.println(dp[N - 1][M - 1][0]);
	}
}
