package study0227;

import java.util.Arrays;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 돈을 인출하는데 필요한 최소 시간.
 * 문제 유형 : 구현.
 * 요구 개념 : 정렬, SJF.
 * <풀이법 요약>
 * 1. 입력받은 숫자를 오름차순으로 정렬한다.
 * 2. 각 사람이 걸리는 시간의 누적 합을 구한다.
 */

public class BOJ_S3_11399 {
	static int N;
	static int[] nums;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		// 배열 오름차순 정렬
		Arrays.sort(nums);
		// 누적합 구하기
		int tot = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			tot += sum;
		}
		// 결과 출력
		System.out.println(tot);
		sc.close();
	}
}
