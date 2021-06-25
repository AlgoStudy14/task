import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_평범한배낭 {
	/*
	 * <문제>
	 * 여행에 필요한 N개의 물품이 각각 W 무게와 V 가치를 가지고 있음.
	 * 최대 K만큼 넣을 수 있을 대 배낭에 넣을 수 있는 물건의 가치의 최대값은?
	 * 
	 * <문제풀이>
	 * knapsack 문제.
	 * 조합 생각을 못 지우고 dp... 를 어떻게 사용해야할까 고민하다  "https://dundung.tistory.com/117" 참고..
	 * 1차원 dp와 2차원 dp가 있는데, 
	 * 1차원 dp[i] = i무게일 때 최대 가치.
	 * 2차원 dp[i][j] = i번째 물건을 기준으로 j가 k일 대, j-w[i] 가 가능하면 계속해서 j-- 를 해주며 최대값을 찾아감.
	 */
	static int N, K;
	static int[][] dp;
	static int[] W, V;
	public static void main(String[] args) throws Exception{
	 	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(reader.readLine());
	    N = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());
	    dp = new int[K + 1][N + 1];
	    W = new int[N + 1];
	    V = new int[N + 1];
	 
	    for(int i = 1; i <= N; i++) {
	    	st = new StringTokenizer(reader.readLine());
	    	W[i] = Integer.parseInt(st.nextToken());
	    	V[i] = Integer.parseInt(st.nextToken());
	    }
	 
	    for(int i = 1; i <= K; i++) {
	    	for(int j=1; j <= N; j++) {
	        	if(i >= W[j])
	            	dp[i][j] = Math.max(dp[i][j - 1], dp[i - W[j]][j - 1] + V[j]);
	            else
	            	dp[i][j] = dp[i][j - 1];
	        }
	    }
	 	System.out.println(dp[K][N]);
	 }
}
