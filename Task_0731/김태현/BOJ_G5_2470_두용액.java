package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 두 산성 용액의 합이 0에 가까워 지는 경우를 출력하라.
 * 문제 유형 : 정렬, 투 포인터.
 * <풀이 요약>
 * 1. 모든 용액을 값 순으로 정렬한다.
 * 2. 인덱스를 가장 첫 번째(i)와 마지막(j)에 위치시킨다.
 * -> 우선 현재 인덱스의 합을 구한다.
 * -> 두합의 절대 값이 현재까지 구한 최소 값과 비교하여 값을 갱신한다.
 * -> 만일, 두 합이 양수라면 j - 1.
 * -> 그렇지 않다면 i + 1.
 * -> i < j인 경우에만 반복한다.
 * <피드백>
 * 두 개로 나누어서 탐색하기 때문에 이분 탐색이라고..? 그런데 투 포인터를 곁들인..
 * 걍 투포인터 아닌가 ㅋㅋ
 * 수학적 사고력이 필요해보임!
 */

public class BOJ_G5_2470_두용액 {
	static int N;
	static int[] arr;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE;

		// 정렬
		Arrays.sort(arr);

		// 시작 인덱스와 끝 인덱스
		int i = 0, j = N - 1;
		int[] res = new int[2];
		while (i < j) {
			// 두 합을 구한다.
			int sum = arr[i] + arr[j];
			// 최소 값을 갱신한다.
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				res[0] = arr[i];
				res[1] = arr[j];
			}
			if (sum > 0) {
				j--;
			} else {
				i++;
			}
		}
		System.out.println(res[0] + " " + res[1]);
	}
}
