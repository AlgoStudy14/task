package s0327;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 가장 크기가 작은 좋은 수열.
 * 요구 개념/문제 유형 : DFS 백트래킹(중복 순열), 구현.
 * <풀이법>
 * 1. DFS를 이용하여 숫자 1, 2, 3 순서로 하나씩 추가한다.
 * -> 뒤의 두 수가 같은지 차례로 확인한다(길이가 2, 4, 6...).
 * -> 길이가 N이 되면 탈출(종료 조건) : 숫자는 1, 2, 3 순서로 넣었으므로 이미 최소값이다.
 */
public class BOJ_G4_2661 {
	static int N, min;
	static int[] arr = { 1, 2, 3 };
	static boolean flag;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 초기화
		N = sc.nextInt();
		min = Integer.MAX_VALUE;
		flag = false;
		// dfs 백트래킹
		dfs(0, "");
		sc.close();
	}

	private static void dfs(int length, String cur) {
		String a, b;
		// 뒤의 두 수가 같은지 차례로 확인한다(길이가 2, 4, 6...)(가지 치기).
		for (int i = 2; i <= length; i += 2) {
			a = cur.substring(cur.length() - i, cur.length() - i / 2);
			b = cur.substring(cur.length() - i / 2, cur.length());
			if (a.equals(b)) {
				return;
			}
		}
		// 작은 수부터 넣기 시작했으니, 목표 길이가 되면 나머지 부분은 탐색할 필요가 없음(종료 조건).
		if (length == N) {
			System.out.println(cur);
			flag = true;
			return;
		}

		// 중복 순열
		for (int i = 0; i < 3; i++) {
			dfs(length + 1, cur + arr[i]);
			if (flag) {
				return;
			}
		}
	}
}
