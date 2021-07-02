import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_S1_11057_오르막_수 {

	static long mod = 10007;
	static int N;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][10];
		// 초기화
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}

		// dp[N][L] = 길이가 N이고 마지막 숫자가 L인 오르막 수의 개수
		// dp[N][L] = hap(dp[N-1][K]) (0<=K<=L)
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k <= j; k++) {
					dp[i][j] += dp[i - 1][k];
					dp[i][j] %= mod; // mod 계산
				}
			}
		}

		long answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += dp[N][i];
		}
		answer %= mod; // mod 계산
		
		System.out.println(answer);
	}

}
