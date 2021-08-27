package algo0828;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <문제 요약>
 * 문제 정의 : N개의 영단어에 대한 모든 가능한 애너그램을 출력
 * 문제 유형 : Permutation
 * <풀이 요약>
 * 단어의 길이는 20이고, 애너그램의 수가 10만개 이하인 단어만 입력으로 주어짐
 * 1. 영단어에 들어있는 알파벳의 개수를 저장
 * 2. 모든 알파벳에 대해 순열을 구함
 *  2-1. 알파벳의 개수가 0이면 패스
 *  2-2. 알파벳의 개수가 1이상이면 사용
 * <피드백>
 * 시간초과가 자꾸 남...
 * 하노이탑 문제 기준 N=20일 경우 
 * System.out.print()을 사용해 바로바로 출력하는 것보다
 * StringBuilder를 사용해 모아서 출력하면
 * 시간이 약 10분의 1로 줄어듦
 * 생각보다 훨씬 시간이 줄어든다!
 */

public class BOJ_G4_6443_애너그램 {

	static int N, L;
	static int[] cnt;
	static char[] c;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int n = 0; n < N; n++) {
			String s = br.readLine();
			L = s.length();
			
			cnt = new int[26];
			c = new char[L];
			
			for (int i = 0; i < L; i++) {
				++cnt[s.charAt(i) - 'a'];
			}
			
			make(0);
		}
		
		System.out.println(sb);
	}

	static void make(int depth) {
		if (depth == L) {
			// 문자 배열은 바로 append 가능
			sb.append(c).append("\n");
			return;
		}
		
		for (int i = 0; i < 26; i++) {
			if (cnt[i] == 0) continue;
			
			--cnt[i];
			c[depth] = (char) (i + 'a');
			make(depth + 1);
			++cnt[i];
		}
	}
}
