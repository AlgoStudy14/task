import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * <문제 유형>
 * 문제 정의 : 문자열 매칭 구하기
 * 문제 유형 : kmp
 * 
 */

public class BOJ_G1_1786_찾기 {

	static String T, P;
	static ArrayList<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = br.readLine();
		P = br.readLine();
		kmp(T, P);
		System.out.println(answer.size());
		for (Integer index : answer) {
			System.out.print(index + " ");
		}
		System.out.println();
	}

	// a b a c a a b a
	// 0 0 1 0 1 1 2 3
	
	// i : a b a b a c a b a c a a b a c a a b a
	// j : a b a c a a b a 
	//     a b a c a a b a 
	//         a b a c a a b a
	//                 a b a c a a b a
 	private static void kmp(String parent, String pattern) {
		// 최대 일치길이 테이블 -> 얼마나 많이 접두사와 접미사가 일치하는지
		int[] table = makeTable(pattern);
		char[] parents = parent.toCharArray();
		char[] patterns = pattern.toCharArray();
		int j = 0;
		for (int i = 0; i < parents.length; i++) {
			// 일치하지 않는 경우가 발생했을 때 -> 이전단계의 위치로 이동
			while (j > 0 && parents[i] != patterns[j]) {
				j = table[j - 1];
			}
			if (parents[i] == patterns[j]) {
				// 모든 문자가 일치한 경우
				if (j == patterns.length - 1) {
					answer.add(i - patterns.length + 2);
					// j가 가리키는 위치만큼 점프
					j = table[j];
				} else {
					// 계속해서 매칭이 이루어지는지 확인을 위해 증가
					j++;
				}
			}
		}

	}

	
	// 접두사와 접미사가 일치하는 최대 길이
	// 0. j=0, i=1 start
	// 1. i와 j가 일치하지 않는 경우 -> i+1
	// 2. i와 j가 일치하는 경우 -> j+1, i+1 -> 일치하는 j의 index+1을 table에 저장
	// 2-1. 이동했는데 일치하지 않는 경우 -> j를 한칸 이전으로 이동시킨다
	// a b a c a a b a b
	// 0 0 1 0 1 1 2 3 2
	private static int[] makeTable(String pattern) {
		char[] patterns = pattern.toCharArray();
		int[] table = new int[patterns.length];
		// 0. j=0, i=1 start
		int j = 0;
		// 1. i와 j가 일치하지 않는 경우 -> i+1
		for (int i = 1; i < pattern.length(); i++) {
			// 2-1. 이동했는데 일치하지 않는 경우 -> j를 한칸 이전으로 이동시킨다 -> 일치하는 접두사 찾기
			while (j > 0 && patterns[i] != patterns[j]) {
				j = table[j - 1];
			}
			// 2. i와 j가 일치하는 경우 -> j+1, i+1 -> 일치하는 j의 index+1을 table에 저장
			if (patterns[i] == patterns[j]) {
				table[i] = ++j;
			}
		}
		return table;
	}

}
