import java.util.Scanner;

public class BOJ_S1_동전1 {
	/*
	 * <문제>
	 * 동전들을 적절히 `조합`하여 가치의 합이 K가 되도록 하는 경우의 수.
	 * 
	 * <조건>
	 * 1 <= N <= 100, 1 <= K <= 10,000	
	 * 
	 * <풀이>
	 * N=3, k=10. c[1] = 1, c[2] = 2, c[3] = 5의 경우로 조합할 때.
	 * 1로만 만들기, 2로만 만들기, 3으로만 만들기, 1 8개, 2 1개, 1 6개 2 2개, 1 4개 2 3개, 1 2개 2 4개. 1 3개 2 1개 5 1개, 1 5개 5 1개
	 * dp를 써야 하는데...	dp[i] = x. i는 금액. x는 경우의 수가 될 것.
	 * 1	2	3	4	5	6	7	8	9	10
	 * 1	1	1	1	1	1	1	1	1	1
	 * 
	 * 1	2	3	4	5	6	7	8	9	10
	 * 1	2	2	3	3	4	4	5	5	6
	 * 
	 * 1	2	3	4	5	6	7	8	9	10
	 * 1	2	2	3	4	5	6	7	8	10
	 * 
	 * 동전을 한가지씩해서 경우를 더하는 방식으로.
	 * 
	 * <문제점>
	 * 일단 답이 0이 나옴.	=> dp[j - coin[i] ] 에서 이전의 값이 안들어가있어서. dp[0]을 1로.
	 * 테케 답이 2가 나옴.	=> for 바깥쪽 1부터 시작했음... 코인은 0번부터 저장.
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
		dp[0] = 1;
		for(int i = 0; i < N; i++) {			//몇 번째 동전을 사용할 때마다.
			for(int j = coin[i]; j <= K; j++)	//dp값 갱신
				dp[j] = dp[j] + dp[j-coin[i]];
		}
		
		System.out.println(dp[K]);
	}
}
