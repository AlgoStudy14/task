import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_20056_������_����_���̾ {

	static class FireBall {
		int x;
		int y;
		int m;
		int s;
		int d;

		public FireBall(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}

	static int N, M, K;
	static ArrayList<FireBall>[][] map;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static Queue<FireBall> ballList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		ballList = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			ballList.add(new FireBall(x, y, m, s, d));
		}

		for (int i = 0; i < K; i++) {
			ballMove();
			checkMap();
			clearMap();
		}

		System.out.println(solve());
	}

	private static int solve() {
		int answer = 0;
		for (FireBall ball : ballList) {
			answer += ball.m;
		}
		return answer;
	}

	// ball �̵�
	private static void ballMove() {
		while (!ballList.isEmpty()) {
			FireBall ball = ballList.poll();
			int nx = (N + ball.x + dx[ball.d] * (ball.s % N)) % N;
			int ny = (N + ball.y + dy[ball.d] * (ball.s % N)) % N;
			map[nx][ny].add(new FireBall(nx, ny, ball.m, ball.s, ball.d));
		}
	}

	// �̵� �� Map Ȯ��
	private static void checkMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = map[i][j].size();
				// ball 0��
				if (size == 0) {
					continue;
				} else if (size == 1) { // ball 1��
					ballList.add(map[i][j].get(0));
				} else { // ball 2�� �̻�
					int sumM = 0, sumS = 0, cntD = size; // ¦���� �� ���ֱ�
					for (FireBall ball : map[i][j]) {
						sumM += ball.m;
						sumS += ball.s;
						if (ball.d % 2 == 0) {
							cntD--;
						}
					}
					// ������ 0�� �� �Ҹ�
					if (sumM / 5 == 0) {
						continue;
					}
					// ��� ¦���̰ų� Ȧ���� ��
					if (cntD == 0 || cntD == size) {
						for (int k = 0; k < 8; k += 2) {
							ballList.add(new FireBall(i, j, sumM / 5, sumS / size, k));
						}
					} else { // ��� ���� ���� ��
						for (int k = 1; k < 8; k += 2) {
							ballList.add(new FireBall(i, j, sumM / 5, sumS / size, k));
						}
					}
				}
			}
		}
	}

	// Map �ʱ�ȭ
	private static void clearMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j].clear();
			}
		}
	}
}
