import java.util.Arrays;
import java.util.Scanner;

public class BOJ_S1_동전2 {
	/*
	 * <문제>
	 * 문제 자체는 동전 1과 거의 같은 문제로 보임. 차이는 가치가 같은 동전이 여러번 주어질 수도 있다고 함. 동전의 개수가 출력 불가능할 때 -1 출력.
	 * 
	 * <조건>
	 * 1 <= N <= 100, 1 <= K <= 10,000	/ 동전의 가치는 10만보다 작은 자연수.
	 * 
	 * <문제풀이>
	 * 동전 1과 거의 동일하지만, 0으로 초기화 하면 안되고, 가치가 10만보다 작은 자연수니 100,001로 초기화
	 * 추가로 경우의 수가 아닌 사용한 동전의 최소 개수
	 */
	
	static int N, K;
	static int[] dp, coin;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		dp = new int[K + 1];
		coin = new int[N];
		
		for(int i = 0; i < N; i++)
			coin[i] = sc.nextInt();
		
		Arrays.fill(dp, 100001);
		dp[0] = 0;
		
		for(int i = 0; i < N; i++) {			//몇 번째 동전을 사용할 때마다.
			for(int j = coin[i]; j <= K; j++)	//dp값 갱신
				dp[j] = Math.min(dp[j],  dp[j - coin[i]] + 1);
		}
		
		System.out.println(dp[K] == 100001 ? -1 : dp[K]);
	}
}
