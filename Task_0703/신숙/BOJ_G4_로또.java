import java.util.Scanner;

public class BOJ_G4_로또 {
	/*
	 *<문제>
	 * 로또는 1부터 m 까지 숫자 중에 N개의 수를 고르는 로또. 각 숫자는 고른 수보다 적어도 2배 이상이 되도록 고름.
	 * N = 4, M = 10일 때
	 * 	1 2 4 8
	 * 	1 2 4 9
	 * 	1 2 4 10
	 * 	1 2 5 10
	 * 같은방법으로 2장 이상 구매하지 않음.
	 * 
	 * <문제>
	 * DP.
	 * 1~M까지 N개 고르는 경우이므로.
	 * dp[X][Y] => X번째 수를 고를 때 1부터 Y까지 숫자 중에서 고르는 경우의 수. => dp [X-1][Y/2]+dp[X][Y-1]
	 * X번째 수를 고를 때 1부터 Y-1까지의 숫자 중에서 선택하는 경우는  dp [X][Y-1]
	 * X번째 수를 Y로 선택하는 경우는 dp [X-1][Y/2]
	 * 
	 * <문제점>
	 * dp 배열 int가 되지 않는다고 한다. long으로.
	 * 
	 */
	static int T, N, M;
	static long[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			dp = new long[N+1][M+1];
			
			for(int i = 0; i <= M; i++)
				dp[0][i] = 1L;
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++)
					dp[i][j] = dp[i - 1][j / 2] + dp[i][j - 1];
			}
			
			System.out.println(dp[N][M]);
		}
	}
}
