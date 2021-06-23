import java.util.Scanner;

public class BOJ_S1_이동하기 {
	/*
	 * <문제>
	 * (1,1)부터 방을 들리며 (아래, 오른쪽, 우측하단 대각선만 가능) 사탕을 먹으면서 갈 때, (N,M)에 도착했을 때 사탕 개수 최댓값 구하는 문제.
	 * 
	 * <문제풀이>
	 * DP배열을 이용하여 이동하면서 이전 값들 최대값 비교.
	 */
	static int N, M;
	static int[][] map, dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++)
				map[i][j] = sc.nextInt(); 
		}
		int left = 0;
		int upper = 0;
		int left_upper = 0;
		int temp = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				left = dp[i][j - 1];
				upper = dp[i - 1][j];
				left_upper = dp[i - 1][j - 1];
				temp = Math.max(left, upper);
				dp[i][j] = Math.max(left_upper,  temp) + map[i][j];
			}
		}
		System.out.println(dp[N][M]);
	}
}
