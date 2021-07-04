package algo0611;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 합이 k원이 되도록 하는 경우의 수를 출력
 * 문제 유형 : DP
 * 주의 사항 : 시간 제한 0.5초
 * <풀이 요약>
 * N개의 동전마다(coins[i]) coins[i]부터 K까지 만들 수 있는 경우의 수 누적  
 */

public class BOJ_2293_동전1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] coins = new int[N];
		int[] D = new int[K + 1];
		
		for (int i = 0; i < N; i++)
			coins[i] = sc.nextInt();
		
		D[0] = 1;
		for (int i = 0; i < N; i++) {
			for (int k = coins[i]; k <= K; k++) {
				int coin = coins[i];
				D[k] += D[k - coin]; 
			}
		}
		
		System.out.println(D[K]);
	}

}
