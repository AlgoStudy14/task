package s0703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : N가지 배수로 구성된 동전이 주어졌을때, K원을 완성할 수 있는 최소 동전의 개수?
 * 문제 유형 : 그리디.
 * <풀이 요약>
 * 1. n원은 n - 1원의 모임으로 만들 수 있기 때문에, 가장 큰 동전의 가치부터 차례차례 K원을 완성시킨다.
 */

public class BOJ_S2_11047_동전0 {
	static int N, K;
	static int[] coins;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N];
		ans = 0;
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		// 가치가 가장 큰 동전부터 카운팅.
		for(int i = coins.length - 1; i >= 0; i--) {
			// K원이 0원이 될 때 까지 동전의 개수 카운팅
			ans += K / coins[i];
			K %= coins[i];
		}
		
		System.out.println(ans);
	}
}
