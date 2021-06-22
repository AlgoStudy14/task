package s0626;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 시작 위치부터 도착 위치까지 가는 최소 비용.
 * 문제 유형 : BFS.
 * 주의 사항 : 시작 위치도 포함하여 계산한다.
 * <풀이 요약 : BFS>
 * 1. BFS를 이용하여, 도착지점에 도착할 때의 비용을 출력한다.
 * -> 한 번 방문한 칸은 방문하지 않는다.
 * -> 0인 칸은 방문하지 않는다.
 * -> 맵을 벗어난 칸은 방문하지 않는다.
 * <피드백>
 * 2차원 배열 1개만 선언해서 푸는 방법이 있나?
 */

public class BOJ_S1_2178_미로탐색 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		bfs();
		System.out.println(map[N - 1][M - 1]);

		sc.close();
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { 0, 0 });
		visited[0][0] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 맵을 벗어나지 않고, 방문한 적 없으며, 0이 아닌 경우.
				if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
					map[nr][nc] = map[cur[0]][cur[1]] + map[nr][nc];
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
