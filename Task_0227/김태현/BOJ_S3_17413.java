package study0227;

import java.util.Scanner;
import java.util.Stack;

/***
 * <문제 요약>
 * 구해야 하는 것 : 태그는 뒤집지 않고, 단어는 뒤집어서 출력하기.
 * 문제 유형 : 구현.
 * 요구 개념 : 문자열, 스택, 정교한 인덱스 처리.
 * <풀이법 요약>
 * 1. 첫 글자를 기준으로 다르게 출력한다. 
 * -> 첫 글자가 '<', 즉 태그라면 '>'가 나올때까지 그냥 출력한다.
 * -> 첫 글자가 '<'이 아니라면, 공백 혹은 '<'이 나오거나, 문장의 끝에 도달할 때 까지 스택에 넣고, 만나면 스택이 빌때까지 출력한다.
 */

public class BOJ_S3_17413 {
	static Stack<Character> s = new Stack<Character>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();

		int idx = 0;
		while (idx < line.length()) {
			// 첫 글자가 '<'라면 '>'가 나올때까지 출력한다.
			if (line.charAt(idx) == '<') {
				while(line.charAt(idx) != '>') {
					System.out.print(line.charAt(idx++));
				}
				// '>' 닫아주기.
				System.out.print(line.charAt(idx++));
			}
			// '<'가 아니라면, 뒤집기 수행.
			else {
				// 인덱스를 벗어나거나, 공백을 만나거나, '<'를 만날때까지 스택에 넣기.
				while(idx < line.length() && line.charAt(idx) != ' ' && line.charAt(idx) != '<') {
					s.push(line.charAt(idx++));
				}

				// 만나면 스택에서 다 빼기
				while(!s.isEmpty()) {
					System.out.print(s.pop());
				}
				if(idx < line.length() && line.charAt(idx) == ' ') {
					System.out.print(line.charAt(idx++));
				} 
			}
		}
		sc.close();
	}
}
