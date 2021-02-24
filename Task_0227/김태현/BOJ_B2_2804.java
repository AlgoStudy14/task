package study0227;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 두 단어가 교차되는 모양 출력(한 글자 공유, 공유 글자 중 첫번 쨰). 
 * 문제 유형 : 구현
 * 요구 개념 : 2차원 배열, String
 * <풀이법 요약>
 * 1. 첫 단어는 열의 개수, 두 번째 단어는 행의 개수로 맵을 초기화 한다.
 * 2. A단어에 대하여 B단어에 순서대로 탐색하면서 겹치는 글자를 찾는다(찾으면 바로 반복 종료).
 * 3. A인덱스는 B단어의 열, B인덱스는 A단어의 행에 배치하여 글자를 세팅한다.
 * 4. 전체를 출력한다(빈 곳은 .으로 출력한다).
 */

public class BOJ_B2_2804 {
	static String A, B;
	static char[][] cross;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.next();
		B = sc.next();
		// 1. 맵 초기화
		cross = new char[B.length()][A.length()];
		// 2. 겹치는 글자 탐색
		int A_idx = 0;
		int B_idx = 0;
		for (int i = 0; i < A.length(); i++) {
			boolean flag = false;
			for (int j = 0; j < B.length(); j++) {
				if (A.charAt(i) == B.charAt(j)) {
					A_idx = i;
					B_idx = j;
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}

		// 글자 배치
		for (int i = 0; i < A.length(); i++) {
			cross[B_idx][i] = A.charAt(i);
		}
		for (int i = 0; i < B.length(); i++) {
			cross[i][A_idx] = B.charAt(i);
		}

		// 출력
		for (int i = 0; i < B.length(); i++) {
			for (int j = 0; j < A.length(); j++) {
				if (cross[i][j] == 0) {
					System.out.print(".");
					continue;
				}
				System.out.print(cross[i][j]);
			}
			System.out.println();
		}

		sc.close();
	}
}
