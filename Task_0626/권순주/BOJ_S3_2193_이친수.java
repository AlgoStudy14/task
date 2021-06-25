import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * <문제 요약>
 * 문제 정의 : N자리 이친수의 개수
 * 문제 유형 : DP
 * 주의 사항 : D[0] = 0, D[1] = 1
 * 
 * <풀이 요약>
 * **그림 참고**
 * 
 * 점화식
 * D[N] = D[N-1] + D[N-2]
 * 
 */

public class BOJ_S3_2193_이친수 {

	static int N;
	static long[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1];

		System.out.println(func());
	}

	private static long func() {
		// 초기화
		dp[0] = 0;
		dp[1] = 1;

		// 점화식
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		// 결과값
		return dp[N];
	}

}
