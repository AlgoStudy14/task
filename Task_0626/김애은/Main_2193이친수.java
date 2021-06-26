package study0626;

import java.util.Scanner;
/*
 * 이찬수에서 1이 연속으로 나타나지 않기 때문에
 * N자리 이진수 구할때는 N이 0이면 N-1번째도 0 or 1 , N이 1이면 N-1은 0
 * 그래서 dp[i][0]=dp[i-1][0]+dp[i-1][1];
 * dp[i][1]=dp[i-1][0]
 */
public class Main_2193이친수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		long [][]dp=new long[N+1][2];
		dp[1][0]=0;
		dp[1][1]=1;
		for(int i=2;i<=N;i++) {
			dp[i][0]=dp[i-1][0]+dp[i-1][1];
			dp[i][1]=dp[i-1][0];
		}
		System.out.println(dp[N][1]+dp[N][0]);
	}
	

}
