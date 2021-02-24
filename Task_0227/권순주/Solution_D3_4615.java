import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 오셀로 게임을 마친 뒤 각각의 돌의 개수 구하기
 * 문제 핵심 요약 : 먼저 탐색하고 오셀로가 성립된다면 바꿔주기
 * <풀이법 요약> 
 * 1. 돌을 놓으면 board에 표시
 * 2. 8가지 방향으로 탐색
 * 3. 해당 방향의 다음 돌이 나와 다른색 이라면 탐색 시작
 * 4. 나와 같은 색의 돌이 나올 때까지 탐색 
 * 4-1. 나와 같은 색이 나오면 -> 반복문으로 해당 방향의 돌의 색을 모두 나와 같은 색으로 바꿔줌
 * 4-2. 나와 다른 색이 나오면 -> 방향 바꿈
 * 5. 구한 board를 가지고 각가의 돌을 카운팅
 */

public class Solution_D3_4615 {

	static int[][] board;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			board = new int[N][N];

			board[N / 2 - 1][N / 2 - 1] = board[N / 2][N / 2] = 2;
			board[N / 2 - 1][N / 2] = board[N / 2][N / 2 - 1] = 1; // 기본값 저장

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int color = Integer.parseInt(st.nextToken());
				board[x - 1][y - 1] = color; // 돌을 놓으면 board에 표시

				check(x - 1, y - 1, color);
			}

			System.out.printf("#%d %s\n", t, count());
		}
	}

	private static void check(int x, int y, int color) {
		int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
		for (int i = 0; i < 8; i++) { // 8방향 탐색
			int nx = x + dx[i];
			int ny = y + dy[i]; // 다음 위치
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 0) { // 만약 board를 넘어가거나 해당 위치의 board가 0이면 다른
				continue;
			}
			if (board[nx][ny] == (3 - color)) { // 만약 나와 다른 돌이면
				boolean flag = false; // 오셀로가 성립된는지 checking
				int tmpx = nx, tmpy = ny; // tmp에 잠시 보관 -> 오셀로가 성립되는지 확인을 위해
				while (true) {
					// 만약 board를 넘어가거나 해당 위치의 board가 0이면 오셀로가 성립되지 않기 때문에 break
					if (tmpx < 0 || tmpx >= N || tmpy < 0 || tmpy >= N || board[tmpx][tmpy] == 0) {
						break;
					}
					if (board[tmpx][tmpy] == color) { // 만약 해당 위치의 board가 나의 색과 같다면
						flag = true; // flag를 true로 만들고 반복문 나옴
						break;
					}
					tmpx += dx[i]; // 위의 조건문에 걸리지 않는다면
					tmpy += dy[i]; // 다음 탐색을 위해 x,y 증가
				}
				if (flag) { // 만약 오셀로가 성립된다면
					while (board[nx][ny] != color) { // 나와 같은 색이 나올 때 까지 나와 같은 색으로 바꿔줌
						board[nx][ny] = color;
						nx += dx[i];
						ny += dy[i];
					}
				}
			}
		}
	}

	private static String count() { // 카운팅 해주는 함수
		int cntB = 0, cntW = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					cntB++;
				} else if (board[i][j] == 2) {
					cntW++;
				}
			}
		}
		return cntB + " " + cntW;
	}

}
