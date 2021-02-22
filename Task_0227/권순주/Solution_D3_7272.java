import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 두 문자열이 같은지 판단
 * 문제 핵심 요약 : 미리 배열에 저장해놓고 index마다 체크
 * <풀이법 요약> 
 * 1. 미리 배열에 구멍없음 : 0 / 구멍 하나 있음 : 1 / 구멍 두개 있음 : 2 를 저장
 * 2. 단어의 길이만큼 반복하면서 조건 체크
 * <조건>
 * (1). 두 단어의 길이가 다르다면 false
 * (2). 같은 index에 해당하는 두 단어가 다르다면 false
 * (3). else true
 */

public class Solution_D3_7272 {

	// 미리 배열에 구멍없음 : 0 / 구멍 하나 있음 : 1 / 구멍 두개 있음 : 2 를 저장
	static int[] alphabet = { 1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();
			System.out.printf("#%d %s\n", t, check(A, B) ? "SAME" : "DIFF");
		}
	}

	private static boolean check(String A, String B) {
		if (A.length() != B.length()) // 만약 길이가 같지 않다면 false return 
			return false;

		int N = A.length();
		for (int i = 0; i < N; i++) {
			if (alphabet[A.charAt(i) - 'A'] != alphabet[B.charAt(i) - 'A']) { // 같은 index의 문자를 비교해서 다르다면 
				return false; // false return
			}
		}
		return true;
	}
}
