import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 문제 정의 : 치즈가 전부 녹는데 걸리는 시간과, 녹기 바로 직전 남아있는 치즈의 개수.
 * 문제 유형 : BFS.
 * 주의 사항 : 외부의 공기부터 시작하여 치즈를 녹여야한다.
 * <풀이 요약>
 * 1. 맵에 패딩을 주어 치즈를 입력 받는다.
 * 2. 맵의 가장 바깥에서 BFS를 이용하여 모든 공기의 위치를 큐에 넣는다.
 * 3. 넣은 큐부터 시작하여, BFS 탐색을 진행하며 치즈를 녹여 나아간다.
 * -> 단, 맵을 벗어나지 않는다.
 * -> 단, 방문한 지역은 재방문하지 않는다.
 * -> 치즈를 녹일 때 개수를 카운팅해둔다.
 * 4. 치즈가 모두 녹을 때까지 2, 3을 반복한다.
 * <피드백>
 * 아 깔끔하게 짜기 힘드네..
 */
public class BOJ_G5_2636_치즈 {
	static int N, M;
	static int[][] map;
	static Queue<int[]> air;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		// 맵에 패딩을 주어 입력 받는다.
		map = new int[N + 2][M + 2];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// BFS를 이용하여 외부 공기를 큐에 넣는다.
		addAir();
		// BFS를 이용하여 치즈를 녹인다.
		melt();

		sc.close();
	}

	private static void melt() {
		boolean[][] visited = new boolean[N + 2][M + 2];

		int time = 0;
		int cnt = 0;
		while (!air.isEmpty()) {
			int size = air.size();
			time++;
			cnt = 0;
			for (int l = 0; l < size; l++) {
				int[] cur = air.poll();
				visited[cur[0]][cur[1]] = true;
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];
					if (nr < 0 || nr >= N + 2 || nc < 0 || nc >= M + 2)
						continue;
					if (visited[nr][nc])
						continue;
					if (map[nr][nc] == 1) {
						cnt++;
						map[nr][nc] = 0;
						visited[nr][nc] = true;
						air.offer(new int[] { nr, nc });
					}
				}
			}
			addAir();
		}
		System.out.println(time);
		System.out.println(cnt);
	}

	private static void addAir() {
		air = new LinkedList<int[]>();
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { 0, 0 });
		boolean[][] visited = new boolean[N + 2][M + 2];
		visited[0][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			air.offer(cur);
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= N + 2 || nc < 0 || nc >= M + 2)
					continue;
				if (visited[nr][nc])
					continue;
				if (map[nr][nc] == 0) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		if (air.size() == (N + 2) * (M + 2)) {
			air.clear();
		}
	}
}
