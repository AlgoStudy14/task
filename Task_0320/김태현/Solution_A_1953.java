import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 시간이 경과한 후 탈주범이 있을 수 있는 터널의 위치의 개수.
 * 문제 유형 : BFS, 구현(시뮬레이션).
 * 요구 개념 : BFS, 방향.
 * <풀이법 요약>
 * 1. 초기 위치를 큐에 넣는다.
 * 2. 파이프의 모양에 따라 다음 파이프를 탐색한다.
 * 단, 다음 좌표의 파이프가 범위 내에 있고, 방문하지 않았으며, 파이프가 존재하고, 파이프간 연결되어 있어야 한다.
 * <주의사항>
 * 1. 이 문제는 구현을 잘못하면 디버깅이 까다로운 편이다. 구현해야할 요소를 명확히 식별하고, 꼼꼼히 구현하도록하자.
 * (디버깅 하는데 시간을 너무 오래 썼다).
 * 2. BFS의 한 사이클을 판단하는 방법은 q에 추가된 사이즈 만큼 for문으로 감싸는 것이다.
 * 3. 시간 조건에 대해서 미비하게 생각했다. while문 탈출 조건은 항상 유의해서 짜도록 하자.
 */

public class Solution_A_1953 {
	static int T;
	static int N, M, R, C, L;
	static int map[][];
	static boolean visited[][];
	static int cnt, hour;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	// 파이프 유형
	static int[][] pipe = { { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 }, { 0, 2 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화.
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 초기 위치를 큐에 넣는다.
			Queue<Node> q = new LinkedList<Node>();
			q.add(new Node(R, C));
			visited[R][C] = true;
			// cnt = 1로 초기화.
			cnt = 1;
			hour = 1;
			while (!q.isEmpty() && hour < L) {
				int step = q.size();
				// BFS 한 사이클을 판단하는 방법.
				for (int s = 0; s < step; s++) {
					Node cur = q.poll();
					int nr;
					int nc;
					for (int i = 0; i < pipe[map[cur.row][cur.col] - 1].length; i++) {
						nr = cur.row + dr[pipe[map[cur.row][cur.col] - 1][i]];
						nc = cur.col + dc[pipe[map[cur.row][cur.col] - 1][i]];
						// 범위 내에 있고, 방문하지 않았으며, 파이프가 존재하고, 파이프간 연결성 확인
						if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && !visited[nr][nc]
								&& isConnect(pipe[map[cur.row][cur.col] - 1][i], nr, nc)) {
							cnt++;
							visited[nr][nc] = true;
							q.add(new Node(nr, nc));
						}
					}
				}
				hour += 1;
			}
			System.out.printf("#%d %d\n", t, cnt);
			// 큐의 모양에 따라 주변을 탐색하고, 탐색된 지점을 다시 큐에 넣는다.
		}
		sc.close();
	}

	private static boolean isConnect(int dir, int nr, int nc) {
		// 상 <-> 하, 좌 <->우
		if (dir == 0) {
			dir = 1;
		} else if (dir == 1) {
			dir = 0;
		} else if (dir == 2) {
			dir = 3;
		} else {
			dir = 2;
		}
		for (int i = 0; i < pipe[map[nr][nc] - 1].length; i++) {
			if (pipe[map[nr][nc] - 1][i] == dir) {
				return true;
			}
		}
		return false;
	}

	static class Node {
		int row;
		int col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
