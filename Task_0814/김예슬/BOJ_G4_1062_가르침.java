package algo0814;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값을 출력
 * 문제 유형 : 완전탐색, 조합
 * <풀이 요약>
 * 1. (a n t i c) 무조건 읽을 수 있음
 *  1-1. (K < 5)인 경우 읽을 수 있는 단어는 무조건 없음
 *  1-2. (K = 26)인 경우 무조건 읽을 수 있음
 * 2. 단어 중 (K - 5)개를 조합해 읽을 수 있는 단어의 개수를 계산
 */

public class BOJ_G4_1062_가르침 {

	static int N, K, max;
	static boolean[] alpha;
	static String[] word;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		alpha = new boolean[26];
		word = new String[N];
		
		if (K < 5) System.out.println(0);
		else if (K == 26) System.out.println(N);
		else {
			sc.nextLine();
			for (int i = 0; i < N; i++) {
				String tmp = sc.nextLine();
				word[i] = tmp.substring(4, tmp.length() - 4); 
			}
			
			alpha['a' - 'a'] = alpha['n' - 'a'] =
			alpha['t' - 'a'] = alpha['i' - 'a'] = 
			alpha['c' - 'a'] = true;
			
			combination(0, 0);
			System.out.println(max);
		}
	}

	static void combination(int depth, int start) {
		if (depth == K - 5) {
			max = Math.max(max, howManyRead());
			return;
		}
		
		for (int i = start; i < 26; i++) {
			if (alpha[i]) continue;
			
			alpha[i] = true;
			combination(depth + 1, i + 1);
			alpha[i] = false;
		}
	}
	
	static int howManyRead() {
		int cnt = 0;
		
		// 단어마다 읽을 수 있는지 없는지 체크
		for (int i = 0; i < N; i++) {
			// 단어의 알파벳 하나마다 읽을 수 있는지 없는지 체크
			boolean isAvailable = true;
			
			for (int j = 0; j < word[i].length(); j++) {
				// 만약 읽을 수 없다면
				if (!alpha[word[i].charAt(j) - 'a']) {
					isAvailable = false;
					break;
				}
			}
			
			// 만약 읽을 수 있는 단어라면
			if (isAvailable) ++cnt;
		}
		
		return cnt;
	}
}
