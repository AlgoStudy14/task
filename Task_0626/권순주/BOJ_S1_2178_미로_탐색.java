import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : (1,1)에서 (N,M)의 위치로 이돌할 때 지나야 하는 최소 칸의 수
 * 문제 유형 : bfs
 * 
 * <풀이 요약>
 * 1. bfs를 진행한다.
 * 2. visited에 흔적을 저장해준다.
 * 3. visited[N-1][M-1]을 출력
 * 
 */

public class BOJ_S1_2178_미로_탐색 {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N, M;
	static int[][] map, visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		bfs();

		System.out.println(visited[N - 1][M - 1]);
	}

	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		visited[0][0] = 1;

		while (!q.isEmpty()) {
			Point current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != 1 || visited[nx][ny] != 0) {
					continue;
				}
				q.add(new Point(nx, ny));
				visited[nx][ny] = visited[current.x][current.y] + 1;
			}
		}

	}

}
