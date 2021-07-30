package algo0731;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * <문제 요약>
 * 문제 정의 : T 중간에 P가 몇 번 나타나는지, P가 나타나는 위치를 공백으로 구분해 출력
 * 문제 유형 : KMP
 */

public class BOJ_G1_1786_찾기 {

	static int N, M, cnt;
	static char[] t, p;
	static int[] pi;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = br.readLine().toCharArray();
		p = br.readLine().toCharArray();
		pi = new int[p.length];
		list = new ArrayList<>();
		
		makeFailFunction();
		kmp();
		
		System.out.println(cnt);
		for (int i : list)
			System.out.println(i);
	}

	static void makeFailFunction() {
		for (int i = 1, j = 0; i < p.length; i++) {
			while (j > 0 && p[i] != p[j])
				j = pi[j - 1];
			
			if (p[i] == p[j]) pi[i] = ++j;
		}
	}
	
	static void kmp() {
		for (int i = 0, j = 0; i < t.length; i++) {
			while (j > 0 && t[i] != p[j]) {
				j = pi[j - 1];
			}
			
			if (t[i] == p[j]) {
				if (j == p.length - 1) {
					++cnt;
					list.add((i + 1) - p.length + 1);
					j = pi[j];
				} else ++j;
			}
		}
	}
}
