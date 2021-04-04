package algo0403;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 결과가 최대/최소인 것 구하기
 * 제약 사항 : 음수를 양수를 나눌 경우, 양수로 바꾼 뒤 그 몫을 음수로 바꿔 계산
 * 문제 유형 : 연산자의 개수가 최대 10이므로 DFS, 순열 이용해 가능한 연산순서를 구함(최대 10!이므로 순열 가능)
 * <풀이법 요약>
 * 1. 가능한 연산자를 완전탐색과 순열을 이용해 선택
 * 2. 나눠질 피연산자가 음수일 경우 C++14의 기준을 따르는 경우를 조심
 */

public class BOJ_S1_14888 {

	static int N, max, min;
	static int[] num, op;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		op = new int[4];
		
		for (int i = 0; i < N; i++)
			num[i] = sc.nextInt();
		for (int i = 0; i < 4; i++)
			op[i] = sc.nextInt();
		
		min = 1000000000;
		max = -1000000000;
		nPr(1, num[0], op[0], op[1], op[2], op[3]);
		
		System.out.println(max);
		System.out.println(min);
	}

	static void nPr(int depth, int sum, int p, int m, int mul, int div) {
		if (depth == N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			
			return;
		}
		
		if (p > 0)
			nPr(depth + 1, sum + num[depth], p - 1, m, mul, div);
		if (m > 0)
			nPr(depth + 1, sum - num[depth], p, m - 1, mul, div);
		if (mul > 0)
			nPr(depth + 1, sum * num[depth], p, m, mul - 1, div);
		if (div > 0) {
			if (sum < 0) 
				nPr(depth + 1, -(-sum / num[depth]), p, m, mul, div - 1);
			nPr(depth + 1, sum / num[depth], p, m, mul, div - 1);
		}
	}
}
