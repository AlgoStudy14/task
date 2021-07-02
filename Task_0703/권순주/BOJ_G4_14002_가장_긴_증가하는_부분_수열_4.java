import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G4_14002_가장_긴_증가하는_부분_수열_4 {

	static int N;
	static int[] nums, dp, index;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N];
		index = new int[N]; // 수열 출력을 위한 index 저장 배열
		Arrays.fill(index, -1); // index 초기화
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// index[i] = j -> i번째는 j번째 다음을 의미
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i] && dp[i] < dp[j]) { // 숫자가 증가하고, 길이가 더 길다면
					dp[i] = dp[j]; // 값 update
					index[i] = j; // 현재 칸과 j번째까지의 길이를 합침을 의미
				}
			}
			dp[i]++; // 내꺼 개수 증가
		}

		int answer = 0;
		int maxIndex = 0;
		for (int i = 0; i < N; i++) {
			if (answer < dp[i]) {
				answer = dp[i];
				maxIndex = i; // 가장 긴 값을 가지는 수열의 최대 index
			}
		}

		// 반대로 index를 접근하면서 탐색
		Stack<Integer> reverseList = new Stack<>();
		while (maxIndex >= 0) {
			reverseList.add(nums[maxIndex]);
			maxIndex = index[maxIndex];
		}
		
		// 출력
		System.out.println(answer);
		while(!reverseList.isEmpty()) {
			System.out.printf("%d ", reverseList.pop());
		}
	}

}
