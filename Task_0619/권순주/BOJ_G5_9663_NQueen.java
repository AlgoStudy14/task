import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * <문제 요약>
 * 문제 정의 : NXN의 체스판에 N개의 퀸을 놓는 방법의 수
 * 문제 유형 : 백트래킹
 * 
 * <풀이 요약>
 * 1. 가지치기를 하며 백트래킹을 진행한다 
 * -> 퀸의 이동 범위를 체크하면서 진행
 * -> 세로, 대각선을 비교하며서 체크
 * 2. N개의 체스를 사용한다면 answer를 증가시킨다
 * 
 * <피트백>
 * 이전에 풀었던 풀이와 2배 이상의 시간 차이가 발생! 문제점이 존재했다
 * 1. 비교 조건 -> 모든 위치로 이동해서 비교하는 부분에서 시간이 오래 걸린다 직관적인 비교가 필요
 * 2. 2차원 배열 -> 1차원 배열로 줄일 수 있는 방법을 생각하자
 * 다시 풀어보자!
 * 
 */

public class BOJ_G5_9663_NQueen {

	static int N;
	static int answer;
	static int[] dx = { -1, 1, -1, -1, 1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, 1 };
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		playChess(0);

		System.out.println(answer);
	}

	// 전체 문제 풀이
	private static void playChess(int chessCnt) {
		// 개수가 N개라면
		if (chessCnt == N) {
			// 결과값 증가
			answer++;
			return;
		}

		// 범위 돌리기
		for (int j = 0; j < N; j++) {
			// 만약 말을 놓을 수 있다면
			if (checkPoint(chessCnt, j)) {
				// 말을 놓기
				map[chessCnt][j] = 1;
				// 재귀
				playChess(chessCnt + 1);
				// 말을 빼기
				map[chessCnt][j] = 0;
			}
		}

	}

	// 말을 놓을 수 있는지 판단한다
	private static boolean checkPoint(int x, int y) {
		for (int i = 0; i < 6; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			while (checkRange(nx, ny)) {
				if (map[nx][ny] == 1) {
					return false;
				}
				nx += dx[i];
				ny += dy[i];
			}
		}

		return true;
	}

	private static boolean checkRange(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < N;
	}

}
