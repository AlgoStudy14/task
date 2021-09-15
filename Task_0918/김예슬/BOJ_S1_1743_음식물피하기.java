package �迹��;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <���� ���>
 * ���� ����: ���Ĺ� �� ���� ū ���Ĺ��� ũ�⸦ ���
 * ���� ����: BFS
 * <Ǯ�� ���>
 * 1. ��� ��ο� ���� ���Ĺ��� ������ �ִ��� üũ
 * 2. map ��ü�� �湮üũ�� ����
 *  2-1. map[x][y]��� �湮�� �ʿ� ����
 * 3. ���Ĺ��� ũ�⸦ bfs�� ����� üũ
 *  3-1. size��� ������ ����� bfs�� �� �� ������ ����������
*/

public class BOJ_S1_1743_���Ĺ����ϱ� {

	static int N, M, K, max;
	static boolean[][] map;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new boolean[N + 1][M + 1];
		
		for (int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = true;
		}
		
		solve();
		System.out.println(max);
	}

	static void solve() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!map[i][j]) continue;
				
				bfs(i, j);
			}
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Pos> Q = new LinkedList<>();
		Q.add(new Pos(x, y));
		map[x][y] = false;
		int size = 1;
		
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
				if (!map[nx][ny]) continue;
				
				Q.add(new Pos(nx, ny));
				map[nx][ny] = false;
				++size;
			}
		}
		
		max = Math.max(max, size);
	}
}
