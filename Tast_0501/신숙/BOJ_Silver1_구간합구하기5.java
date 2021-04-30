import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_Silver1_구간합구하기5 {
	/*
	 * <문제 : 구간 합 구하기 5>
	 * N * N 표에 수가 채워져 있음
	 * 구해야하는 횟수 M이 주어지고, M줄에 걸쳐 (x1, y1), (x2, y2)가 주어짐.
	 * 위 좌표부터 다음 좌표까지 합을 구하기.
	 * N <= 1024 M<= 100,000 수 <= 1000
	 * x1 <= x2, y1 <= y2
	 * 
	 *  <풀이>
	 *  그냥 완전탐색으로 합을 구하라는 문제라기엔 너무 쉬울 것 같고... => DP
	 *  DP로 짜려면 합을 저장하고 있어야 함.	=> 어디서부터 어디까지의 합인지 미리 알수 없으니 (1, 1)부터의 합으로.
	 *  dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + map[i][j]	=> 1부터니까 초기값인 i, j = 1이 된다면 dp[0][0]을 가르키게 되고 이는 초기화상태 이므로 0값이 들어감.
	 */
	static int N, M;
	static int[][] map, dp;
	static ArrayList<Integer> ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];
		ans = new ArrayList<>();
		int x1, x2, y1, y2;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++)
				map[i][j] = sc.nextInt();
		}
		for(int i = 1; i <= N; i++) {
		       for(int j = 1; j <= N; j++)
		    	   dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + map[i][j];
		}
		for(int k = 0; k < M; k++) {
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            //합구하기. => 이러면 dp 식이 아래가아니라 위에 있어야함.		=>  구글 참조.
            ans.add(dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]);
        }
	    for(int i = 0; i < M; i++)
	    	System.out.println(ans.get(i));
	}
}
