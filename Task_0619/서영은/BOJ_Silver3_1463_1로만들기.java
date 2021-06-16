package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <문제 요약> 구해야 하는 것: 연산을 사용하는 횟수의 최솟값 
 * 제약 사항: 1<=N<=10^6 
 * 문제 유형: DP 
 * 요구 개념: DP
 * 
 * <풀이법 요약>
 * 
 */
public class BOJ_Silver3_1463_1로만들기 {

	static int N, D[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		
		D = new int[N+1];
		
		D[1] = 0;
		for (int i = 2; i <= N; i++) {
			D[i] = D[i-1] + 1;
			if(i%3==0) D[i] = Math.min(D[i/3] + 1, D[i]);
			if(i%2==0) D[i] = Math.min(D[i/2] + 1, D[i]);
		}
		
		System.out.println(D[N]);
	}
}
