import java.util.Scanner;
import java.util.Stack;

/*
 * <문제 요약>
 * 단어만 뒤집기
 * tag로 감싸져있는것은 뒤집지 않음
 * tag안에 있지 않은 것은 띄워쓰기를 기준으로 단어 판별
 * 
 * <풀이법 요약>
 * 알파벳을 뒤집는 것이기 때문에 Stack사용
 * Character을 붙여나가기 때문에 StringBuilder사용
 * 주어진 String의 첫번째 문자부터 탐색
 * '<'을 만나면 차있던 stack을 다 sb로 옮기고 '>'를 만날때까지 sb에 바로 넣음\
 * 띄워쓰기를 만나면 stack을 다 sb로 옮김
 * 알파벳을 만나면 stack에 넣음
 * 전체 문자를 다 탐색하면 stack에 남아있는 문자들을 다 sb로 넘김
 */

public class Main_BOJ_17413_단어뒤집기2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int pointer = 0;
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		while (pointer < str.length()) {
			if (str.charAt(pointer) == '<') {
				while (!stack.empty())
					sb.append(stack.pop());

				while (str.charAt(pointer) != '>') {
					sb.append(str.charAt(pointer));
					pointer++;
				}
				sb.append('>');
				pointer++;
				continue;
			}

			if (str.charAt(pointer) == ' ') {
				while (!stack.empty())
					sb.append(stack.pop());
				sb.append(' ');
				pointer++;
				continue;
			}

			stack.push(str.charAt(pointer));
			pointer++;
		}

		while (!stack.empty())
			sb.append(stack.pop());

		System.out.print(sb.toString());
	}

}
