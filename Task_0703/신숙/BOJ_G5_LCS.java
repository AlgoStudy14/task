import java.util.Scanner;

public class BOJ_G5_LCS {
	/*
	 * <문제>
	 * LCS(LONGEST COMMON SubSequnce. => 가장 긴 공통 부분수열). 두 문자열이 주어지면, 부분수열 중 가장 긴 공통수열이 몇글자인지 출력하기.
	 * 
	 * <문제풀이>
	 * DP문제는 DP를 어떻게 적용할 것인지 찾는게 제일 어려운 문제... D -> T
	 * 맨 앞부터 비교해보기.
	 * 		A		C		A		Y		K		P
	 * C	0		1		1		1		1		1
	 * 
	 * A	1		1		2		2		2		2			
	 * 
	 * P	1		1		2		2		2		3
	 * 	
	 * C	1		2		2		2		2		3
	 * 	
	 * A	1		2		3		3		3		3
	 * 
	 * K	1		2		3		3		4		4					
	 * 
	 * 문자가 같은 부분이 있다면 이전 좌측 대각선에서 + 1을 하는 것.
	 * 
	 * <문제점>
	 * 1. 	dp[i][j - 1], dp[i - 1][j] 같은 부분에서 제일 처음일때 범위밖.
	 * 2. 답이 1씩 작게 나옴					=> 0보다 크다고 한 부분을 크거나 같다로 수정.
	 * 		ex) 예제답 3, AC, CA 가 0, ACA, CAK가 1이 나옴.	
	 * 3. 크거나 같다로 수정했음에도 ACA, CAK가 1이 나옴. 예쁘지도 않으니 배열 길이를 + 1씩해서 1부터 시작시키기.
	 * 
	 * <후기>
	 * dp 규칙을 찾을 때 아직은 펜으로 경우들 써보는거 아니면 찾기 힘든듯. 푸는 법 조금 잊은 후에 다시 푸려고해도 어려울 듯함.
	 */
	
	static String arr1, arr2;
	static int size1, size2, ans;
	static int[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arr1 = sc.next();
		arr2 = sc.next();
		size1 = arr1.length();
		size2 = arr2.length();
		
		dp = new int[size1 + 1][size2 + 1];
		for(int i = 1; i <= size1; i++) {
			for(int j = 1; j <= size2; j++) {
				if(arr1.charAt(i - 1) == arr2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		
		/*
		dp = new int[size1][size2];
		
		for(int i = 0; i < size1; i++) {
			for(int j = 0; j < size2; j++) {
				if(arr1.charAt(i) == arr2.charAt(j)) {
					if(i - 1 > 0 && j - 1 > 0)
						dp[i][j] = dp[i - 1][j - 1] + 1;
					//제일 앞 열인데, 제일 앞 열은 최대 부분수열 길이가 1이므로 같으면 1을 넣어줌.
					else
						dp[i][j] = 1;
				}
				else {
					if(i - 1 >= 0 && j - 1 >= 0)
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
					else if(i - 1 >= 0)
						dp[i][j] = Math.max(0, dp[i - 1][j]);
					else if(j - 1 >= 0)
						dp[i][j] = Math.max(dp[i][j - 1], 0);
					else
						dp[i][j] = 0;
				}
			}
		}
		*/
		System.out.println(dp[size1][size2]);
	}
}
