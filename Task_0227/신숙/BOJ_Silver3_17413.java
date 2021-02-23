package BaekJoon;

import java.util.Scanner;

public class BOJ_Silver3_17413 {
	/*
	 * 단어 뒤집기.
	 * 1.알파벳 소문자('a'-'z'), 숫자('0'-'9'), 공백(' '), 특수 문자('<', '>')로만 이루어져 있다.
	 * 2.문자열의 시작과 끝은 공백이 아니다.
	 * 3.'<'와 '>'가 문자열에 있는 경우 번갈아가면서 등장하며, '<'이 먼저 등장한다. 또, 두 문자의 개수는 같다.
	 * 		< >가 있는 것은 단어로 치지 않아서 그냥 그대로 출력시켜야함.
	 * 
	 * 
	 * String으로 sc.nextLine()으로 받아서 문자열 1개로 처리
	 * 반복문으로 charAt을 이용해 공백이나, '<'를 만나기 전까지거꾸로 출력.
	 * '<'를 만나면 '>'가 있기 전까지는 일반 출력.
	 * 마지막 문자열일때도 역시 
	 * 
	 * 왜틀렸지? 예제는 다 나오는데... 9%까지 가고 틀리네.
	 * 괄호뒤에 공백을 두게 되면 공백이 출력이 안되네.		=> 괄호를 만나고 i를 설정해줄때의 문제.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String sr = sc.nextLine();
		boolean flag = false;
		int count = 0;
		for(int i = 0; i < sr.length(); i++) {
			flag = false;
			//앞부터 살펴보았을 때, 쭈욱 지나가는데 공백이 있으면 count~그 공백전까지 역으로 출력.
			if(sr.charAt(i) == ' ') {
				for(int j = i - 1; j >= count; j--) {
					sb.append(sr.charAt(j));
				}
				count = i + 1;
				sb.append(sr.charAt(i));
			}//앞부터 살펴보았을 때, '<'를 만나게 된다면 '>'까지 바르게 출력.
			else if(sr.charAt(i) == '<') {
				//중간에 '<'를 만나게 된것이라면 count~그 앞까지 역으로 출력해야함.
				for(int j = i - 1; j >= count; j--) {
					sb.append(sr.charAt(j));
				}
				count = i + 1;
				//문자열을 현재 있는 위치부터 돌렸을 때, '>'를 만나기 전까지 돌리고 
				for(int j = i; j < sr.length(); j++) {
					if(sr.charAt(j) == '>') {
						//'>'를만났다면 '<'부터 '>'까지 바르게 출력.
						for(int k = i; k <= j; k++) {
							sb.append(sr.charAt(k));
							flag = true;
							count = j + 1;
							i = j;
						}
					}
					if(flag)
						break;
				}
			}//문자열 끝을 만났다면, count부터 역으로 출력.
			else if(i == sr.length() - 1){
				for(int j = i; j >= count; j--) {
					sb.append(sr.charAt(j));
				}
			}
		}
		System.out.println(sb.toString());
	}
}