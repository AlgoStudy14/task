import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 땅에서 물로 이동할 수 있는 모든 최소 횟수의 합.
 * 문제 유형 : BFS.
 * 요구 개념 : BFS.
 * <풀이법 요약>
 * 1. BFS를 진행한다.
 * -> 처음 큐에는 모든 물의 위치를 집어 넣는다.
 * -> 방문 조건 : 지도를 벗어나지 않고, 방문한 적 없으며, 물이 아니다.
 * -> 방문을 완료하면 현재 이동 횟수를 값 배열에 대입하고, 해당 위치를 다시 큐에 넣는다.
 * -> 현재 이동 횟수는 이전 칸에 대하여 + 1처리하면 된다.
 * 2. 땅인 부분의 값을 모두 합한다.
 */

public class Solution_A_10966 {
	static int T;
	static int N, M;
	static char[][] map;
	static int[][] val;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 초기화.
			N = sc.nextInt();
			M = sc.nextInt();
			sc.nextLine();
			map = new char[N][M];
			val = new int[N][M];
			Queue<Node> q = new LinkedList<Node>();
			for (int i = 0; i < N; i++) {
				String line = sc.nextLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					// 처음 큐에는 모든 물의 위치를 집어 넣는다.
					if (map[i][j] == 'W') {
						q.add(new Node(i, j));
					}
				}
			}

			while (!q.isEmpty()) {
				Node cur = q.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.row + dr[dir];
					int nc = cur.col + dc[dir];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && val[nr][nc] == 0 && map[nr][nc] != 'W') {
						val[nr][nc] = val[cur.row][cur.col] + 1;
						q.add(new Node(nr, nc));
					}
				}
			}

			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(val[i][j] + " ");
					if (map[i][j] == 'L') {
						sum += val[i][j];
					}
				}
				System.out.println();
			}
			System.out.printf("#%d %d\n", t, sum);
		}
		sc.close();
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
