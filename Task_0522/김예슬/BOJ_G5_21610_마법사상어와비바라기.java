package algo0531;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 출력
 * 제약 사항 : 물의 양을 증가시킨 후 대각선 체크
 * 문제 유형 : 시뮬레이션
 * <풀이법 요약>
 * 1. M번 d방향으로 s칸 이동
 * 2. 비를 내림(각 바구니의 양 1 증가)
 * 3. 구름이 있는 곳에서 각 칸의 대각선 바구니의 값이 0이상인 바구니의 수만큼 물의 양 증가
 * 	3-1. 경계를 넘어가는 칸은 대각선X
 * 4. 구름 사라짐
 * 5. 바구니의 물의 양이 2 이상인 칸에 구름이 발생
 *  5-1. 방금 구름이 있었던 칸X
 */

public class BOJ_G5_21610_마법사상어와비바라기 {

	static int N, M;
	static int[][] map;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static ArrayList<Pos> cloud;
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		cloud = new ArrayList<>();
		
		for (int i = 0; i < N * N; i++)
			map[i / N][i % N] = sc.nextInt();
		
		// 비바라기 초기화
		cloud.add(new Pos(N - 1, 0));
		cloud.add(new Pos(N - 1, 1));
		cloud.add(new Pos(N - 2, 0));
		cloud.add(new Pos(N - 2, 1));
		
		// 이동
		for (int i = 0; i < M; i++) {
			int d = sc.nextInt() - 1;
			int s = sc.nextInt();
			move(d, s);
			rain();
			copybug();
			makeCloud();
		}
		
		// 출력
		System.out.println(count());
	}

	// 1. M번 d방향으로 s칸 이동
	static void move(int d, int s) {
		for (int i = 0; i < cloud.size(); i++) {
			Pos p = cloud.get(0);
			int nx = (N * s + p.x + dx[d] * s) % N;
			int ny = (N * s + p.y + dy[d] * s) % N;
			cloud.remove(0);
			cloud.add(new Pos(nx, ny));
		}
	}

	// 2. 비를 내림(각 바구니의 양 1 증가)
	static void rain() {
		for (Pos p : cloud)
			++map[p.x][p.y];
	}
	
	// 3. 구름이 있는 곳에서 각 칸의 대각선 바구니의 값이 0이상인 바구니의 수만큼 물의 양 증가
	static void copybug() {
		for (Pos p : cloud) {
			int cnt = 0;
			
			// 대각선 바구니의 값 체크
			for (int dir = 1; dir < 9; dir += 2) {
				int nx = p.x + dx[dir];
				int ny = p.y + dy[dir];
				
				// 3-1. 경계를 넘어가는 칸은 대각선X
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (map[nx][ny] == 0) continue;
				
				++cnt;
			}
			
			map[p.x][p.y] += cnt; 
		}
	}
	
	// 5. 바구니의 물의 양이 2 이상인 칸에 구름이 발생
	static void makeCloud() {
		ArrayList<Pos> newCloud = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean isCloudBefore = false;
				for (Pos p : cloud) {
					if (i == p.x && j == p.y) {
						isCloudBefore = true;
						break;
					}
				}
				
				// 방금 구름이 있었던 칸X
				if (isCloudBefore || map[i][j] < 2) continue;
				
				map[i][j] -= 2;
				newCloud.add(new Pos(i, j));
			}
		}
		
		cloud = newCloud;
	}
	
	static int count() {
		int sum = 0;
		for (int i = 0; i < N * N; i++)
			sum += map[i / N][i % N];
		return sum;
	}
}
