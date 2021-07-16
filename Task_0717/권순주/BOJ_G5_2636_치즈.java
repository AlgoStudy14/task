import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_2636_치즈 {

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
				if (map[i][j] == 1) { // 최초 치즈 개수 체크
					cheeseCnt++;
				}
			}
		}

		solve();
	}

	private static void solve() {
		List<Integer> cheeseCntList = new ArrayList<>(); // 치즈 개수 List
		cheeseCntList.add(cheeseCnt);
		
		int day = 0, lastCheeseCnt = cheeseCnt;
		while (cheeseCnt != 0) {
			visited = new boolean[N][M];
			cheeseMelting(new Point(0, 0)); // 0,0부터 시작
			cheeseCntList.add(cheeseCnt);
			day++;
		}

		if (cheeseCntList.size() > 1) { // 치즈가 전부 녹는데 2시간 이상이 거릴 때 
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
					cheeseCnt--; // 치즈 수 줄여주기
				} else if (map[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.add(new Point(nx, ny));
				}
			}
		}
	}

}
