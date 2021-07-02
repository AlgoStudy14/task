import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS_11053 {
	
	static int[] input;
	static int[] dp;
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		input = new int[N];
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			LIS(i);
		}
		max = dp[0];
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);

}
	private static int LIS(int index) {
		if(dp[index]==0) {
			dp[index]=1;
		
		for (int i = index-1; i >= 0; i--) {
			if(input[i]<input[index]) {
				dp[index] = Math.max(dp[index], LIS(i) + 1);
			}
		}	
	}
	return dp[index];
}
}
