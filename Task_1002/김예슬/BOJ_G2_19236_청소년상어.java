package algo1002;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ����: �� ���� �� �ִ� ����� ��ȣ�� ���� �ִ��� ���
 * ���� ����: �ùķ��̼�, ��Ʈ��ŷ
 * <Ǯ�� ���>
 * �����(��ȣ[1~16], ����[�����¿�밢��])
 * ��, ��, ��, ��, ��, ��, ��, ��
 * 
 * 1. ����̵�
 *  1-1. ���� ĭ �̵�
 *  1-2. ���� ������� �������� ��ȯ
 *  1-3. ����Ⱑ ������ �̵� �Ұ����ϰ� ����
 * 2. ����� �̵�
 *  2-1. ����������� �� ĭ�� �̵�
 *  2-2. 45�� �ݽð� ���� ȸ���ϸ鼭 �̵� ������ ���� ã��
 *  2-3. �� �ְų� ��� ������ �̵� �Ұ���
 *  2-4. �̹� ����Ⱑ �ִ� ĭ�� ��� ������ ��ġ ��ȯ
*/

public class BOJ_G2_19236_û�ҳ��� {
	
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
		
		// ����� �̵�
		moveFish(map, F);
		
		// ��� ������ �� ���� �� �̵�(dfs)
		for (int d = 1; d < 4; d++) {
			int nx = shark.x + dx[shark.d] * d;
			int ny = shark.y + dy[shark.d] * d;
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if (map[nx][ny] == -1) continue;
			
			// -2: ���, -1: ��ĭ
			int[][] copy = copy(map);					// ����
			ArrayList<Fish> fish = new ArrayList<>();	// �̵� �� ����� ���� �迭
			F.forEach(f -> fish.add(new Fish(f.x, f.y, f.n, f.d)));	// ���̵� �� ��� ���� �迭
//			fish.addAll(F);								
			
			Shark s = new Shark(nx, ny, fish.get(copy[nx][ny]).d, shark.eat + copy[nx][ny] + 1);
			// ��Ƹ��� ������� ��ȣ -1�� ǥ��
			fish.get(map[nx][ny]).n = -1;
			// ����� ��ġ -2�� ǥ��
			copy[nx][ny] = -2;
			// �� �־��� ��ġ -1(��ĭ)�� ǥ��
			copy[shark.x][shark.y] = -1;
			// Ž��
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
				
				// ���� ĭ
				int curNum = map[nx][ny];
				
				// �� �ִ� ĭ
				if (curNum == -2) continue;
				
				// ����Ⱑ �ִ� ĭ
				if (curNum >= 0) {
					// ��ġ��ȯ
					exchange(map, f, F.get(curNum));
					
				// ��ĭ
				} else if (curNum == -1) {
					map[nx][ny] = f.n;			// �̵��� ĭ
					map[f.x][f.y] = -1;			// ���� ĭ
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
