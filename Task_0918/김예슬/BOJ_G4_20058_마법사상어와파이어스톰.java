package algo0918;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ����: �����ִ� ������ �հ� ���� ū ������ �����ϴ� ĭ�� ������ ���(���ٸ� 0)
 * ���� ����: �ùķ��̼�, BFS
 * <Ǯ�� ���>
 * 1. rotate: (2^L x 2^L) �κ� �ð� �������� 90�� ȸ��
 * 2. melt: ������ ������ �ִ� ĭ�� 3�� ���ϸ� ������ �� 1 ����
 *  2-1. canMelt: ���� ĭ�� ���� üũ ���� �� �쿩�� ��
 * 3. sum: �����ִ� ������ �� ���ϱ�
 * 4. maxIceArea: ���� ����� ū ������ ĭ�� ������ ���ϱ�
 *  4-1. bfs: bfs�� ����� ��� ũ�� üũ
 *  4-2. bfs�� ����� �� visited �迭�� ���� ������� �ʰ� �̹� �湮�� ĭ�� -1�� ǥ��
*/

public class BOJ_G4_20058_������������̾�� {

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
		
		// ���̾�� ����
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
				// ���ο��� 90�� ȸ��
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
		// �쿩�� �Ǵ� ĭ�� üũ �� �쿩�� ��
		melted = new ArrayList<>();
		canMelt();
		
		// ���̱�
		for (Pos p : melted) {
			--map[p.x][p.y];
		}
	}
	
	static void canMelt() {
		// ��� ĭ�� ���� �쿩�� �Ǵ� ĭ�� üũ
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
				 
				// ������ ĭ�� ������ 3�� �̻� ���ٸ� �쿩�� ��
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
