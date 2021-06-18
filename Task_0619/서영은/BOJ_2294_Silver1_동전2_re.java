package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294_Silver1_동전2_re {

	static int n, k, dp[], coin[];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[k+1];
		coin = new int[n];
		
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(in.readLine());
		}
		
//		Arrays.sort(coin);
		Arrays.fill(dp, 10001);
		
		dp[0] = 0;
		
		for (int idx = 0; idx < n; idx++) {
			for (int i = coin[idx]; i <= k; i++) {
				dp[i] = Math.min(dp[i - coin[idx]] + 1, dp[i]);
			}
		}
		
		if (dp[k] == 10001) {
			System.out.println(-1);
		} else {
			System.out.println(dp[k]);
		}
	}
}
