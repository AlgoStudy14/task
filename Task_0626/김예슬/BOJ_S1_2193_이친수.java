package algo0708;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : n자리 이친수의 개수를 출력
 * 문제 유형 : DP
 * 주의 사항 : 변수 타입 주의
 * <풀이 요약>
 * 피보나치와 유사
 */

public class BOJ_S1_2193_이친수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[] D = new long[N + 1];
		
		D[0] = 0;
		D[1] = 1;
		for (int i = 2; i <= N; i++) {
			D[i] = D[i - 1] + D[i - 2];
		}
		
		System.out.println(D[N]);
	}

}