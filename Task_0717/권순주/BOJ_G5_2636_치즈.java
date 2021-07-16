import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* (��)
 * <���� ���>
 * ���� ���� : ���� �߿��� ġ� ��� ��� �������µ� �ɸ��� �ð��� ��� ��� �� �ð� ���� �����ִ� ġ�� ������ ����
 * ���� ���� : BFS
 * ���� ���� : �׵θ��� �������
 * 
 * <�ǵ��>
 * BFS �������� Ǯ��µ� ���� Ǯ����� �� �� ����..����
 * �׸��� �Ź� BFS �����°� ���� ���� ���� Ǯ�̰� ������?? ��εǴ� ����
 * 
 */

public class BOJ_G5_2636_ġ�� {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int cheeseCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) { // ���� ġ�� ���� üũ
					cheeseCnt++;
				}
			}
		}

		solve();
	}

	private static void solve() {
		List<Integer> cheeseCntList = new ArrayList<>(); // ġ�� ���� List
		cheeseCntList.add(cheeseCnt);
		
		int day = 0, lastCheeseCnt = cheeseCnt;
		while (cheeseCnt != 0) {
			visited = new boolean[N][M];
			cheeseMelting(new Point(0, 0)); // 0,0���� ����
			cheeseCntList.add(cheeseCnt);
			day++;
		}

		if (cheeseCntList.size() > 1) { // ġ� ���� ��µ� 2�ð� �̻��� �Ÿ� �� 
			lastCheeseCnt = cheeseCntList.get(cheeseCntList.size() - 2);
		}
		
		System.out.println(day);
		System.out.println(lastCheeseCnt);
	}

	private static void cheeseMelting(Point point) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(point);
		visited[point.x][point.y] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
					continue;
				}
				if (map[nx][ny] == 1) {
					map[nx][ny] = 0;
					visited[nx][ny] = true;
					cheeseCnt--; // ġ�� �� �ٿ��ֱ�
				} else if (map[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));
				}
			}
		}
	}

}
