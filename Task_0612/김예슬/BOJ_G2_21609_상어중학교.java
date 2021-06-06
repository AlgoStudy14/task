package algo0509;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 오토 플레이가 모두 끝났을 때 획득한 점수의 합을 출력
 * 제약 사항 : 크기 大 > 무지개 多 > 행 大 > 열 大
 * 문제 유형 : bfs를 사용한 시뮬레이션..?
 * <풀이법 요약>
 * 0. 빈칸 -2으로 저장
 * 1. bfs/dfs로 큰 블록 그룹 찾기
 *  1-1. 검은 블록(-1) X, 무지개 블록(0) O
 *  1-2. 일반 블록이 적어도 하나 이상, 그룹의 크기는 2이상
 *  1-3. 그룹의 크기가 최대인 곳의 좌표와 사이즈 저장
 * 2. 점수 획득
 * 3. 블록제거 후 중력
 * 4. 90도 회전 후 중력
 */

public class BOJ_G2_21609_상어중학교 {

	static int N, M, rainbowCnt, score;
	static int[][] map;
	static PriorityQueue<Pos> big;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static class Pos implements Comparable<Pos> {
		int x, y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Pos p) {
			int tmp = map[this.x][this.y] - map[p.x][p.y];
			if (tmp != 0) return -tmp;
			else {
				tmp = this.x - p.x;
				if (tmp != 0) return tmp;
				else return this.y - p.y;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		
		for (int i = 0; i < N * N; i++)
			map[i / N][i % N] = sc.nextInt();
		
		int dir = 0;
		while (true) {
			find(new boolean[N][N]);

			// 그룹의 크기가 2보다 작을 경우
			int size = big.size();
			if (size < 2) break;
			
			// 점수 획득
			System.out.println(size * size);
			score += size * size;

			// 블록제거 후 중력
			remove();
			
			System.out.println("===========>제거");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.printf("%3d ",map[i][j]);
				}
				System.out.println();
			}
			gravity(dir);
			
			System.out.println("===========>중력1=>>"+dir);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.printf("%3d ",map[i][j]);
				}
				System.out.println();
			}
			// 90도 회전 후 중력
			dir = (dir + 1) % 4;
			gravity(dir);
			
			System.out.println("===========>중력2=>>"+dir);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.printf("%3d ",map[i][j]);
				}
				System.out.println();
			}
		}
		
		System.out.println(score);
	}

	// 그룹의 크기가 최대인 곳 찾기
	static void find(boolean[][] noNeedToCheck) {
		big = new PriorityQueue<>();
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 자동으로 일반 블록이 하나 이상 포함되도록
				if (map[i][j] <= 0 || noNeedToCheck[i][j]) continue;

				bfs(i, j, max, noNeedToCheck);
			}
		}
	}
	
	static void bfs(int x, int y, int max, boolean[][] noNeedToCheck) {
		int standard = map[x][y];
		int tmpRainbowCnt = 0;
		
		Queue<Pos> Q = new LinkedList<>();
		Q.add(new Pos(x, y));
		
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		
		PriorityQueue<Pos> tmp = new PriorityQueue<>();
		tmp.add(new Pos(x, y));
		
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				
				int now = map[nx][ny];
				if (now != standard && now != 0) continue;

				visited[nx][ny] = true;
				if (now == 0) noNeedToCheck[nx][ny] = true;
				Q.add(new Pos(nx, ny));
				if (now == 0) ++tmpRainbowCnt;
				tmp.add(new Pos(nx, ny));
			}
		}
		
		// 블록의 크기가 2이상인 경우만
		if (tmp.size() >= 2) {
			if (big.size() == tmp.size()) {
				if (rainbowCnt < tmpRainbowCnt) {
					rainbowCnt = tmpRainbowCnt;
					big = tmp;
				} else if (rainbowCnt == tmpRainbowCnt) {
					Pos bigS = big.peek();
					Pos tmpS = tmp.peek();
					
					if (bigS.x < tmpS.x) big = tmp;
					else if (bigS.x == tmpS.x && bigS.y < tmpS.y) 
						big = tmp;
				}
			} else if (big.size() < tmp.size()) {
				big = tmp;
				rainbowCnt = tmpRainbowCnt;
			}
		}
	}
	
	// 삭제
	static void remove() {
		for (Pos p : big)
			map[p.x][p.y] = -2;
	}
	
	// x y x y
	// 중력(회전방향) ↓ ←- ↑ -→
	static void gravity(int dir) {
		switch (dir) {
		case 0:
			for (int i = N - 2; i >= 0; i--) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] < 0) continue;
					
					int block = map[i][j];
					
					int nx = i;
					while (true) {
						++nx;
						if (nx == N) break;
						if (map[nx][j] != -2) break;
					}
					
					map[i][j] = -2;
					map[--nx][j] = block;
				}
			}
			break;
		case 1:
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[j][i] < 0) continue;
					
					int block = map[j][i];
					
					int nx = i;
					while (true) {
						--nx;
						if (nx == -1) break;
						if (map[j][nx] != -2) break;
					}
					
					map[j][i] = -2;
					map[j][++nx] = block;
				}
			}	
			break;
		case 2:
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] < 0) continue;
					
					int block = map[i][j];
					
					int nx = i;
					while (true) {
						--nx;
						if (nx == -1) break;
						if (map[nx][j] != -2) break;
					}
					
					map[i][j] = -2;
					map[++nx][j] = block;
				}
			}
			break;
		case 3:
			for (int i = N - 2; i >= 0; i--) {
				for (int j = 0; j < N; j++) {
					if (map[j][i] < 0) continue;
					
					int block = map[j][i];
					
					int nx = i;
					while (true) {
						++nx;
						if (nx == N) break;
						if (map[j][nx] != -2) break;
					}
					
					map[j][i] = -2;
					map[j][--nx] = block;
				}
			}			
			break;
		}
	}
}
