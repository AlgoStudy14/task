import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * (미완).
 * <문제 요약>
 * 문제 정의 : LCS를 구하여라.
 * 문제 유형 : DP.
 * <풀이 요약>
 * 1. 대응되는 두 문자열을 각각 비교하고, 비교하는 문자열(B)이 비교 당하는 문자열(A)에 대하여 해당 위치에서 가질 수 있는 LCS의 최대 값을 저장할 2차원 dp테이블을 선언한다(상호).
 * 2. 문자열 B의 각 문자를 하나하나 A와 비교한다. 이 비교를 수행하며 2차원 dp 테이블을 갱신해 나간다.
 * -> 윗 행에서 얻을 수 있는 것 : B문자열의 이전 문자의 LCS 값을 참고할 수 있다.
 * -> 왼쪽 열에서 얻을 수 있는 것 : A문자열의 이전 문자의 LCS 값을 참고할 수 있다.
 * -> 윗 행, 왼쪽 열에서 얻을 수 있는 것 : 이전 문자까지의 LCS 값을 참고할 수 있다.
 * -> 따라서, 비교하는 문자가 다르다면 만약 다르다면 A, B문자열의 각 이전 문자 중 최대 값이 현재 위치에서 최대일 것이다.
 * -> 또한, 비교하는 문자가 같다면 A, B문자열의 공통 이전 문자의 LCS 값에 + 1을 시켜주면 될 것이다.
 * <피드백>
 * 어렵다 어려워..
 */

public class BOJ_G5_9251_LCS {
	static char[] A, B;
	static int[][] dp;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		int alength = a.length();
		int blength = b.length();
		A = new char[alength + 1];
		B = new char[blength + 1];
		for(int i = 1; i <= alength; i++) {
			A[i] = a.charAt(i - 1);
		}
		for(int i = 1; i <= blength; i++) {
			B[i] = b.charAt(i - 1);
		}
		
		// 첫 행에서도 이전 문자를 참고할 수 있도록 패딩을 준다.
		dp = new int[blength + 1][alength + 1];
		max = 0;
		
		// B의 모든 문자열을 A문자열과 비교
		for(int i = 1; i <= blength; i++) {
			for(int j = 1; j <= alength; j++) {
				// 만일 두 문자가 같은 경우
				if(B[i] == A[j]) {
					// 대각선의 값을 참고하여 LCS의 값을 + 1한다.
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				// 두 문자가 다른 경우
				else {
					// 각 문자열의 이전 문자 중 최대 LCS값을 선택.
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
	}
}
