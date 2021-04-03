package s0410;

import java.util.Scanner;

/* <문제 요약>
 * 구해야 하는 것 : N개의 수와 N-1개의 연산자가 주어졌을때 만들 수 있는 결과의 최대, 최소  
 * 유의 조건 : 주어진 수의 순서는 바뀌면 안되고, 연산자 순서만 바꿔야 한다.
 * 유형 : DFS 백트래킹(순열로 해도 될거 같긴 하다).
 * <풀이법>
 * 1. 연산자의 개수만큼 dfs 탐색을 진행한다.
 * -> 단, 0으로는 나눌 수 없다.
 * -> 연산자의 개수 만큼만 탐색해야 한다.
 * <주의점>
 * 반복문으로 깔끔하게 줄일 수 있다면, 줄이는 것도 좋지만 우선 떠오르는 방식이 있다면 실전에서는 먼저 코딩부터 하자.
 */
public class BOJ_S1_14888 {
	static int N;
	static int[] arr;
	static int[] oper;
	static int max, min;

	public static void main(String[] args) {
		// 초기화
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		oper = new int[4];
		for (int i = 0; i < 4; i++) {
			oper[i] = sc.nextInt();
		}
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		dfs(0, arr[0]);
		System.out.println(max);
		System.out.println(min);
		sc.close();
	}

	private static void dfs(int cnt, int res) {
		// 연산자의 개수가 N - 1개가 되면 최대/최소값 갱신후 재귀를 종료한다.
		if (cnt == N - 1) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		// 더하기
		if (oper[0] > 0) {
			oper[0]--;
			dfs(cnt + 1, res + arr[cnt + 1]);
			oper[0]++;
		}
		// 빼기
		if (oper[1] > 0) {
			oper[1]--;
			dfs(cnt + 1, res - arr[cnt + 1]);
			oper[1]++;
		}
		// 곱하기
		if (oper[2] > 0) {
			oper[2]--;
			dfs(cnt + 1, res * arr[cnt + 1]);
			oper[2]++;
		}
		// 나누기
		if (oper[3] > 0 && arr[cnt + 1] != 0) {
			oper[3]--;
			dfs(cnt + 1, res / arr[cnt + 1]);
			oper[3]++;
		}
	}
}
