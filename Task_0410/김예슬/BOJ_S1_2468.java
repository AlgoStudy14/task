package algo0404;

import java.util.Arrays;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 물에 잠기지 않는 안전한 영역의 최대 개수를 출력
 * 제약 사항 : 아무 지역도 물에 잠기지 않을 수 있음(=비가 오지 않을 경우ㅡㅡ)
 * 문제 유형 : DFS, 완탐으로 가능한 최대 개수를 찾음
 * <풀이법 요약>
 * 0. 비가 오지 않았을 경우 or 안전 영역의 개수가 최소일 경우 max는 1
 * 1. 어떤 지역의 최대 높이를 구함
 * 2. 최대 높이의 양만큼부터 비를 내려줌(maxHeight ~ 1)
 * 	2-1. 현재 비의 양만큼 비가 내렸을 경우 안전영역을 구함
 *  2-2. 비의 양을 바꿔서 체크할 때마다 visited 배열 초기화!!!
 * 3. 제일 처음을 제외하고 max 값이 현재 안전영역의 높이보다 작아질 경우 탐색을 멈춤
 */

public class BOJ_S1_2468 {

	static int N, maxHeight, max = 1;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N * N; i++) {
			int tmp = sc.nextInt();
			map[i / N][i % N] = tmp;
			maxHeight = Math.max(maxHeight, tmp);
		}
		
		// 가장 높은 지역의 높이 = 비의 양부터 체크 시작
		for (int r = maxHeight; r >= 1; r--) {
			int safeZone = submerge(r);
			if (max <= safeZone) max = safeZone;
			else break;								// 현재 비의 양일 때, 안전영역의 개수가 max보다 작을 경우 탐색 종료
			visitedInit();
		}
		
		System.out.println(max);
	}

	// 비의 양에 따른 안전영역
	static int submerge(int rain) {
		int safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= rain || visited[i][j]) continue;
				
				countSafeZone(i, j, rain);
				++safeZone;
			}
		}
		return safeZone;
	}
	
	// DFS로 안전영역 체크
	static void countSafeZone(int x, int y, int rain) {
		visited[x][y] = true;
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if (map[nx][ny] <= rain || visited[nx][ny]) continue;
			
			countSafeZone(nx, ny, rain);
		}
	}
	
	// visited 배열 초기화
	static void visitedInit() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}
}
