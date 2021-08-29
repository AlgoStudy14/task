package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <풀이 : 시뮬레이션>
 * 1. 회전.
 * 2. 얼음의 양 감소.
 * 3. 가장 큰 덩어리 찾기.
 * <피드백>
 * 1. 회전 관련 알고리즘 기억해두기.
 * 2. 한 번에 넣어두고 처리하는 방식은 2가지가 있다.
 * -> 임시 배열에 복사한뒤 계산하거나
 * -> ArrayList에 넣어서 추후에 한 번에 계산하거나
 * 3. 문제가 정말 틀린점이 없으면 로직 자체를 잘 못 접근한거라고 생각하자.
 * -> 이 문제 문제 설명 매우매우 애매함..;
 */

public class BOJ_G4_20058_마법사상어와파이어스톰 {
	static int N, Q, L;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		N = (int) Math.pow(2, N);
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			L = Integer.parseInt(st.nextToken());
			divide((int) Math.pow(2, L));
			reduce();
		}
		find();
	}

	// 전체 합과 가장 큰 덩어리의 합을 구한다.
	private static void find() {
		// 전체합
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);

		// 넘버링
		visited = new boolean[N][N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					max = Math.max(max, bfs(i, j));
				}
			}
		}

		if (max == 1) {
			System.out.println(0);
		} else {
			System.out.println(max);
		}
	}

	private static int bfs(int r, int c) {
		int cnt = 1;

		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (!visited[nr][nc] && map[nr][nc] != 0) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 인접한 4개의 지역을 탐사하고, 얼음의 양을 줄인다.
	private static void reduce() {
		ArrayList<int[]> temp = new ArrayList<int[]>();

		// 모든 얼음을 탐사한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					// 범위를 벗어나면 아웃
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					// 인접한 경우가 1이상인 경우만 카운팅.
					if (map[nr][nc] >= 1)
						cnt++;
				}
				// 얼음이 있는 지역이 2개 이하라면, 얼음의 양을 1 줄인다.
				if (cnt <= 2 && map[i][j] != 0)
					temp.add(new int[] { i, j });
			}
		}

		for (int[] point : temp)
			map[point[0]][point[1]]--;
	}

//	private static void printMap() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}

	private static void divide(int range) {
		// 분할된 영역의 개수 만큼 반복
		for (int i = 0; i < N; i += range) {
			for (int j = 0; j < N; j += range) {
				rotate(i, j, range);
			}
		}
	}

	private static void rotate(int r, int c, int range) {
		// 전체 격자의 임시 저장 배열을 만든다.
		int[][] temp = new int[range][range];

		// 배열을 90도 회전 시켜 temp에 저장한다.
		for (int i = r; i < r + range; i++) {
			for (int j = c; j < c + range; j++) {
				temp[j - c][r + range - i - 1] = map[i][j];
			}
		}

		// 다시 원 배열에 저장한다.
		for (int i = r; i < r + range; i++) {
			for (int j = c; j < c + range; j++) {
				map[i][j] = temp[i - r][j - c];
			}
		}
	}
}
