import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_11053_가장_긴_증가하는_부분_수열 {

	static int N;
	static int[] nums, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) // 숫자가 작을 때만 진행
					dp[i] = Math.max(dp[i], dp[j]); // 최댓값을 저장
			}
			dp[i]++; // 내꺼 개수 증가
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}

}
