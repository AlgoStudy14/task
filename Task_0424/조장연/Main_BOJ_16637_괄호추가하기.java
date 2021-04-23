import java.util.Scanner;

/*
 * <문제 요약>
 * 수식이 주어졌을 때 적절히 괄호를 사용하여 최대수 구하기
 * 조건 : 1. 괄호안에 괄호사용 불가
 * 		2. 연산자의 우선순위 없음
 * 		3. 괄호안에 1개의 연산자만 있어야 한다.
 * 
 * <풀이법 요약>
 * 주어진 조건에 따라 계산을 하면서 경우의 수를 따져나가야 한다(조건 2)
 * 괄호를 사용하는 경우와 사용하지 않는 경우 2가지로 나누어 수식을 앞에서부터 읽어나간다.(조건1, 조건3 때문에 가능)
 */

public class Main_BOJ_16637_괄호추가하기 {
	static int N;
	static String expression;
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		expression = sc.nextLine();
		answer = Integer.MIN_VALUE;

		func((int) (expression.charAt(0) - '0'), 1);
		System.out.println(answer);
	}

	private static void func(int number, int pointer) {
		if (pointer >= N) {
			answer = Math.max(answer, number);
			return;
		}

		if (expression.charAt(pointer) == '+') {
			int num = number + (int) (expression.charAt(pointer + 1) - '0'); //괄호 사용 X
			func(num, pointer + 2);

			if (pointer < N - 2) { // 괄호 사용 O
				int num2;
				if (expression.charAt(pointer + 2) == '+') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') + (int) (expression.charAt(pointer + 3) - '0');
					func(number + num2, pointer + 4);
				} else if (expression.charAt(pointer + 2) == '-') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') - (int) (expression.charAt(pointer + 3) - '0');
					func(number + num2, pointer + 4);
				} else if (expression.charAt(pointer + 2) == '*') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') * (int) (expression.charAt(pointer + 3) - '0');
					func(number + num2, pointer + 4);
				}
			}
		} else if (expression.charAt(pointer) == '-') {
			int num = number - (int) (expression.charAt(pointer + 1) - '0');
			func(num, pointer + 2);

			if (pointer < N - 2) {
				int num2;
				if (expression.charAt(pointer + 2) == '+') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') + (int) (expression.charAt(pointer + 3) - '0');
					func(number - num2, pointer + 4);
				} else if (expression.charAt(pointer + 2) == '-') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') - (int) (expression.charAt(pointer + 3) - '0');
					func(number - num2, pointer + 4);
				} else if (expression.charAt(pointer + 2) == '*') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') * (int) (expression.charAt(pointer + 3) - '0');
					func(number - num2, pointer + 4);
				}
			}
		} else if (expression.charAt(pointer) == '*') {
			int num = number * (int) (expression.charAt(pointer + 1) - '0');
			func(num, pointer + 2);

			if (pointer < N - 2) {
				int num2;
				if (expression.charAt(pointer + 2) == '+') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') + (int) (expression.charAt(pointer + 3) - '0');
					func(number * num2, pointer + 4);
				} else if (expression.charAt(pointer + 2) == '-') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') - (int) (expression.charAt(pointer + 3) - '0');
					func(number * num2, pointer + 4);
				} else if (expression.charAt(pointer + 2) == '*') {
					num2 = (int) (expression.charAt(pointer + 1) - '0') * (int) (expression.charAt(pointer + 3) - '0');
					func(number * num2, pointer + 4);
				}
			}
		}
	}

}
