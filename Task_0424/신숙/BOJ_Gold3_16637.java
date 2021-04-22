import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_Gold3_16637 {
	/*
	 * 시간제한 0.5초...
	 * <괄호 추가하기>
	 * 길이가 N인 수식. 결과값 2^31보다 작고, -2^31보단 크다.
	 * 수식의 수는 모두 한 자리수. +, -, *, / 4가지 연산종류.	
	 * 		=> 0으로 나누게될 예외 생각하고있었는데 나누기 없음.
	 * 
	 * 수식이 주어질때 괄호를 추가할 수 있음. 중첩괄호는 허용 안됨. 괄호들을 사용하여 얻을 수 있는 최댓값.
	 * 	=> 중첩괄호가 안되면 괄호를 하고 넘길때와 안하고 넘길때, 2가지로.
	 * 연산자를 관리하는 리스트와, 수를 관리하는 리스트 2가지 사용.
	 * 
	 * 에러라.... java.lang.IndexOutOfBoundsException Index: 4, Size: 4
	 */
	static int N, ans, count;
	static String expr;
	static ArrayList<Character> op;
	static ArrayList<Integer> num;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();	//nextInt 같은거 뒤에 남아있는 널문자 빼주는 역할이었던가?
		expr = sc.nextLine();
		//초기화들
		ans = Integer.MIN_VALUE;
		op = new ArrayList<>();
		num = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0)
				num.add(expr.charAt(i) - '0');
			else
				op.add(expr.charAt(i));
		}
		count = op.size();		//계산은 연산자 수만큼 하니까 연산자 길이
		dfs(0, num.get(0));
		System.out.println(ans);
	}
	static void dfs(int cnt, int sum) {
		if(cnt == count) {
			ans = Math.max(ans, sum);
			return;
		}
		//괄호를 하지 않고 넘길 때
		dfs(cnt + 1, calc(sum, op.get(cnt), num.get(cnt + 1)));
		//괄호를 해주고 넘길 때		= > 다음 것을 계산하고 넘길때.
		if(cnt < op.size() - 1) {
			int next = calc(num.get(cnt + 1), op.get(cnt + 1), num.get(cnt + 2));	//인덱스 에러 ?		=> 마지막 연산일때는 다음꺼가 없네
			dfs(cnt + 2, calc(sum, op.get(cnt), next));
		}
	}
	static int calc(int a, char operation, int b) {
		if(operation == '+')
			return a + b;
		else if(operation == '-')
			return a - b;
		else
			return a * b;
//		else if(operation == '*')
//			return a * b;
//		else{
//			if(b != 0)
//				return a / b;
//			else
//				return ..?   		//넘겨야하는디...
	}
}
