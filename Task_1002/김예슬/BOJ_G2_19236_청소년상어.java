package algo1002;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의: 상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 출력
 * 문제 유형: 시뮬레이션, 백트랙킹
 * <풀이 요약>
 * 물고기(번호[1~16], 방향[상하좌우대각선])
 * ↑, ↖, ←, ↙, ↓, ↘, →, ↗
 * 
 * 1. 상어이동
 *  1-1. 여러 칸 이동
 *  1-2. 먹은 물고기의 방향으로 전환
 *  1-3. 물고기가 없으면 이동 불가능하고 삭제
 * 2. 물고기 이동
 *  2-1. 오름차순대로 한 칸씩 이동
 *  2-2. 45도 반시계 방향 회전하면서 이동 가능한 방향 찾음
 *  2-3. 상어가 있거나 경계 밖으로 이동 불가능
 *  2-4. 이미 물고기가 있는 칸일 경우 서로의 위치 교환
*/

public class BOJ_G2_19236_청소년상어 {
	
	static int N = 4, max;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[][] map = new int[N][N];
		ArrayList<Fish> F = new ArrayList<>();
		Shark shark;
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int n = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken()) - 1;
				F.add(new Fish(i, j, n, d));
				map[i][j] = n;
			}
		}
		
		Collections.sort(F);
		
		shark = new Shark(0, 0, F.get(map[0][0]).d, F.get(map[0][0]).n + 1);
		F.get(map[0][0]).n = -1;
		map[0][0] = -2;

		dfs(map, F, shark);
		
		System.out.println(max);
	}
	
	static void dfs(int[][] map, ArrayList<Fish> F, Shark shark) {
		max = Math.max(max, shark.eat);
		
		// 물고기 이동
		moveFish(map, F);
		
		// 상어 가능한 곳 여러 번 이동(dfs)
		for (int d = 1; d < 4; d++) {
			int nx = shark.x + dx[shark.d] * d;
			int ny = shark.y + dy[shark.d] * d;
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if (map[nx][ny] == -1) continue;
			
			// -2: 상어, -1: 빈칸
			int[][] copy = copy(map);					// 공간
			ArrayList<Fish> fish = new ArrayList<>();	// 이동 후 물고기 담을 배열
			F.forEach(f -> fish.add(new Fish(f.x, f.y, f.n, f.d)));	// ↓이동 후 상어 담을 배열
//			fish.addAll(F);								
			
			Shark s = new Shark(nx, ny, fish.get(copy[nx][ny]).d, shark.eat + copy[nx][ny] + 1);
			// 잡아먹힌 물고기의 번호 -1로 표시
			fish.get(map[nx][ny]).n = -1;
			// 상어의 위치 -2로 표시
			copy[nx][ny] = -2;
			// 상어가 있었던 위치 -1(빈칸)로 표시
			copy[shark.x][shark.y] = -1;
			// 탐색
			dfs(copy, fish, s);
		}
	}
	
	static void moveFish(int[][] map, ArrayList<Fish> F) {
		for (Fish f : F) {
			if (f.n == -1) continue;
			
			for (int i = 0; i < 8; i++) {
				int dir = (f.d + i) % 8;
				int nx = f.x + dx[dir];
				int ny = f.y + dy[dir];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				// 다음 칸
				int curNum = map[nx][ny];
				
				// 상어가 있는 칸
				if (curNum == -2) continue;
				
				// 물고기가 있는 칸
				if (curNum >= 0) {
					// 위치교환
					exchange(map, f, F.get(curNum));
					
				// 빈칸
				} else if (curNum == -1) {
					map[nx][ny] = f.n;			// 이동할 칸
					map[f.x][f.y] = -1;			// 현재 칸
					f.x = nx;
					f.y = ny;
				}
				
				f.d = dir;
				break;
			}
			
		}
	}
	
	static void exchange(int[][] map, Fish f1, Fish f2) {
		int tn = f1.n;
		int tx = f1.x;
		int ty = f1.y;

		map[f1.x][f1.y] = map[f2.x][f2.y];
		f1.x = f2.x;
		f1.y = f2.y;
		
		map[f2.x][f2.y] = tn;
		f2.x = tx;
		f2.y = ty;
	}
	
	static int[][] copy(int[][] map) {
		int[][] copy = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	static class Shark {
		int x, y, d, eat;

		public Shark(int x, int y, int d, int eat) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.eat = eat;
		}
	}
	
	static class Fish implements Comparable<Fish> {
		int x, y, n, d;
	
		public Fish(int x, int y, int n, int d) {
			this.x = x;
			this.y = y;
			this.n = n;
			this.d = d;
		}

		@Override
		public int compareTo(Fish f) {
			return this.n - f.n;
		}
	}
}
