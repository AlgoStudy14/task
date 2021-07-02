package s0703;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 가장 긴 증가하는 부분 수열의 길이와 해당 수열을 순서대로 출력하라.
 * 문제 유형 : DP + 구현.
 * <풀이 요약 : DP + 구현>
 * 1. N번째 원소까지 가장 긴 부분 수열을 저장할 2차원 dp테이블을 ArrayList로 선언한다.
 * 2. N번째 원소의 크기에 대하여, 앞선 원소들의 크기들을 비교하며 해당 숫자보다 N번째 수가 크고 저장된 배열의 크기도 크다면, 해당 dp테이블의 위치의 값을 비우고, 앞선 배열에 해당 원소의 값을 추가하여 저장한다.
 * -> 따라서, dp의 모든 원소는 자기 자신의 배열로 초기화 한다.
 * -> 점화식은 다음과 같아진다 : dp[i]의 배열 = Math.max(dp[0]의 배열 + nums[i], dp[1] + nums[i], ... dp[i - 1] + nums[i], nums[i])(단, i번째 이전 수들의 크기, 배열의 크기 < i번째 수의 크기, 배열의 크기).
 * <피드백>
 * 다른 구현 방법도 있는 것 같다.
 */

public class BOJ_G4_14002_가장긴증가하는부분수열4 {
	static int N;
	static int[] nums;
	static ArrayList<ArrayList<Integer>> dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N];
		dp = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
			dp.add(new ArrayList<Integer>());
		}

		// 1번째 원소부터 N번째 원소까지 반복한다.
		int max = Integer.MIN_VALUE;
		int idx = 0;
		for (int i = 0; i < N; i++) {
			// 우선 현재 배열의 위치의 배열을 자신으로 초기화한다.
			dp.get(i).add(nums[i]);
			// 1번째 원소부터 현재 선택된 원소의 위치까지 크기가 크고, 배열의 크기도 큰 경우
			// 현재 위치의 배열을 초기화하고 앞선 배열에 현재 원소의 값을 더한 배열로 값을 갱신한다.
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i] && dp.get(j).size() + 1 > dp.get(i).size()) {
					dp.get(i).clear();
					dp.get(i).addAll(dp.get(j));
					dp.get(i).add(nums[i]);
				}
			}
			// 만일, 현재 갱신된 배열의 크기가 앞서 갱신된 배열의 크기보다 크다면, 해당 인덱스를 기억해둔다.
			if (max < dp.get(i).size()) {
				max = dp.get(i).size();
				idx = i;
			}
		}

		// 기억된 배열의 크기와 배열을 출력한다.
		System.out.println(dp.get(idx).size());
		for (Integer i : dp.get(idx))
			System.out.print(i + " ");
		sc.close();
	}
}
