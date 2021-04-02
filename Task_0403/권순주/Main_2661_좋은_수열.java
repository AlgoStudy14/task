import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2661_좋은_수열 {

	static int N;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		back(sb);
	}

	private static void back(StringBuilder sb) {
		if (flag)
			return;
		if (sb.length() == N) { // N번 돌았다면
			flag = true; // flag 바꿔주고
			System.out.println(sb); // print -> 가장 작은 값을 찾는 것이기 때문에 그냥 끝내줌!
			return;
		}
		for (int i = 1; i < 4; i++) {
			if (check(sb.append(i))) { // 인접한 두 개의 부분 수열이 동일한 것이 없다면
				back(sb); // 백트래킹~~
			}
			sb.deleteCharAt(sb.length() - 1); // check에서 append했으니까 하나 지워줌
		}
	}

	// 비교 -> 이거 구하다가 새벽 3시... 구선생 짱짱~!!
	private static boolean check(StringBuilder sb) {
		int len = sb.length();
		int mid = len / 2; // 반쪼개서 비교
		for (int i = 1; i < mid + 1; i++) {
			String s1 = sb.substring(len - i * 2, len - i);// 왼쪽
			String s2 = sb.substring(len - i, len); // 오른쪽
			if (s1.equals(s2)) { // 같아? -> 삐이이ㅣX
				return false;
			}
		}
		return true;
	}

}
