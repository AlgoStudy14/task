package s0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 치즈가 모두 녹는데 걸리는 시간. 
 * 제한 조건 : 모눈종이의 맨 가장자리는 항상 외부공기이다(중요).
 * 문제 유형 : BFS, 시뮬레이션.
 * <풀이법>
 * 1. 모든 치즈를 따로 기억해둔다.
 * 2. 모눈종이의 외부 공기 영역을 BFS를 이용하여 표시해둔다. 이때 치즈를 방문하는 경우는 해당 치즈를 몇번 방문했는지 체크해둔다.
 * 3. 모든 치즈에 대하여, 외부 공기로부터 2번 이상 방문된 치즈를 제거한다.
 * 4. 2, 3을 반복한다.
 * -> 치즈를 저장해둔 배열이 빈다면, 반복을 종료한다.
 */

public class BOJ_G4_2638_치즈 {
	static int N, M;
	static int[][] map;
	static int[][] cheese;
	static int size;
	// 외부 공기에서 방문 횟수
	static int[][] cnt;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cheese = new int[(N - 2) * (M - 2)][2]; // 치즈의 최대 개수 만큼!
		size = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cheese[size++] = new int[] { i, j };
				}
			}
		}

		int time = 0;
		while (true) {
			// 외부 공기 영역을 bfs를 이용하여 구한다(Flood fill)
			time++;
			bfs();
			// 존재하는 모든 치즈에 대하여 외부 공기와 2변 이상 접촉한 치즈를 제거하고 공기로 바꾼다.
			melt();
			if (size == 0) {
				break;
			}
		}
		System.out.println(time);
	}

	private static void melt() {
		for (int i = 0; i < size; i++) {
			if (cnt[cheese[i][0]][cheese[i][1]] >= 2) {
				map[cheese[i][0]][cheese[i][1]] = 0;
				cheese[i] = cheese[size - 1];
				size--;
				i--;
			}
		}
	}

	private static void bfs() {
		// 방문 횟수 초기화
		cnt = new int[N][M];
		// 방문 여부
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<int[]>();
		// 가장 자리는 항상 외부공기이다.
		q.offer(new int[] { 0, 0 });
		visited[0][0] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 범위를 벗어나지 않고, 방문한적 없으면 방문하기
				if (checkBoundary(nr, nc) && !visited[nr][nc]) {
					// 만약, 해당 영역이 공기라면 탐색을 계속 진행하기(방문 횟수는 그대로)
					if (map[nr][nc] == 0) {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });

					}
					// 만약, 해당 영역이 치즈라면 탐색은 진행하지 않고 방문 횟수 + 1.
					else {
						cnt[nr][nc]++;
					}
				}
			}
		}
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
