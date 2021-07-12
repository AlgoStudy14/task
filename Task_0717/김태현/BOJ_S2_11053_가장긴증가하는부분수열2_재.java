import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 가장 긴 증가하는 부분 수열의 길이를 구하여라.
 * 문제 유형 : DP(N^2).
 * <풀이 요약>
 * 1. N번째 원소의 위치까지 LIS를 저장할 1차원 dp테이블을 만든다.
 * 2. 만일, N번째 위치가 이전 수들보다 큰 값을 갖는다면, 해당 수들 중 가장 큰 LIS를 갖는 수 + 1이 N번째 위치의 LIS이다.
 * -> 즉, dp[N] = Math.max(dp[N], dp[1] ~ dp[N - 1])(단, N번째 위치의 수가 1 ~ N - 1번째 위치의 수 보다 큰 경우).
 */

public class BOJ_S2_11053_가장긴증가하는부분수열2_재 {
	static int N;
	static int[] nums, dp;
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		max = 1;

		for (int i = 0; i < N; i++) {
			// 자기 자신의 길이는 1이므로, 일단 dp테이블의 값을 1로 초기화.
			dp[i] = 1;
			// 자기 자신보다 앞의 값들을 탐색하며 dp테이블 갱신
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					max = Math.max(max, dp[i]);
				}
			}
		}
		System.out.println(max);
	}
}
