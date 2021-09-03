package algo0904;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 문제 정의 : 마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 출력
 * 문제 유형 : 시뮬레이션
 * <풀이 요약>
 *  7 0 1
 *  6   2
 *  5 4 3
 *  1. 파이어볼 각자 이동
 *  2. 모든 이동 후, 2개 이상의 파이어볼이 있는 경우
 *   2-1. 같은 칸에 여러 개의 파이어볼이 있을 경우 합침
 *   2-2. 4개로 나누어짐
 *  	m: (합친 파이어볼의 질량 합) / 5
 *  	s: (속력의 합) / (파이어볼 개수)
 *  	d: 방향이 모두 홀수거나 짝수이면 (0, 2, 4, 6), 아니면 (1, 3, 5, 7)
 *   2-3. 질량이 0인 파이어볼은 소멸
 * <피드백>
 * 문제를 개그지같이 설명해놨네...
 * "1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결"를 이해하는데 시간이 걸렸음👎
 * "파이어볼은 4개의 파이어볼로 나누어진다."는 단순히 나누어 지는 것이고 이동하는 건 아님...🤦‍♀️
 */

public class BOJ_G5_20056_마법사상어와파이어볼 {

	static int N, M, K;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static Queue<Fireball> Q;
	static ArrayList<Fireball>[][] map;
	
	static class Fireball {
		int x, y, m, s, d;

		public Fireball(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Q = new LinkedList<>();
		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Q.add(new Fireball(x, y, m, s, d));
		}
		
		// K번 이동
		for (int i = 0; i < K; i++) {
			move();
			checkAfterMove();
			resetMap();
		}
		
		System.out.println(calculate());
	}

	// 1. 이동
	static void move() {
		while (!Q.isEmpty()) {
			Fireball cur = Q.poll();
			int x = cur.x;
			int y = cur.y;
			int m = cur.m;
			int s = cur.s;
			int d = cur.d;
			int nx = ((x + dx[d] * (s % N)) + N) % N;
			int ny = ((y + dy[d] * (s % N)) + N) % N;
			map[nx][ny].add(new Fireball(nx, ny, m, s, d)); 
		}
	}
	
	// 2. 전체 이동 후 파이어볼 확인
	static void checkAfterMove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int size = map[i][j].size();
				// 파이어볼이 없을 경우
				if (size == 0) continue;
				// 파이어볼이 하나만 있을 경우
				if (size == 1) {
					Q.add(map[i][j].get(0));
				}
				// 파이어볼이 여러 개일 경우
				else {
					int m = 0, s = 0;
					int odd = 0, even = 0;
					for (Fireball fb : map[i][j]) {
						m += fb.m;
						s += fb.s;
						if (fb.d % 2 == 0) ++even;
						else ++odd;
					}
					
					// 질량이 0인 파이어볼은 스킵
					if (m / 5 == 0) continue;
					
					if (odd == 0 || even == 0) divide(i, j, m / 5, s / size, 0);
					else divide(i, j, m / 5, s / size, 1);
				}
			}
		}
	}
	
	// 3. 같은 칸 파이어볼 여러 개일 경우 4개로 나눔
	static void divide(int x, int y, int m, int s, int isSame) {
		for (int i = isSame; i <= 6 + isSame; i = i + 2) {
			Q.add(new Fireball(x, y, m, s, i));
		}
	}
	
	// 4. 파이어볼의 질량의 합 구하기
	static int calculate() {
		int sum = 0;
		for (Fireball fb : Q) {
			sum += fb.m;
		}
		return sum;
	}
	
	// 초기화
	static void resetMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j].clear();
			}
		}
	}
}
