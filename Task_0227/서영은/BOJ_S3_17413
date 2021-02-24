import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * <문제 요약>
 * 구해야 하는 것: 문자열에서 단어만 뒤집기 
 * 제약 사항: 태그 사이에 있는 단어는 뒤집지 않는다. 
 * 알파벳 소문자('a'-'z'), 숫자('0'-'9'), 공백(' '), 특수 문자('<', '>')로만 이루어져 있다.
 * 문자열의 시작과 끝은 공백이 아니다.
 * '<'와 '>'가 문자열에 있는 경우 번갈아가면서 등장하며, '<'이 먼저 등장한다. 또, 두 문자의 개수는 같다.
 * 문제 유형: 스택 이용 
 * 요구 개념: 스택을 잘 이용하자 
 * 
 * <풀이법 요약>
 * 1. 처음에 입력 받은 문자열을 char 배열로 변환 
 * 2. "<"을 만나면 ">"를 만날 때까지 그냥 출력 
 * 3. 그 외에는, "<"과 " "을 만날때까지 단어를 스택에 담은 후에, 만나면 스택에서 pop()하며 출력한다. 
 *
 */
public class BOJ_17413_단어뒤집기2 {

	static Stack<Character> stack = new Stack<Character>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = in.readLine();
		
		char[] strArr = str.toCharArray();
		
		boolean tagCheck = false; // 태그를 체크 
		boolean ban = false; // 거꾸로 뒤집는 문자열 체크 
		for (int i = 0; i < strArr.length; i++) {
			
			if(!tagCheck && strArr[i] != ' ' && strArr[i] != '<') {
				stack.push(strArr[i]);
			} else if(!tagCheck && strArr[i] == ' ') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(strArr[i]);
			} else if(strArr[i] == '<') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(strArr[i]);
				tagCheck = true;
				continue;
			} else if(tagCheck && strArr[i] == '>') {
				sb.append(strArr[i]);
				tagCheck = false;
				continue;
			} else if(tagCheck) {
				sb.append(strArr[i]);
				continue;
			} 
		}
		
		while(!stack.isEmpty()) sb.append(stack.pop());
		
		System.out.println(sb.toString());
	}
}
