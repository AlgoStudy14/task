package study0227;

import java.util.Scanner;
import java.util.Stack;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 인접한 인덱스의 값이 같은 경우 그 값을 제외한 최종 비밀번호. 
 * 문제 유형 : 구현. 
 * 요구 개념 : 스택
 * <풀이법 요약> 
 * 1. 비밀번호를 차례로 스택에 넣는다. 
 * 2 - 1. 스택의 top과 들어오는 값이 같은 경우, pop() 메서드를 호출한다. 
 * 2 - 2. 스택의 top과 들어오는 값이 다른 경우, 값을 넣는다. 
 * 3. 최종적으로 스택에 저장된 값의 순서가 비밀번호이다.
 */
public class Solution_D3_1234 {
	// 문자의 총 수.
	static int N;
	// 입력된 문자를 넣을 스택
	static Stack<Integer> pass;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			N = sc.nextInt();
			String line = sc.next();

			pass = new Stack<Integer>();
			int temp;
			// 비밀번호를 차례로 스택에 넣는다.
			for (int i = 0; i < N; i++) {
				temp = line.charAt(i) - '0';
				// 만일 스택이 차있을때, top과 비교하여 같은 값을 가지면 pop을 한 번 진행한다.
				if (!pass.isEmpty() && pass.peek() == temp) {
					pass.pop();
				}
				// 비어있지 않다면, 비밀번호를 그냥 스택에 넣는다.
				else {
					pass.push(temp);
				}
			}
			// 최종 결과 출력
			System.out.printf("#%d ", t);
			for (Integer num : pass) {
				System.out.print(num);
			}
			System.out.println();
		}
		sc.close();
	}
}
