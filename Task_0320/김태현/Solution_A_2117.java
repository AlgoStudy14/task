import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 보안회사가 손해를 보지 않고 서비스 가능한 최대 집의 개수(이득의 크기는 중요하지 않음. 집의 개수가 중요).
 * 문제 유형/요구 개념 : 완전 탐색, 시뮬레이션. 
 * <풀이법 요약>
 * 1. 전체 집의 개수를 구하고, 확장 가능한 최대 K를 구한다.
 * 2. BFS 4방 탐색을 진행한다(최대 K까지).
 * -> 방문하는 위치는 방문 처리를 한다.
 * -> 방문을 하며 집이 있다면, 현재  손익을 갱신한다.
 * -> 진행 도중 확장된 영역이 '이득'인 상태라면, 현재 최대 서비스 가능 집의 개수를 갱신한다.
 * 3. 방문 배열을 초기화하고 2번을 반복한다.
 * 4. 모든 집에 대하여 완전 탐색을 종료하였다면, 결과를 출력한다.
 * 
 * <생각해본 풀이법>
 * V 풀이 1. 2차원 배열의 좌상단부터 시작하여, 우하단까지 각각 서비스를 최대까지 키워보며 완전 탐색. 크기의 한계는 전체 집의 개수를 다 더해도 손해일때까지.
 * -> 배열의 최대 크기는 20x20, 한 좌표당 최대 시뮬레이션 횟수를 곱해도 10만이상의 연산을 안하는것 같다.
 * -> 적절한 풀이법이라고 생각함.
 * 풀이 2(불가능). 집이 있는 위치에 대하여, BFS를 진행하면서 수익을 계산해본다. 
 * -> 집에 대해서만 탐색을 진행하는 경우는 예외 케이스가 존재한다. 완탐이 가능해보이면 웬만하면 완탐을 하자.
 */

public class Solution_A_2117 {
	static int T;
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	// 최대 집의 개수.
	static int max;
	// 최대 확장 가능 범위.
	static int K;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			visited = new boolean[N][N];
			max = 1;
			// 집의 개수
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					// 전체 집의 개수를 구한다.
					if (map[i][j] == 1) {
						cnt++;
					}
				}
			}
			// 확장 가능한 최대 K를 구한다.
			K = 1;
			while ((K * K + (K - 1) * (K - 1)) <= cnt * M) {
				K++;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 집이 있는 위치를 기준으로, BFS 4방 탐색을 진행한다(최대 K까지).
//					if (map[i][j] == 1) {
					bfs(i, j);
//					}
				}
			}

			// 모든 집에 대하여 완전 탐색을 종료하였다면, 결과를 출력한다.
			System.out.printf("#%d %d\n", t, max);
		}
		sc.close();
	}

	private static void bfs(int row, int col) {
		visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, col });
		visited[row][col] = true;
		int cnt;
		if (map[row][col] == 1) {
			cnt = 1;
		} else {
			cnt = 0;
		}
		for (int val = 2; val < K; val++) {
			int setUp = val * val + (val - 1) * (val - 1);
			int step = q.size();
			// BFS 한 사이클.
			for (int a = 0; a < step; a++) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
						// 방문하는 위치는 방문 처리를 한다.
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });
						// 방문을 하며 집이 있다면, 현재 손익을 갱신한다.
						if (map[nr][nc] == 1) {
							cnt++;
						}
					}
				}
			}

			if (setUp <= cnt * M) {
				// 진행 도중 확장된 영역이 '이득'인 상태라면, 현재 최대 서비스 가능 집의 개수를 갱신한다.
				max = Math.max(max, cnt);
			}
		}
	}
}
