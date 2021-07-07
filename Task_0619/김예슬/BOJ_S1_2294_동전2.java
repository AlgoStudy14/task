package algo0705;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 합이 k원이 되도록 하는 동전의 최소 개수를 출력
 * 문제 유형 : DP
 * 주의 사항 : 시간 제한 1초
 * <풀이 요약>
 * 1부터 K금액까지 사용할 수 있는 동전의 수를 누적  
 */

public class BOJ_S1_2294_동전2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] coins = new int[N];
		int[] D = new int[K + 1];
		
		for (int n = 0; n < N; n++)
			coins[n] = sc.nextInt();
		
		Arrays.fill(D, 10002);
		D[0] = 0;
		
		for (int i = 1; i <= K; i++) {
			for (int n = 0; n < N; n++) {
				int coin = coins[n];
				if (i - coin >= 0) {
					// i금액에 현재 동전을 사용할 수 있으면
					if (i == coin) D[i] = 1;
					// i금액에 현재 동전을 사용할 수 없으면 
					else {
						if (i - coin >= 0) {
							D[i] = Math.min(D[i], D[i - coin] + 1);
						}
					}
				}
			}
		}
		
		if (D[K] == 10002) D[K] = -1;
		System.out.println(D[K]);
	}

}
