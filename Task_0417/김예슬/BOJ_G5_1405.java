package algo0413;

import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 로봇의 이동 경로가 단순할 확률을 출력
 * 제약 사항 : 
 * 문제 유형 : DFS
 * <풀이법 요약>
 * 0. visited: 방문한 곳 체크(N이 14이므로 14*2=>대충 30)
 * 1. DFS로 탐색 시작
 * 2. 단순하지 않은 경우를 구함
 *  2-1. 이미 방문했던 곳을 방문할 경우, 탐색을 멈추고  notSimple에 단순하지 않은 확률을 더함
 */

public class BOJ_G5_1405 {

	static int N;
	static double notSimple;
	static boolean[][] visited = new boolean[30][30];
	static double[] cd = new double[4];
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 0; i < 4; i++)
			cd[i] = sc.nextDouble() * 0.01;
		
		solve(0, 15, 15, 1);
		
		System.out.println(1 - notSimple);
	}

	static void solve(int depth, int x, int y, double ratio) {
		if (depth == N) return;
		
		visited[x][y] = true;
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (visited[nx][ny]) {
				notSimple += ratio * cd[dir];
				continue;
			}
			
			solve(depth + 1, nx, ny, ratio * cd[dir]);
			visited[nx][ny] = false;
		}
	}
}
