import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (미완).
 * <문제 요약>
 * 문제 정의 : 가장 긴 증가하는 부분 수열의 길이를 구하여라.
 * 문제 유형 : DP(NlogN), 이분 탐색.
 * <풀이 요약>
 * 1. 배열의 크기가 곧 LIS인 1차원 dp테이블을 선언한다.
 * 2. N번째 숫자에 대하여, dp테이블에 해당 숫자가 더 작은 경우가 존재한다면, 해당숫자와 N번째 숫자를 교체한다.
 * -> 이 과정에서 LIS의 길이가 유지된다.
 * -> 만일, 그런 부분을 찾지 못하면 dp테이블에 해당 숫자를 삽입한다.
 * -> 이때, dp 배열은 오름 차순으로 정렬되며 저장되기 때문에 이분 탐색을 이용하여 삽입될 위치를 찾는다.
 */

public class BOJ_S2_11053_가장긴증가하는부분수열_재 {
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

		// 현재 dp테이블에 저장된 숫자 배열의 크기.
		int size = 0;
		// N번째 숫자까지 탐색한다.
		for (int i = 0; i < N; i++) {
			int idx = binarySearch(nums[i], 0, size - 1, size);
			// 만일, 인덱스가 -1라면 삽입할 위치를 찾지 못한 경우이다. 따라서 뒤에 삽입해준다.
			if (idx == -1)
				dp[size++] = nums[i];
			// 그렇지 않다면 삽입할 위치에 해당 위치를 넣어주면 된다.
			else
				dp[idx] = nums[i];
		}

		// 최종 size = LIS.
		System.out.println(size);
	}

	private static int binarySearch(int num, int start, int end, int size) {
		int res = -1;
		while (start <= end) {
			// 중앙 값을 찾는다.
			int mid = (start + end) / 2;
			// 만일, 현재 숫자가 더 큰 경우 뒷 부분을 탐색한다.
			if (num > dp[mid]) {
				start = mid + 1;
			}
			// 만일, 현재 숫자가 더 작거나 같은 경우 앞 부분을 탐색한다.
			else {
				res = mid;
				end = mid - 1;
			}
		}
		// 찾지 못한 경우
		if (start == size)
			return -1;
		return res;
	}
}
