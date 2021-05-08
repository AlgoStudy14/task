package algo0501;

import java.util.Scanner;
/*
S1
 * N*N표에서 (x1,y1)~(x2,y2) 구간 합 구하는 문제 -> 이 구간은 직사각형
 * N 표의크기 M 합을 구해야 하는 횟수 주어짐
 * N개의 줄에 표 숫자 주어지고
 * M개 줄에 구간합을 구해야할 좌표 주어짐
 */
public class BOJ_11660_구간합구하기5 {

	static int N,M;
	static int[][] map,dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N+1][N+1];
		dp = new int[N+1][N+1];
		
		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <=N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <=N; j++) {
				dp[i][j] = dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+map[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			System.out.println(dp[x2][y2]-dp[x2][y1-1]-dp[x1-1][y2]+dp[x1-1][y1-1]);
		}
	}

}
