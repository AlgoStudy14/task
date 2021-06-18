import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : n가지 종류의 동전으로 k를 만드는데 필요한 최소 동전 개수
 * 문제 유형 : DP
 * 유의 사항 : 초기값 dp[0] = 0
 * 
 * <풀이 요약>
 * index | 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 * 	dp   | 0 1 2 3 4 1 2 3 4 5 2  3  1  2  3  3
 * 
 * => 결과
 * 1. index를 기준으로 탐색 시작
 * 2. coin을 돌리면서 조건 체크
 * 2-1. 코인을 사용해서 만들 수 있는 값이 존재해야 한다.(i - coin[j] >= 0 && dp[i - coin[j]] != -1)
 * 2-2. 동전 개수가 존재하지 않거나, 다른 동전을 사용하는 것이 더 적은 개수가 필요할 때 값을 바꿔준다.(dp[i] == -1 || dp[i - coin[j]] + 1 < dp[i])
 * 3. 점화식 : dp[i] = dp[i - coin[j]] + 1
 * 
 * for(1~k index 선택)
 * 	for(0~n coin 선택)
 * 		if(i - coin[j] >= 0 && dp[i - coin[j]] != -1)
 * 			if(dp[i] == -1 || dp[i - coin[j]] + 1 < dp[i])
 * 				dp[i] = dp[i - coin[j]] + 1
 * 
 * <피드백>
 * dp[i - coin[j]] != -1을 찾지 못해 시간이 오래걸렸다!
 * 작은 조건도 놓치지 말자~!!
 */

public class BOJ_S1_2294_동전_2 {

	static int n, k;
	static int[] coin, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coin = new int[n];
		dp = new int[k + 1];
		Arrays.fill(dp, -1);
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(func());
	}

	private static int func() {
		// 초기값
		dp[0] = 0;

		// 점화식 계산
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < n; j++) {
				if (i - coin[j] >= 0 && dp[i - coin[j]] != -1) {
					if (dp[i] == -1 || dp[i - coin[j]] + 1 < dp[i]) {
						dp[i] = dp[i - coin[j]] + 1;
					}
				}
			}
		}

		// 결과값 출력
		return dp[k];
	}

}
