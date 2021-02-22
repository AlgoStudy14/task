package BaekJoon;

import java.util.Scanner;

public class BOJ_Bronze2_2804 {
	/*
	 * 문자열 두개를 입력 받아서 처음 겹치는 곳이 크로스 되게, 2차원 배열을 출력하는 문제.
	 * 
	 * 문자열을 비교하고, 처음 겹치는 곳 수를 저장하고, 이 수를 이용해서 배열에 저장.
	 */
	static char[][] board;
	static String A, B;
	static boolean flag;
	static int Al, Bl;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.next();
		B = sc.next();
		board = new char[B.length()][A.length()];
		// .으로 배열 초기화
		for(int i = 0; i < B.length(); i++ ) {
			for(int j = 0; j < A.length(); j++) {
				board[i][j] = '.';
			}
		}
		// A문자열의 몇번째에서 겹치고, B문자열의 몇번째에서 겹치는지 확인.
		// BANANA  => 1, PIDZAMA = > 4
		for(int i = 0; i < A.length(); i++) {
			for(int j = 0; j < B.length(); j++) {
				if(A.charAt(i) == B.charAt(j)) {
					Al = i; Bl = j;
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}
		//문자열 한줄씩 넣기.
		for(int i = 0; i < A.length(); i++) {
			board[Bl][i] = A.charAt(i);
		}
		for(int i = 0; i < B.length(); i++) {
			board[i][Al] = B.charAt(i);
		}
		//출력
		for(int i = 0; i < B.length(); i++ ) {
			for(int j = 0; j < A.length(); j++) {
				System.out.print(board[i][j]);
			}System.out.println();
		}
	}
}
