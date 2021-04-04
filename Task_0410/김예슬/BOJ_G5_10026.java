package algo0404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/***
 * <문제 요약>
 * 구해야 하는 것 : 적록색약인 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때 구역의 수를 출력
 * 제약 사항 : 적록색약인 경우 R=G
 * 문제 유형 : BFS를 사용해 구역의 개수를 카운팅
 * <풀이법 요약>
 * 0. 입력을 받을 때 적록색약의 경우 'G'를 'R'로 바꿔 받음
 * 1. 적록색약이 아닐 때, 구역의 수를 BFS를 사용해 카운팅
 * 2. visited 배열 초기화!
 * 3. 적록색약일 때, 구역의 수를 BFS를 사용해 카운팅
 */

public class BOJ_G5_10026 {

	static int N;
	static char[][] normal, daltonism;
	static boolean[][] visited;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		normal = new char[N][N];
		daltonism = new char[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			normal[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (normal[i][j] == 'G') daltonism[i][j] = 'R';
				else daltonism[i][j] = normal[i][j];
			}
		}
		
		// 적록색약X
		System.out.print(solve(false) + " ");
		// visited 배열 초기화
		visitedInit();
		// 적록색약O
		System.out.println(solve(true));
	}

	static int solve(boolean isDaltonism) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j]) continue;
				
				++cnt;
				if (isDaltonism) bfs(i, j, daltonism);
				else bfs(i, j, normal);
			}
		}
		return cnt;
	}

	// 구역의 수를 카운팅
	static void bfs(int x, int y, char[][] target) {
		Queue<Pos> Q = new LinkedList<>();
		Q.offer(new Pos(x, y));
		char tmp = target[x][y];
		
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (target[nx][ny] != tmp || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				Q.offer(new Pos(nx, ny));
			}
		}
	}
	
	// visited 배열 초기화
	static void visitedInit() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}
}
