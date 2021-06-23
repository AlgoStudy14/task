package s0626;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 규칙에 맞게 칠공주를 결성할 수 있는 경우의 수를 구하라.
 * 문제 유형 : (DFS), (조합, BFS)
 * 주의 사항 : 총 맵의 크기가 25이고, 25C7은 48만정도의 경우의 수가 도출되므로 완전 탐색이 가능해보인다.
 * 
 * <풀이 요약 : DFS : 실패>
 * 1. 이중 for문을 이용하여 맵의 좌상단부터 우하단까지 모든 경우에 대한 탐색을 진행하며 모든 경우를 계산해본다.
 * 2. DFS 탐색 : 현 지점을 기준으로 상하좌우로 탐색한다.
 * -> 단, 맵을 벗어나지 않는다.
 * -> 단, 임도연파의 학생이 4명 이상이 되지 않는다.
 * -> 단, 방문한 지역을 방문하지 않는다.
 * -> 깊이가 7이 된다면, 경우의 수를 + 1하고 재귀를 종료한다.
 * <피드백>
 * 탐색이 불가능한 경우가 많이 있음. 초기 아이디어가 떠오르면 어느정도 검증하는 습관 들이기.
 * 또한, 중복되는 경우를 제거해야하는 로직을 추가해야하는 번거로움도 있음.
 * DFS를 심화해서 풀이하는 방법이 있는거 같긴한데.. 잘 모르겠음.
 * 
 * <풀이 요약 : 조합, BFS>
 * 1. 5x5 맵에서 25C7조합에 대하여 칠공주 결성조건에 맞는지 판단한다.
 * 2. 결성 조건 
 * -> 각 조합에 대해서 이다솜파의 학생이 4명이상인지 판단한다.
 * -> 각 조합에 대해서 모든 학생이 인접해 있는지 BFS를 이용하여 판단한다.
 * -> 위 두 조건을 만족한다면 칠공주 결성 경우의 수를 + 1한다.
 * <피드백>
 * 조건에 따라 조합을 사용한다는 아이디어를 떠올릴 수 있어야 한다. BFS와 조합을 연습하기에 좋은 문제였다.
 */

public class BOJ_G3_1941_소문난칠공주 {
	static char[][] map;
	static boolean[][] visited;
	static int s;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new char[5][5];
		visited = new boolean[5][5];
		s = 0;
		ans = 0;
		for (int i = 0; i < 5; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		// 조합을 이용하여 25C7의 모든 경우를 따져본다.
		Combination(0, 0);

		System.out.println(ans);

		sc.close();
	}

	private static void Combination(int cnt, int idx) {
		// 7명의 학생이 된다면, 결성조건이 맞는지 판단한다.
		if (cnt == 7) {
			// 이다솜파가 4명이상이고, 모든 학생이 인접해있다.
			if (s >= 4 && checkAdj()) {
				ans++;
			}
			return;
		}
		// 25명의 학생 중 7명을 선택한다.
		for (int i = idx; i < 25; i++) {
			int r = i / 5;
			int c = i % 5;
			visited[r][c] = true;
			if (map[r][c] == 'S')
				s++;
			Combination(cnt + 1, i + 1);
			if (map[r][c] == 'S')
				s--;
			visited[r][c] = false;
		}
	}

	private static boolean checkAdj() {
		boolean[][] visited2 = new boolean[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0 ; j < 5; j++) {
				visited2[i][j] = visited[i][j];
			}
		}
		
		Queue<int[]> q = new LinkedList<int[]>();
		// 최초로 방문된 지점을 큐에 넣는다.
		boolean flag = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (visited2[i][j]) {
					q.offer(new int[] { i, j });
					visited2[i][j] = false;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		// 해당 지점으로부터 인접한 모든 true의 개수가 7개인지 판단한다.
		int cnt = 1;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 맵을 벗어나지 않고, true인 경우만 탐색
				if (checkBoundary(nr, nc) && visited2[nr][nc]) {
					cnt++;
					visited2[nr][nc] = false;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		if (cnt == 7) {
			return true;
		}
		else
			return false;
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < 5 && nc >= 0 && nc < 5;
	}
}
