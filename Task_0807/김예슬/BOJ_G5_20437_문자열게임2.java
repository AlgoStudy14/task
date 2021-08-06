package algo0807;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 어떤 문자 K개를 포함하는 가장 짧은 연속 문자열의 길이와 문자열의 첫 번째와 마지막 글자가 같은 가장 긴 연속 문자열의 길이 출력
 * 문제 유형 : 슬라이딩 윈도우
 * <풀이 요약>
 * 1. 문자마다 문자열에서 등장한 위치를 저장
 * 2. 문자의 위치를 저장한 리스트의 크기가 K 이상일 경우
 *  2-1. 해당 리스트에서 K개마다 길이를 측정 후 min과 max를 구함 
 */

public class BOJ_G5_20437_문자열게임2 {

	static int N, K, minLen, maxLen;
	static ArrayList<Integer>[] idx = new ArrayList[26];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		int T = sc.nextInt();
		
		for (int i = 0; i < 26; i++) {
			idx[i] = new ArrayList<>(); 
		}
		
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < 26; i++) {
				idx[i].clear();
			}
			
			String str = sc.next();
			for (int i = 0; i < str.length(); i++) {
				idx[str.charAt(i)- 'a'].add(i);
			}
			
			K = sc.nextInt();
			solve();
			
			if (minLen == Integer.MAX_VALUE && maxLen == Integer.MIN_VALUE)
				System.out.println(-1);
			else
				System.out.println(minLen + " " + maxLen);
		}
	}

	static void solve() {
		minLen = Integer.MAX_VALUE;
		maxLen = Integer.MIN_VALUE;
		
		for (int i = 0; i < 26; i++) {
			if (idx[i].size() < K) continue;
			
			int start = 0;
			int end = K - 1;
			while (end < idx[i].size()) {
				minLen = Math.min(minLen, idx[i].get(end) - idx[i].get(start) + 1);
				maxLen = Math.max(maxLen, idx[i].get(end) - idx[i].get(start) + 1);
				++start;
				++end;
			}
		}
	}
}
