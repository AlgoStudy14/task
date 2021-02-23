package study0227;

/***
 * <문제 요약>
 * 구해야 하는 것 : 주어진 문자열을 경근이가 어떻게 판단하는가(같은가? 다른가?).
 * 문제 유형 : 구현
 * 요구 개념 : contains.
 * <풀이법 요약>
 * 1. 구멍이 없는 문자, 1개 있는 문자, 2개 있는 문자를 분류한다.
 * 2. 입력 받은 문자를 각각  분류된 문자열중 어디에 포함되는지(contains) 판단한다.
 * 3. 모두 같은 곳에 포함되면 SAME, 하나라도 다르면 DIFF.
 */

import java.util.Scanner;

public class Solution_D3_7272 {
	static int T;
	// 구멍이 없거나, 하나 있거나, 두 개 있는 경우 분류(인덱스 번호로).
	static String[] hole = { "CEFGHIJKLMNSTUVWXYZ", "ADOPQR", "B" };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = Integer.parseInt(sc.nextLine());
		String a;
		String b;
		for (int t = 1; t <= T; t++) {
			a = sc.next();
			b = sc.next();

			boolean flag = true;
			for (int i = 0; i < a.length(); i++) {
				// 두 문자열의 길이가 다르면 바로 다르다고 판단.
				if (a.length() != b.length()) {
					flag = false;
					break;
				}

				// 각각 어떤 분류에 속하는지 판단하기 위한 로직.
				int kind_a = 0;
				int kind_b = 0;
				// 3가지 분류를 돌면서
				for (int j = 0; j < hole.length; j++) {
					// a, b 각 문자의 분류를 정한다.
					if (hole[j].contains(a.substring(i, i + 1))) {
						kind_a = j;
					}
					if (hole[j].contains(b.substring(i, i + 1))) {
						kind_b = j;
					}
				}
				// 분류가 다르다면, DIFF
				if (kind_a != kind_b) {
					flag = false;
					break;
				}
			}
			// 분류가 모두 같다면, flag == true일 것이므로 SAME.
			if (flag) {
				System.out.printf("#%d SAME\n", t);
			} else {
				System.out.printf("#%d DIFF\n", t);
			}
		}
		sc.close();
	}
}
