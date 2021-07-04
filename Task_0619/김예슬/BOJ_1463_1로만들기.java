package algo0611;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 정수 N을 1로 만들 때 필요한 연산의 최소 횟수를 출력
 * 문제 유형 : DP
 * 주의 사항 : 시간 제한 0.15초
 * <풀이 요약>
 * 
 */

public class BOJ_1463_1로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] D = new int[N + 1];
		
		D[1] = 0;
		for (int i = 2; i <= N; i++) {
			D[i] = D[i - 1] + 1;
			if (i % 2 == 0) D[i] = Math.min(D[i], D[i / 2] + 1);
			if (i % 3 == 0) D[i] = Math.min(D[i], D[i / 3] + 1);
		}
		
		System.out.println(D[N]);
	}

}
