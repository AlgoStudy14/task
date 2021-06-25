package algo0626;

import java.util.Scanner;

public class BOJ_12865_평범한배낭 {

	static Integer[][] dp;
	static int[] W,V;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		W = new int[N];
		V = new int[K];
		
		dp = new Integer[N][K+1];
		
		for (int i = 0; i < N; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}
		
		System.out.println(knapsack(N-1,K));
	}
	private static int knapsack(int i, int k) {
		
		if(i<0) return 0;
		
		if(dp[i][k] == null) {
			if(W[i]>k) dp[i][k] = knapsack(i-1, k);
			else dp[i][k] = Math.max(knapsack(i-1, k), knapsack(i-1, k-W[i])+V[i]);
		}

		
		return dp[i][k];
	}

}
