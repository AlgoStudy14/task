import java.util.HashMap;
import java.util.Scanner;
/**
 * <문제 요약>
 * 구해야 하는 것: 입력받은 두 문자가 같은 문자인지 판별 
 * 제약 사항: 같은 문자로 보이는 알파벳들에 대한 제약 
 * 문제 유형: 구현
 * 요구 개념: HashMap
 * 
 * <풀이법 요약>
 * 1. HashMap에 구멍이 하나 뚫린 알파벳을 키값으로 넣고, value에 1, 두개는 2, 0개는 0으로 셋팅한다.
 * 2. 문자열을 입력받아 순회하면서 같은 문자열로 보이는지 새로운 문자열을 만들어 판별한다.
 *
 */
public class Solution_D3_7272_안경이없어 {

	static HashMap<Character, Integer> map = new HashMap<>();
	static String answer = "";

	public static void main(String[] args) {

		Scanner scann = new Scanner(System.in);

		// 해시맵 세팅
		for (int i = 0; i < 26; i++) {
			if ((char) (i + 'A') == 'B')
				map.put((char) (i + 'A'), 2);
			else if ((char) (i + 'A') == 'A' || (char) (i + 'A') == 'D' || (char) (i + 'A') == 'O'
					|| (char) (i + 'A') == 'P' || (char) (i + 'A') == 'Q' || (char) (i + 'A') == 'R')
				map.put((char) (i + 'A'), 1);
			else
				map.put((char) (i + 'A'), 0);
		}
		
		int T = scann.nextInt();

		for (int t = 1; t <= T; t++) {
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			
			String str1 = scann.next();
			String str2 = scann.next();
			
			// 새로운 문자열 만들기 
			for (int i = 0; i < str1.length(); i++) {
				sb1.append(map.get(str1.charAt(i)));
			}
			for (int i = 0; i < str2.length(); i++) {
				sb2.append(map.get(str2.charAt(i)));
			}
			
			// 두 문자열 비교
			if(sb1.toString().equals(sb2.toString())) {
				answer = "SAME";
			} else {
				answer = "DIFF";
			}

			System.out.println("#" + t + " " + answer);
		}
	}
}
