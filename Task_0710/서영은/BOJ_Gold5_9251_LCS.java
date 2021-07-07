package algo210710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_Gold5_9251_LCS {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = in.readLine();
		String str2 = in.readLine();

		int oneLen = str1.length();
		int twoLen = str2.length();
		int dp[] = new int[oneLen];
		Arrays.fill(dp, -1);
		
		int twoLastIdx = -1;
		for (int oneIdx = 0; oneIdx < oneLen; oneIdx++) {
			for (int twoIdx = twoLastIdx + 1; twoIdx < twoLen; twoIdx++) {
				if(str1.charAt(oneIdx) == str2.charAt(twoIdx)) {
					twoLastIdx = twoIdx;
					dp[oneIdx] = twoLastIdx;
					break;
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < oneLen; i++) {
			if(dp[i] != -1) ++cnt;
		}
		
		System.out.println(cnt);
	}
}
