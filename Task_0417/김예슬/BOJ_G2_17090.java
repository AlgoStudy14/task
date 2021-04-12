package algo0410;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 구해야 하는 것 : 로봇의 이동 경로가 단순할 확률을 출력
 * 문제 유형 : DFS, 백트랙킹
 * <풀이법 요약>
 * 0. arr: 미로, able: 탈출 가능한지 여부 저장
 * 1. 각 칸마다 탈출할 수 있는지 체크(solve)
 * 2. 처음 방문할 경우, 탈출이 가능한지 저장함
 *  2-1. 이미 방문했던 칸은 탈출여부가 저장되어 있으므로 탐색을 줄일 수 있음
 *  2-2. 
 *   	0: 아직 방문X
 *   	1: 탈출 가능(배열을 벗어날 경우)
 *     -1: 탈출 불가능(이미 방문한 경우)
 *   	2: 탈출 가능한지 체크중
 * c.f) 처음에 탈출 여부를 저장하면서 코드를 작성했지만 시간초과 -> 좀 더 효율적으로 코드 작성 필요
 */

public class BOJ_G2_17090 {
	
	static int N, M;
	static char[][] arr;
	static int[][] able;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()) ;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		able = new int[N][M];
		
		for (int i = 0; i < N; i++)
			arr[i] = br.readLine().toCharArray();
		
		// 탐색 시작
		solve();
		
		// 탈출가능한 칸의 수를 계산
		int cnt = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (able[i][j] == 1) ++cnt;

		// 탈출가능한 칸의 수를 출력
		System.out.println(cnt);
	}

	static void solve() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) 
				if (able[i][j] == 0) escape(i, j);
	}
	
	// 탈출할 수 있는지 체크(아직방문X: 0, 탈출가능: 1, 탈출불가능: -1, 체크중: 2)
	static int escape(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M || able[x][y] == 1) return 1;
		if (able[x][y] == -1 || able[x][y] == 2) return -1;
		
		able[x][y] = 2;
		
		int dir = getDirection(arr[x][y]);
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		able[x][y] = escape(nx, ny);
		return able[x][y];
	}
	
	// 현재 칸에서 이동하는 방향
	static int getDirection(char c) {
		if (c == 'U') return 0;
		else if (c == 'R') return 1;
		else if (c == 'D') return 2;
		else if (c == 'L') return 3;
		return 4;
	}
}
