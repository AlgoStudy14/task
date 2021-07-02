import java.util.Scanner;

public class BOJ_9251_LCS {

	static char[] str1;
	static char[] str2;
	static Integer[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		str1 = sc.nextLine().toCharArray(); //문자열 -> char[]
		str2 = sc.nextLine().toCharArray();
		dp = new Integer[str1.length][str2.length];
		System.out.println(LCS(str1.length-1,str2.length-1));
	}
	private static int LCS(int i, int j) {
		if(i==-1||j==-1) return 0; //범위밖
		if(dp[i][j]==null) { //탐색 안한경우. 여길 Null 비교 하려고 Integer형 배열
			dp[i][j] = 0;
			if(str1[i]==str2[j]) dp[i][j] = LCS(i-1,j-1)+1;
			else dp[i][j] = Math.max(LCS(i-1, j), LCS(i, j-1));
		}
		return dp[i][j];
	}

}
