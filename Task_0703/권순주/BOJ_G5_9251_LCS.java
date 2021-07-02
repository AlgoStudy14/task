import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_G5_9251_LCS {

	static String A, B;
	static int[][] dp;
	
	// https://www.youtube.com/watch?v=nyXkaHUahHk&t=14s 
	// 공부하면서 풀었슴당

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine();
		B = br.readLine();
		int lenA = A.length();
		int lenB = B.length();
		dp = new int[lenA + 1][lenB + 1];
		
		for (int i = 1; i <= lenA; i++) {
			for (int j = 1; j <= lenB; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) { // 1. A와 B의 문자가 같은 경우 -> 대각선 위 + 1
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else { // 2. A와 B의 문자가 다른 경우 -> Max(위, 왼쪽)
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		System.out.println(dp[lenA][lenB]);
	}

}
