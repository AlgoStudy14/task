package study0227;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 규칙에 따라 진행했을때 공을 던지는 횟수.
 * 문제 유형 : 구현.
 * 요구 개념 : 인덱스 다루기, 나눗셈.
 * <풀이법 요약>
 * 1. N 크기의 카운팅 배열을 만든다.
 * 2. 공을 받은 횟수(홀짝)에 따라서 다음 카운팅 인덱스를 증가 시키거나 감소 시킨다.
 * -> 홀수면 증가(시계 방향), 짝수이면 감소(반시계 방향).
 * -> 이때, 배열의 범위를 넘어서는 경우 인덱싱을 잘 처리해준다.
 * -> 한 번 던질때마다 총 카운팅 횟수를 늘린다.
 * 3. 카운팅이 되는 순간 종료하고 결과 출력.
 */

public class BOJ_B2_1592 {
	static int N, M, L;
	// 인덱스
	static int idx;
	// 카운팅 배열
	static int[] counts;
	// 전체 카운팅 횟수
	static int tot;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		L = sc.nextInt();

		// 카운팅 배열과 인덱스 0으로 초기화.
		counts = new int[N];
		idx = 0;
		// 첫 사람도 공을 받는다.
		counts[idx]++;

		while (counts[idx] != M) {
			// 짝수이면 감소
			if (counts[idx] % 2 == 0) {
				idx -= L;
				if (idx < 0) {
					idx = N + idx;
				}
			}
			// 홀수이면 증가
			else {
				idx += L;
				if (idx >= N) {
					idx = idx - N;
				}
			}
			counts[idx]++;
			tot++;
		}

		System.out.println(tot);

		sc.close();
	}
}
