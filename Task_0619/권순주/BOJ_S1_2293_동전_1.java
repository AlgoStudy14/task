import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : n가지 종류의 동전으로 k원을 만드는 경우의 수
 * 문제 유형 : DP
 * 유의 사항 : 초기값 dp[0] = 1
 * 
 * <풀이 요약>
 * coin 1 
 * index | 0 1 2 3 4 5 6 7 8 9 10
 * 	dp   | 1 1 1 1 1 1 1 1 1 1 1
 * 
 * coin 2
 * index | 0 1 2 3 4 5 6 7 8 9 10
 * 	dp   | 1 1 2 2 3 3 4 4 5 5 6 
 * 
 * coin 3
 * index | 0 1 2 3 4 5 6 7 8 9 10
 * 	dp   | 1 1 1 1 1 4 5 6 7 8 10
 * 
 * => 결과
 * 1. 현재 coin을 선택한 뒤 coin을 기준으로 dp를 진행한다.
 * 2. dp를 할 때는 index가 coin일 때부터 값이 변경되기 시작한다.
 * 3. 점화식 : dp[index] += dp[index - coin]
 * 
 * for(0 ~ n - coin 선택)
 * 	for(coin ~ k - index 선택)
 * 		dp[index] += dp[index - coin]
 * 
 * <피드백>
 * 점화식 추출하는게 너무 어렵다..ㅜㅜ
 * 종이 없으면 못풀어!
 */

public class BOJ_S1_2293_동전_1 {

	static int n, k;
	static int[] coin, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coin = new int[n];
		dp = new int[k + 1];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(func());
	}

	private static int func() {
		// 초기값
		dp[0] = 1;

		// 점화식 계산
		for (int i = 0; i < n; i++) {
			for (int j = coin[i]; j <= k; j++) {
				dp[j] += dp[j - coin[i]];
			}
		}

		// 결과값 출력
		return dp[k];
	}
}
