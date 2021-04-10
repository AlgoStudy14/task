package s0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 미로에서 탈출 가능한 칸의 개수.
 * 문제 유형 : DFS 백트래킹, 메모이제이션.
 * <풀이법>
 * 1. 맵의 좌상단부터 우 하단까지 DFS 탐색을 진행한다.
 * -> 최종 지점이 탈출지점이라면(맵을 벗어나거나, 해당 탈출 배열의 값이 1이거나), 이전 좌표에 1을 대입하며 재귀를 종료한다(return 활용).
 * -> 탈출 지점이라면, 재귀를 종료하면서 탈출 가능 칸 수를 1씩 추가 시켜준다.
 * -> 최종 지점이 탈출 불가능 지점이라면(이미 방문한 지점을 방문하거나, 해당 탈출 배열의 값이 2이거나), 이전 좌표에 2를 대입하며 재귀를 종료한다(return 활용).
 * 2. 맵에서 이미 방문했던 지점이라면(1혹은 2가 표시된) 탐색을 진행하지 않는다.
 * <참고 사항>
 * 재귀가 어떻게 구성되는지 더 구체적으로 이해하기 좋은 문제이다.
 */

public class BOJ_G2_17090_미로탈출하기 {
	static int N, M;
	static char[][] map;
	// 각 지점의 탈출 가능성을 저장해둔 배열
	static int[][] exist;
	// 최종 탈출 가능 칸 수
	static int ans;

	public static void main(String[] args) throws IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		exist = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		ans = 0;

		// 맵의 좌상단부터 우하단까지 탐색 진행
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 탈출 가능 여부를 모르는 지역만 dfs 탐색 진행
				if (exist[i][j] == 0) {
					dfs(i, j);
				}
			}
		}
		System.out.println(ans);
	}

	// 이전 좌표를 기억하면서 dfs탐색 진행.
	private static int dfs(int cur_row, int cur_col) {
		// 범위를 벗어났다면, 이전 값들을 탈출가능(=1)으로 표시하며 재귀를 종료.
		if (!checkBoundary(cur_row, cur_col)) {
			return 1;
		}
		// 만일 1로 표시된 곳을 방문했다면, 탈출 가능(=1)으로 표시하며 재귀 종료.
		if (exist[cur_row][cur_col] == 1) {
			return 1;
		}
		// 만일 2로 표시된 곳을 방문했다면, 탈출 불가능(=2)으로 표시하며 재귀 종료.
		if (exist[cur_row][cur_col] == 2) {
			return 2;
		}
		// 만일 -1로 표시된 곳(현재 방문하며 진행된 곳)을 방문했다면, 탈출 불가능(=2)으로 표시하며 재귀 종료.
		if (exist[cur_row][cur_col] == -1) {
			return 2;
		}
		// 다음 지역 방문
		exist[cur_row][cur_col] = -1; // 방문 지역외 방문체크를 위해 -1을 대입하면서 탐색 진행.
		switch (map[cur_row][cur_col]) {
		case 'U':
			exist[cur_row][cur_col] = dfs(cur_row - 1, cur_col);
			break;
		case 'D':
			exist[cur_row][cur_col] = dfs(cur_row + 1, cur_col);
			break;
		case 'L':
			exist[cur_row][cur_col] = dfs(cur_row, cur_col - 1);
			break;
		default:
			exist[cur_row][cur_col] = dfs(cur_row, cur_col + 1);
			break;
		}
		// 만일, 탈출이 가능한 집합인경우 재귀를 탈출하면서 탈출 가능한 칸수 + 1.
		if (exist[cur_row][cur_col] == 1) {
			ans++;
		}
		return exist[cur_row][cur_col];
	}

	private static boolean checkBoundary(int cur_row, int cur_col) {
		return cur_row >= 0 && cur_row < N && cur_col >= 0 && cur_col < M;
	}
}
