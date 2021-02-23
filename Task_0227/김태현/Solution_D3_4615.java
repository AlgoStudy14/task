package study0227;

/***
 * <문제 요약>
 * 구해야 하는 것 : 규칙에 따라 오셀로를 진행한 뒤 남은 흑돌과 백돌의 수.
 * 문제 유형 : 구현.
 * 요구 개념 : 2차원 배열, 행렬.
 * <풀이법 요약>
 * 1. 초기 보드 및 흑, 백돌의 위치를 세팅한다.
 * 2. 각 돌을 놓을때 마다 그 돌을 기준으로 8방향을 차례로, 끝 까지 탐색한다.
 * -> 만일 같은 돌이 나온다면, 그 위치부터 입력 위치 사이의 모든 돌을 같은 색으로 바꾼다.
 * -> 만일 탐색하는 도중 빈 칸이 나오거나(돌이 없는 칸), 인덱스를 벗어났다면 해당 방향을 탐색을 종료한다.
 * 3. 최종 흑돌과 백돌의 개수를 세고 출력한다.
 */

import java.util.Scanner;

public class Solution_D3_4615 {
	static int T;
	static int N, M;
	static int[][] board;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			// 맵 초기화
			board = new int[N][N];
			board[N / 2 - 1][N / 2 - 1] = 2;
			board[N / 2][N / 2] = 2;
			board[N / 2][N / 2 - 1] = 1;
			board[N / 2 - 1][N / 2] = 1;
			M = sc.nextInt();
			for (int i = 0; i < M; i++) {
				int r = sc.nextInt() - 1;
				int c = sc.nextInt() - 1;
				int kind = sc.nextInt();
				// 8방향 탐색.
				check(r, c, kind);
			}
			// 최종 돌의 수 세기.
			int black = 0;
			int white = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == 1) {
						black++;
					}
					if (board[i][j] == 2) {
						white++;
					}
				}
			}
			System.out.printf("#%d %d %d\n", t, black, white);
		}
		sc.close();
	}

	private static void check(int r, int c, int kind) {
		// 동쪽부터 시계 방향으로 8방향.
		int[] dr = { 0, 1, 1, 1, 0, -1, -1, -1 };
		int[] dc = { 1, 1, 0, -1, -1, -1, 0, 1 };

		// 8방향 탐색
		for (int i = 0; i < 8; i++) {
			// 인덱스를 벗어나거나, board가 비어있다면(0이라면) 탐색 종료
			int nr = r + dr[i];
			int nc = c + dc[i];
			while ((0 <= nr && nr < N && 0 <= nc && nc < N) && (board[nr][nc] != 0)) {
				// 만일 같은 숫자를 찾으면, 해당 방향 사이의 모든 돌의 색 바꾸고 다른 방향 탐색.
				if (board[nr][nc] == kind) {
					while (nr != r || nc != c) {
						nr -= dr[i];
						nc -= dc[i];
						board[nr][nc] = kind;
					}
					break;
				}
				// 못 찾았다면 해당 방향 계속 해서 탐색.
				nr += dr[i];
				nc += dc[i];
			}
		}
	}
}
