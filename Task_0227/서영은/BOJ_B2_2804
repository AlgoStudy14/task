import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <문제 요약>
 * 구해야 하는 것: 크로스워드 출력 
 * 제약 사항: 동시에 포함되어 있는 글자를 공유해야 함. 여러개 등장하는 경우, 먼저 등장하는 글자를 선택한다. 
 * 문제 유형: 구현?
 * 요구 개념: 구현? -> 알파벳 빈도를 체크하는 배열을 만들어서 해결  
 * 
 * <풀이법 요약>
 * 1. 문자열을 입력 받고, 등장하는 알파벳 빈도를 체크한다. 
 * 2. 인덱스를 체크하여 출력한다. 
 */
public class BOJ_B2_2804 {
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		String A = scann.next();
		String B = scann.next();
		
		int N = A.length();
		int M = B.length();
		
		int[] Acheck = new int[26];
		int[] Bcheck = new int[26];
		
		for (int i = 0; i < A.length(); i++) {
			Acheck[A.charAt(i)-'A']++;
		}
		for (int i = 0; i < B.length(); i++) {
			Bcheck[B.charAt(i)-'A']++;
		}
		
		// 겹치는 인덱스를 담는 배열 
		ArrayList<Integer> checkIndex = new ArrayList<Integer>();
		for (int i = 0; i < 26; i++) {
			if(Acheck[i] > 0 && Bcheck[i] > 0) {
				checkIndex.add(i);	// 어느 알파벳인지 담는 것! 
			}
		}
		
		// aIndex 체크 
		int Aindex = Integer.MAX_VALUE;
		for (int i = 0; i < checkIndex.size(); i++) {
			for (int j = 0; j < A.length(); j++) {
				if((char)(checkIndex.get(i)+'A') == A.charAt(j)) {
					Aindex = Math.min(Aindex, j);
				}
			}
		}

		char dup = A.charAt(Aindex);

		int Bindex = Integer.MAX_VALUE;
		for (int i = 0; i < B.length(); i++) {
			if(dup == B.charAt(i)) {
				Bindex = i;
				break;
			}
		}
		
		// 출력
		char map[][] = new char[M][N];
		
		for (int i = 0; i < M; i++) {
			Arrays.fill(map[i], '.');
		}
		
		
		for (int i = 0; i < N; i++) {
			map[Bindex][i] = A.charAt(i);
		}
		
		for (int i = 0; i < M; i++) {
			map[i][Aindex] = B.charAt(i);
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

}
