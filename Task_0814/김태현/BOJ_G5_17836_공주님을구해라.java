package ss;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 문제 정의 : 용사가 주어진 시간내에 공주가 있는 곳으로 이동할 수 있는가?
 * 문제 유형 : BFS, 맨해튼 거리.
 * <풀이 요약>
 * 1. 먼저, 일반적인 BFS를 사용하여 공주가 있는 곳으로 이동하는 시간을 계산한다.
 * 2. 용사 ~ 검까지의 맨해튼 거리와 검~ 공주까지의 맨해튼 거리를 구한다(N + M - 2).
 * 3. 1, 2의 최솟 값을 구한다.
 * 4. 해당 최솟 값이 T시간 이내라면, 해당 시간을 출력하고  
 * <피드백>
 * 왜 틀리지 하..
 */

public class BOJ_G5_17836_공주님을구해라 {
	static int N, M, T;
	static int[][] map;
	static int min, gram;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		T = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		min = Integer.MAX_VALUE;
		gram = Integer.MAX_VALUE;

		int res = bfs();
		if(res == -1) {
			System.out.println("Fail");
			sc.close();
			return;
		} else {
			res = Math.min(res, gram);
			min = Math.min(min, res);
		}

		if (min <= T) {
			System.out.println(min);
		} else {
			System.out.println("Fail");
		}

		sc.close();
	}

	private static int bfs() {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		boolean[][] visited = new boolean[N][M];

		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { 0, 0 });
		visited[0][0] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				int[] cur = q.poll();

				if (cur[0] == N - 1 && cur[1] == M - 1)
					return cnt;
				if (map[cur[0]][cur[1]] == 2) {
					return cnt + (N - cur[0]) + (M - cur[1]) - 2;
				}

				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;
					if (visited[nr][nc])
						continue;
					if (map[nr][nc] != 1) {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });
					}
				}
			}
			cnt++;
		}
		
		if (!visited[N - 1][M - 1])
			return -1;
		else
			return cnt;
	}
}
