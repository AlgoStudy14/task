import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약> 구해야 하는 것: 섬을 연결하는 다리 중 가장 짧은 다리. 제약 사항: 지도의 크기는 100, 섬은 여러개가 있음 문제
 * 유형: BFS 요구 개념: BFS
 * 
 * <풀이법 요약>
 * 1. BFS를 통해 섬에 번호를 입력한다.
 * 2. 섬에서 4방향 중 다리를 만들 수 있는 바다가 있으면 다리를 만든다.
 * 3. 가장 짧게 만들어진 다리의 길이를 출력한다.
 * 
 * 왜 틀렸을까.....
 */

public class BOJ_G3_2146_다리만들기 {

	static int N;

	static int[][] map;
	static boolean[][] v;
	
	static int min;
	
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int rabel = 2;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					// 섬 라벨링
					rabeling(i, j, rabel);
					rabel++;
				}
			}
		}
		
		min = 987654321;
		for(int i = 2; i < rabel; i++) {
			// 1번 섬부터 구해본다.
			BFS(i);
		}

		System.out.println(min);
	}

	public static void BFS(int rabel) {

		Queue<int[]> queue = new LinkedList<>();
		v = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == rabel) {
					// 섬을 큐에 다 넣는다.
					queue.add(new int[] {i, j, 0});
				}
			}
		}
		int res = 0;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (isBoundery(nr, nc))
					continue;
				// 다른 섬에 도착
				if (map[nr][nc] != 0 && map[nr][nc] != rabel) {
					min = Math.min(min, res);
					return;
				}
				// 아직 방문하지 않은 바다
				else if (map[nr][nc] == 0 && !v[nr][nc]) {
					v[nr][nc] = true;
					queue.add(new int[] { nr, nc, cnt + 1 });
				}
			}

		}

	}

	public static void rabeling(int i, int j, int rabel) {
		Queue<int[]> queue = new LinkedList<int[]>();
		v = new boolean[N][N];
		queue.add(new int[] { i, j });
		map[i][j] = rabel;
		v[i][j] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			for (int d = 0; d < 3; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (isBoundery(nr, nc)) continue;
				if (map[nr][nc] == 1 && !v[nr][nc]) {
					map[nr][nc] = rabel;
					v[nr][nc] = true;
					queue.add(new int[] { nr, nc });
				}
			}
		}
	}

	public static boolean isBoundery(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}
