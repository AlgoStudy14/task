package algo210410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약> 
 * 구해야 하는 것: N개의 수와 N-1개의 연산자가 주어졌을 때, 결과가 최대인 것과 최소인 것을 구하는 프로그램
 * 제약 사항: 항상 값은 -10억보다 크거나 같고, 10억보다 작거나 같은 결과만 나오는 입력만 주어진다.
 * 문제 유형: 완탐, 백트래킹
 * 요구 개념: 완탐, 백트래킹
 * 
 * <풀이법 요약> 
 * 1. 그냥 전체적으로 탐색 해보기 
 * => N-1개의 연산자를 썼을 때, 연산은 끝난다.
 */
public class BOJ_Silver1_연산자끼워넣기 {

	static int N, max, min;
	static int[] num;
	static int[] oper;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		num = new int[N];
		oper = new int[4];
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		dfs(1, 0, num[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	private static void dfs(int numIdx, int operCnt, int calc) {
		if(operCnt == N-1) {
			max = Math.max(max, calc);
			min = Math.min(min, calc);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(oper[i] == 0) continue;
			
			--oper[i];
			if(i==0) dfs(numIdx+1, operCnt+1, calc + num[numIdx]);
			else if(i==1) dfs(numIdx+1, operCnt+1, calc - num[numIdx]);
			else if(i==2) dfs(numIdx+1, operCnt+1, calc * num[numIdx]);
			else if(i==3) dfs(numIdx+1, operCnt+1, calc / num[numIdx]);
			++oper[i];
		}
		
	}

}
