package algo0918;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의: 남아있는 얼음의 합과 가장 큰 얼음이 존재하는 칸의 개수를 출력(없다면 0)
 * 문제 유형: 시뮬레이션, BFS
 * <풀이 요약>
 * 1. rotate: (2^L x 2^L) 부분 시계 방향으로 90도 회전
 * 2. melt: 주위에 얼음이 있는 칸이 3개 이하면 얼음의 양 1 감소
 *  2-1. canMelt: 녹일 칸을 전부 체크 해준 뒤 녹여야 됨
 * 3. sum: 남아있는 얼음의 합 구하기
 * 4. maxIceArea: 가장 덩어리가 큰 얼음의 칸의 개수를 구하기
 *  4-1. bfs: bfs를 사용해 덩어리 크기 체크
 *  4-2. bfs를 사용할 때 visited 배열을 따로 사용하지 않고 이미 방문한 칸은 -1로 표시
*/

public class BOJ_G4_20058_마법사상어와파이어스톰 {

	static int N, Q;
	static int[][] map;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static ArrayList<Pos> melted;
	
	public static void main(String[] args) throws Exception {
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
		
		// 파이어스톰 실행
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			rotate(L);
			melt();
		}
		
		System.out.println(sum());
		System.out.println(maxIceArea());
	}

	static void rotate(int L) {
		int smallSquare = (int) Math.pow(2, L);
		int[][] tmp = new int[N][N];
		
		for (int i = 0; i < N; i += smallSquare) {
			for (int j = 0; j < N; j += smallSquare) {
				// 내부에서 90도 회전
				for (int r = 0; r < smallSquare; r++) {
					for (int c = 0; c < smallSquare; c++) {
						tmp[i + c][j + smallSquare - r - 1] = map[i + r][j + c];
					}
				}
			}
		}
		
		map = tmp;
	}
	
	static void melt() {
		// 녹여야 되는 칸을 체크 후 녹여야 함
		melted = new ArrayList<>();
		canMelt();
		
		// 녹이기
		for (Pos p : melted) {
			--map[p.x][p.y];
		}
	}
	
	static void canMelt() {
		// 모든 칸에 대해 녹여야 되는 칸을 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;
				
				int cnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];
					
					if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
					if (map[nx][ny] == 0) continue;
					
					++cnt;
				}
				 
				// 주위의 칸에 얼음이 3개 이상 없다면 녹여야 함
				if (cnt < 3) melted.add(new Pos(i, j));
			}
		}
	}
	
	static int sum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	static int maxIceArea() {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 || map[i][j] == -1) continue;
				
				max = Math.max(max, bfs(i, j));
			}
		}
		return max;
	}
	
	static int bfs(int x, int y) {
		Queue<Pos> Q = new LinkedList<>();
		Q.add(new Pos(x, y));
		map[x][y] = -1;

		int cnt = 1;
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (map[nx][ny] == 0 || map[nx][ny] == -1) continue;
				
				Q.add(new Pos(nx, ny));
				map[nx][ny] = -1;
				++cnt;
			}
		}
		return cnt;
	}
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
