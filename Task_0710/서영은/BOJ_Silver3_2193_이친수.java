package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_Silver3_2193_이친수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(in.readLine());
		
		long dp[] = new long[N+1];
		
		if(N == 1) {
			bw.write("1\n");
			
			in.close();
			bw.flush();
			bw.close();
			return;
		}
		
		dp[0] = 0;
		dp[1] = 1;
		
		for (int idx = 2; idx <= N; idx++) {
			dp[idx] = dp[idx-1] + dp[idx-2];
		}
		
		bw.write(dp[N] + "\n");
		
		in.close();
		bw.flush();
		bw.close();
	}
}
