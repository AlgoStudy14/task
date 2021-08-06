package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이와 긴 연속 문자열의 길이를 구하여라.
 * 문제 유형 : 문자열, 구현, 슬라이딩 윈도우.
 * <풀이 요약>
 * 1. 문자열을 탐색하면서 다음을 저장한다.
 * -> 해당 알파벳의 개수.
 * -> a - z의 인덱스를 각각 저장해둔다(ArrayList 활용).
 * 2. a - z의 문자에 대해서 다음을 탐색한다.
 * -> 해당 문자의 개수가 K개 이상인 경우에만 아래 과정을 진행한다.
 * -> 시작과 끝 인덱스를 지정하여 i와 i + K - 1 인덱스의 차를 구하고 최대, 최소를 갱신한다(슬라이딩 윈도우).
 * <피드백>
 * 문자열 싫엉
 * 한 번만 더 풀어보자..
 */

public class BOJ_G5_20437_문자열게임 {
	static int T, K, min, max;
	static int[] cnt;
	static ArrayList<ArrayList<Integer>> idxs;
	static String W;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			cnt = new int[26];
			idxs = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < 26; i++) {
				idxs.add(new ArrayList<Integer>());
			}

			// 저장
			for (int i = 0; i < W.length(); i++) {
				// 알파벳 개수 세기
				int alpha = W.charAt(i) - 'a';
				cnt[alpha]++;
				// 해당 알파벳의 인덱스 추가
				idxs.get(alpha).add(i);
			}

			// 탐색
			for (int i = 0; i < 26; i++) {
				if (cnt[i] < K)
					continue;
				// 슬라이딩 윈도우
				int s = 0, e = K - 1;
				while (e < cnt[i]) {
					// 차이 계산
					int diff = idxs.get(i).get(e++) - idxs.get(i).get(s++);
					// 최대, 최소 갱신.
					max = Math.max(max, diff);
					min = Math.min(min, diff);
				}
			}

			// 결과
			if (max == Integer.MIN_VALUE)
				System.out.println(-1);
			else
				System.out.printf("%d %d\n", min + 1, max + 1);
		}
	}
}
