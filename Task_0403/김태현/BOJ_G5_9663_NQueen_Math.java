package s0327;

import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : N x N인 체스판에서 퀸 N개를 서로 공격할 수 없게 만드는 경우의 수.
 * 요구 개념/문제 유형 : DFS 백 트래킹, 수학적 방법 활용.
 * <풀이법>
 * 1. 체스판의 좌 상단(0, 0)부터 dfs 탐색을 진행한다.
 * -> 0 ~ N - 1열까지 차례로 퀸을 놓았다 빼는 것을 반복하되, 한 행에 퀸을 놓았으면 다음 행으로 먼저 dfs를 진행한다.
 * -> 지금 놓고자 하는 퀸을 놓을 수 있는지 저장된 퀸을 바탕으로 판단한다(가지 치기)(수학적 방법 이용).
 * -> 퀸을 선택했으면, 스택에 저장해둔다.
 * -> 행이 N이 되면 모든 행에 퀸을 놓은 것이므로 경우의 수 + 1.
 * 2. 수학적 방법
 * -> 퀸의 세로 위치 : 열 비교.
 * -> 퀸의 대각선 위치 : (대상의 x좌표 - 나의 x좌표 == 대상의 y좌표 - 나의 y좌표)라면 대각선 경로에 존재하는 것이다. 
 * <주의점>
 * 퀸을 놓을때, 어차피 행마다 퀸을 하나씩 밖에 놓지 못하므로 1차원 배열을 응용하는 아이디어를 써야한다.
 * 만일, 스택을 이용하여 퀸을 놓거나 빼는 과정을 추가로 구현하면 시간 초과가 발생한다.
 * 정적인 배열을 활용하는 방법에 익숙해지는게 좋다고 생각된다.
 * 또한, 어떤 대상이 대각선상에 위치하는지 판단하는 수학적 방법은 자주 활용되니 기억해두도록 하자.
 */

public class BOJ_G5_9663_NQueen_Math {
	static int N;
	static int cnt;
	static int[] queens;

	public static void main(String[] args) {
		// 초기화.
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// 퀸을 놓을 배열. 인덱스는 퀸이 놓인 행을, 인덱스의 값은 퀸이 놓인 열을 의미한다.
		queens = new int[N];
		// dfs탐색 진행.
		dfs(0);

		System.out.println(cnt);
		sc.close();
	}

	private static void dfs(int row) {
		// 모든 행에 퀸을 배치했다면, 경우의 수 + 1.
		if (row == N) {
			cnt++;
			return;
		}
		// 모든 열마다 퀸을 놓아볼 것이다.
		for (int col = 0; col < N; col++) {
			// 퀸을 놓을 수 있으면 놓기.
			if (simulation(row, col)) {
				// 퀸 놓기.
				queens[row] = col;
				// 행을 증가시켜 dfs 탐색 진행.
				dfs(row + 1);
			}
		}
	}

	private static boolean simulation(int row, int col) {
		// 현재 놓여있는 모든 퀸에 대하여 판단
		for (int i = 0; i < row; i++) {
			// 세로 위치에 있는지 확인.
			if (queens[i] == col) {
				return false;
			}
			// 대각선 위치에 있는지 확인.
			if (Math.abs(row - i) == Math.abs(col - queens[i])) {
				return false;
			}
		}
		// 퀸을 놓을 수 있다.
		return true;
	}
}
