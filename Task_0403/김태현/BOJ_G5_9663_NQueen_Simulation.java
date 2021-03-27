package s0327;

import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : N x N인 체스판에서 퀸 N개를 서로 공격할 수 없게 만드는 경우의 수.
 * 요구 개념/문제 유형 : DFS 백 트래킹, 시뮬레이션.
 * <풀이법>
 * 1. 체스판의 좌 상단(0, 0)부터 dfs 탐색을 진행한다.
 * -> 0 ~ N - 1열까지 차례로 퀸을 놓았다 빼는 것을 반복하되, 한 행에 퀸을 놓았으면 다음 행으로 먼저 dfs를 진행한다.
 * -> 퀸을 선택했으면, 해당 퀸이 공격할 수 있는 경로를 맵에 모두 표시한다(시뮬레이션).
 * -> 맵을 바탕으로 지금 놓고자 하는 퀸을 놓을 수 있는지 판단한다(가지 치기).
 * -> 행이 N이 되면 모든 행에 퀸을 놓은 것이므로 경우의 수 + 1.
 * 2. 시뮬레이션
 * -> 현재 놓인 퀸의 좌표에 대하여 3방(7시, 6시, 5시)으로 퀸을 놓거나 뺀다.
 * -> 퀸을 놓을때는 맵에 +1, 뺄 때는 맵에서 -1한다.
 * <주의점>
 * 1. 아무 생각 없이 백트래킹을 진행하면 문제가 발생한다. 
 * 예를 들어, 퀸을 모두 놓고 퀸을 놓을 수 있는지 시뮬레이션을 한다면 가지 치기를 하는 것이 아니라, 완전 탐색을 하는 것이다.
 * 2. 따라서, 퀸을 놓을 수 있을 만한 곳을 미리 맵에 표시하면서 백트래킹을 진행하는 것이 시간을 단축시키는 방법이다.
 */

public class BOJ_G5_9663_NQueen_Simulation {
	static int N;
	static int[][] map;
	static int cnt;
	// 7시, 6시, 5시
	static int[] dr = { 1, 1, 1 };
	static int[] dc = { -1, 0, 1 };

	public static void main(String[] args) {
		// 초기화.
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];

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
			if (map[row][col] == 0) {
				// 퀸 놓기.
				simulation(row, col, 1);
				// 행을 증가시켜 dfs 탐색 진행.
				dfs(row + 1);
				// 놓은 퀸 회수.
				simulation(row, col, -1);
			}
		}
	}

	private static void simulation(int row, int col, int put) {
		// 맵에 퀸을 놓거나 빼기
		for (int i = 0; i < 3; i++) {
			int nr = row;
			int nc = col;
			map[nr][nc] += put;
			// 5시, 6시, 7시에 놓거나 빼기.
			while (true) {
				nr += dr[i];
				nc += dc[i];
				if (!checkBoundary(nr, nc)) {
					break;
				}
				map[nr][nc] += put;
			}
		}
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
