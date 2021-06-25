import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : (1,1)에서 (N,M)으로 이동할 때, 가져올 수 있는 사탕의 개수
 * 문제 유형 : DP
 * 주의 사항 : [N+1][M+1]로 만든 뒤, 테두리를 0으로 만들어준다
 * 
 * <풀이 요약>
 * map[N+1][M+1]
 * 0 0 0 0 0
 * 0 1 2 3 4
 * 0 0 0 0 5
 * 0 9 8 7 6
 * 
 * dp[N+1][M+1]
 * 0 0  0  0  0
 * 0 1  3  6  10
 * 0 1  3  6  15
 * 0 10 18 25 31
 * 
 * 점화식
 * for(i - 1 ~ N)
 * 	for(j - 1 ~ M)
 * 		D[i][j] = Max(D[i-1][j], D[i][j-1], D[i-1][j-1])
 * 
 */

public class BOJ_S1_11048_이동하기 {

	static int N, M;
	static int[][] map, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(func());
	}

	private static int func() {
		// 점화식
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1])) + map[i][j];
			}
		}

		// 결과값
		return dp[N][M];
	}

}
