package algo0417;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * <문제 요약>
 * 구해야 하는 것 : 괄호를 적절히 추가해 만들 수 있는 식의 결과의 최댓값 출력
 * 제약 사항: 중첩괄호 사용 불가, 괄호 안에는 연산자 하나만!
 * 문제 유형 : subset, dfs 사용
 * <풀이법 요약>
 * 0. numbers: 피연산자, op: 연산자
 * 1. 괄호 사용O, 괄호 사용X 나눠서 dfs
 *  1-1. depth는 연산자의 인덱스라 생각
 *  1-2. 괄호 O: 현재까지의 결과에 (현재 + 1) 피연산자와 (현재 + 2) 피연산자의 연산의 결과를 연산 
 *  1-3. 괄호 X: 현재까지의 결과에 바로 뒤 피연산자까지 계산
 */

public class BOJ_G3_16637_괄호추가하기 {

	static int N, max;
	static int[] numbers;
	static char[] op;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N / 2 + 1];
		op = new char[N / 2];
		
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) numbers[i / 2] = s.charAt(i) - '0';
			else op[i / 2] = s.charAt(i);
		}
		
		max = Integer.MIN_VALUE;
		subset(0, numbers[0]);
			
		System.out.println(max);
	}

	static void subset(int depth, int sum) {
		if (depth == N / 2) {
			max = Math.max(max, sum);
			return;
		}
		
		// 괄호O
		if (depth + 2 <= N / 2) {
			int tmp = calc(numbers[depth + 1], numbers[depth + 2], op[depth + 1]);
			subset(depth + 2, calc(sum, tmp, op[depth]));			
		}
		
		// 괄호X
		subset(depth + 1, calc(sum, numbers[depth + 1], op[depth]));
	}
	
	// +, -, x
	static int calc(int a, int b, char op) {
        switch (op) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        }
        return -1;		
	}
}
