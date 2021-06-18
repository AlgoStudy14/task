import java.util.Scanner;

public class Main_BOJ_2294_동전2 {
	static int N, K;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		arr = new int[N];
		dp = new int[K + 1];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = 1; i < K + 1; i++) {
			dp[i] = Integer.MAX_VALUE-10;
		}

		for (int i = 0; i < N; i++) {
			for (int j = arr[i]; j <= K; j++) {
				dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
			}
		}

		if (dp[K] == Integer.MAX_VALUE-10)
			System.out.println("-1");
		else
			System.out.println(dp[K]);
	}

}
