import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수
 * 유형 : bfs, 약간의 완탐?
 * 
 * <풀이법 요약>
 * 1. 입력 높이의 최댓값과 최솟값을 저장
 * 2. 최솟값 <= 높이 <= 최댓값 범위 안의 높이를 다 탐색한다
 * 2-1. 해당 높이보다 높은 영역이 있다면 bfs 진행
 * 2-2. bfs를 진행할 때마다 안전 영역 카운팅
 * 3. 2번을 진행하면서 카운팅한 값의 최대값을 계산한다
 */

public class Main_S1_2468_안전_영역 {

	static class Point { // 위치를 저장할 class
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		// 높이의 범위를 정하기 위한 변수 
		// min : 최소 높이
		// max : 최대 높이
		int min = 0, max = 101;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]); // 최소 높이 계산
				max = Math.max(max, map[i][j]); // 최대 높이 계산
			}
		}

		int answer = 0; // 최대 개수를 구하기 위한 변수
		for (int h = min; h <= max; h++) { // 높이를 min~max까지 탐색
			int count = 0; // 안전 영역의 개수 저장
			visited = new boolean[N][N]; // 높이를 바꿔줄 때마다 방문 체크 배열 다시 할당
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] > h) { // 만약 해당 높이보다 크다면  bfs 진행
						bfs(i, j, h);
						count++; // 안전 영역 카운팅
					}
				}
			}
			answer = Math.max(answer, count); // 안전 영역의 최대값을 계산
		}

		System.out.println(answer);
	}

	// 안전 영역 찾기!
	public static void bfs(int x, int y, int h) {
		q.offer(new Point(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Point current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				// 높이가 h 이하이면 물에 잠기기 때문에 조건을 걸어준다
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] <= h) {
					continue;
				}
				q.offer(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}

}
