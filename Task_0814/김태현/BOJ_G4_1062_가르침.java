package ss;

import java.util.Scanner;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : K개의 글자를 가르칠 수 있을 때, 학생들이 읽을 수 있는 최대 단어의 개수.
 * 문제 유형 : 시뮬레이션, 구현, 완전 탐색.
 * <풀이 요약>
 * 1. 5개의 알파벳을 선택하는 모든 경우의 수를 구한다.
 * -> 만일 K가 4이하라면 무조건 0을 출력하고 종료한다.
 * 2. 각 경우마다 아래 시뮬레이션을 진행한다.
 * -> 제거한 단어의 첫 인덱스부터 탐색하면서 선택된 알파벳에 포함되지 않는 문자가 나오는 경우 스킵. 다 통과하는 경우 카운트 + 1.
 * <피드백>
 * 어떤 대상을 비교할 때, 일일히 비교하지 않고 카운팅배열과 같이 해당 대상이 존재하는지 여부로 판단해도 될듯!
 * 여러모로 다시 풀어볼만하다.
 */

public class BOJ_G4_1062_가르침 {
	static int N, K, max;
	static boolean[] letters;
	static String[] words;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		sc.nextLine();

		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = sc.nextLine();
		}
		max = Integer.MIN_VALUE;

		// K가 5보다 작은 경우에는 0을 출력하고 종료(최소 문자 개수 충족 불가).
		if (K < 5) {
			System.out.println(0);
			sc.close();
			return;
		}
		letters = new boolean[26];

		// 알파벳을 K개 선택하는 모든 경우를 구한다.
		Combination(0, 0);
		System.out.println(max);

		sc.close();
	}

	private static void Combination(int cnt, int idx) {
		// K개를 선택했으면 시뮬레이션 진행.
		if (cnt == K) {
			max = Math.max(max, check());
			return;
		}
		// 조합
		for (int i = idx; i < 26; i++) {
			letters[i] = true;
			Combination(cnt + 1, i + 1);
			letters[i] = false;
		}
	}

	private static int check() {
		// N개의 문자를 모두 검사한다.
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			String word = words[i];
			int length = word.length();
			boolean know = true;
			for (int j = 0; j < length; j++) {
				if (!letters[(word.charAt(j) - 'a')]) {
					know = false;
					break;
				}
			}
			if (know) {
				cnt++;
			}
		}
		return cnt;
	}
}
