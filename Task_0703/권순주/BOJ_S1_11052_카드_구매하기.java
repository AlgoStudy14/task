import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_11052_카드_구매하기 {

	static int N;
	static int[] cards, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		cards = new int[N + 1];
		dp = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
			dp[i] = cards[i];
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				// 카드를 사용하거나, 사용하지 않거나 중에 큰 값을 저장
				dp[i] = Math.max(dp[i], dp[i - j] + cards[j]);
			}
		}

		System.out.println(dp[N]);
	}

}
