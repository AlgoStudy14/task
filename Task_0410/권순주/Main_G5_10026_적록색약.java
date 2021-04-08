import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수
 * 유형 : bfs
 * 
 * <풀이법 요약>
 * 이 문제는 풀면서 어떻게 하면 더 알차게? 풀 수 있을지 고민을 많이 했던것 같다..
 * 근데 고민하다가 귀찮아서 map도 2개 visited도 2개 각각 만들어 줬다 
 * 2중 for문 두번 쓰는게 귀찮아서... ㅎㅎ 
 * 
 * 1. 입력받은 그림의 G를 R로 바꿔서 적록 색맹이 보는 map을 따로 저장
 * 2. 일반인과 적록 색맹 각각 bfs를 돌면서 영역의 수를 찾아준다
 */

public class Main_G5_10026_적록색약 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	// map : 입력받은 그림
	// RGmap : 적록 색약이 보는 그림
	static char[][] map, RGmap;
	// visited : 일반인의 방문 체크
	// RGvisited : 적록 색약의 방문 체크
	static boolean[][] visited, RGvisited;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		RGmap = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'G') { // 만약 입력받은 값이 G라면
					RGmap[i][j] = 'R'; // 적록 색약의 그림은 R이라고 저장한다
				} else { // 만약 입력값이 R,B라면 
					RGmap[i][j] = map[i][j]; // 그대로 저장
				}
			}
		}

		// normal : 일반인이 보는 구역의 수 
		// color : 적록 색맹이 보는 구역의 수
		int normal = 0, color = 0;
		visited = new boolean[N][N];
		RGvisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 일반인
				if (!visited[i][j]) { 
					bfs(map[i][j], i, j, map, visited);
					normal++;
				}
				// 적록 색맹
				if (!RGvisited[i][j]) {
					bfs(RGmap[i][j], i, j, RGmap, RGvisited);
					color++;
				}
			}
		}

		System.out.println(normal + " " + color);

	}

	// tmp : 각각의 그림
	// check : 각각의 방문 체크
	// 함수를 사용할 때 일반인, 적록 색맹에 맞게 파라미터로 넣어서 사용한다
	private static void bfs(char color, int x, int y, char[][] tmp, boolean[][] check) {
		q.offer(new Point(x, y));
		check[x][y] = true;

		while (!q.isEmpty()) {
			Point current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				// 처음 위치의 color와 다르다면 다른 구역이기 때문에 continue로 넘겨준다
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || check[nx][ny] || tmp[nx][ny] != color) {
					continue;
				}
				q.offer(new Point(nx, ny));
				check[nx][ny] = true;
			}
		}
	}

}
