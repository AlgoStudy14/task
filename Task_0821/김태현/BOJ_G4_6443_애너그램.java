package ss;

import java.util.Arrays;
import java.util.Scanner;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 애너그램을 출력하라.
 * 문제 유형 : 순열
 * <풀이 요약>
 * 1. 입력 단어를 정렬한다.
 * 2. 정렬된 단어를 순열을 이용하여 모두 출력한다.
 * <피드백>
 * 중복을 제거하는 방법이 예술임.
 * 다음 순열로도 풀어보기.
 */

public class BOJ_G4_6443_애너그램 {
	static int N, length;
	static char[] word, overlap;
	static boolean[] visited;
	static StringBuilder result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		result = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String temp = sc.nextLine();
			length = temp.length();
			word = new char[length];
			overlap = new char[length];
			visited = new boolean[length];
			for (int j = 0; j < length; j++) {
				word[j] = temp.charAt(j);
			}
			Arrays.sort(word);

			Permutation(0, new StringBuilder());
		}
		System.out.println(result);

		sc.close();
	}

	private static void Permutation(int cnt, StringBuilder s) {
		if (cnt == length) {
			result.append(s + "\n");
			return;
		}

		// 굉장히 천재적인 방법이다.. aab로 시뮬레이션 해보기.
		// 이전에 저장한 값을 기반으로, 정렬 된 상태임을 이용하여 중복 제거.
		overlap[cnt] = 0;
		for (int i = 0; i < length; i++) {
			if (visited[i])
				continue;
			if (overlap[cnt] >= word[i])
				continue;
			overlap[cnt] = word[i];

			visited[i] = true;
			s.append(word[i]);
			Permutation(cnt + 1, s);
			s.deleteCharAt(cnt);
			visited[i] = false;
		}

	}
}
