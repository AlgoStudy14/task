import java.util.ArrayList;
import java.util.Scanner;

/*
 * <문제 요약>
 * ADOPQR은 같은글자인식.
 * B를 제외한 나머지는 같은글자인식
 * B만 제대로 인식할 때 두 문자열이 같은지 비교
 * 
 * <풀이법 요약>
 * 두 문자열의 길이가 다르면 false
 * 두 문자열의 첫글자부터 비교시작
 * 
 * 1. B는 제대로 판별하므로 B인경우 확인
 * 2. B가 아닌경우 ADOPQR안에 포함되었는지 확인 (포함되는지 확인하기 쉽게하려고 ArrayList사용)
 * 
 */

public class Solution_d3_7272_안경이없어 {
	static int T;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = Integer.parseInt(sc.nextLine());
		ArrayList<Character> arrList = new ArrayList<>();
		arrList.add('A');
		arrList.add('D');
		arrList.add('O');
		arrList.add('P');
		arrList.add('Q');
		arrList.add('R');

		for (int t = 1; t <= T; t++) {
			String[] strArr = sc.nextLine().split(" ");
			String str1 = strArr[0];
			String str2 = strArr[1];

			boolean check = true;

			if (str1.length() != str2.length()) {
				System.out.println("#" + t + " DIFF");
				continue;
			}
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) == 'B') {
					if (str2.charAt(i) == 'B')
						continue;
					else {
						check = false;
						break;
					}
				}

				if (arrList.contains(str1.charAt(i))) {
					if (arrList.contains(str2.charAt(i)))
						continue;
					else {
						check = false;
						break;
					}
				}
			}

			if (check == false)
				System.out.println("#" + t + " DIFF");
			else
				System.out.println("#" + t + " SAME");
		}
	}

}
