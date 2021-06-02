package s0529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 주어진 마법 시뮬레이션 이후, 총 폭발한 구슬의 개수를 구하라.
 * 문제 유형 : 시뮬레이션, 완전 탐색.
 * 주의 사항 : N의 크기가 최대 49인 것으로 보아, 완전 탐색이 가능하다.
 * <풀이 요약 : 시뮬레이션, 완전 탐색>
 * 1. 상어를 기준으로 소용돌이 모양 탐색 함수를 만든다(추출, 삽입에 사용).
 * 2. 블리자드 마법 함수를 만든다.
 * 3. 4개 이상의 구슬을 폭발 시키는 함수를 만든다. 이때, 폭발되는 구슬의 개수를 카운팅한다.
 * 4. 구슬의 그룹을 나누는 함수를 만든다.
 * 5. 2, 3, 4를 모든 마법이 시전될 때 까지 반복한다.
 * <피드백>
 * 1. 이 시뮬레이션 문제를 시간안에 푼다면 당신은 이미 삼성 피플~
 * 2. 문제 로직보다는, 빠른 디버깅 능력이 중요하다고 생각된다. 로직 자체가 굉장히 난해하고 복잡하기 때문에, 디테일한 부분을 생각하는 연습이 중요한듯.
 * 3. 큐, 스택, 덱과 같이 poll혹은 pop연산을 사용하는 경우는 "항상" 예외 처리를 해주어야한다. 그렇지 않으면 NullPointer Exception이 반겨줄 가능성이 높다.
 */

public class BOJ_G2_21611_마법사상어와블리자드 {
	static int N, M;
	static int[][] map;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Deque<Integer> ball;
	static Queue<Integer> result;
	// 최종 점수
	static int score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		score = 0;
		// 마법의 개수만큼 반복한다
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			blizzard(dir - 1, dis);
			// 소용돌이 탐색후 큐에 저장하는 함수
			search();
			// 연속된 4개의 구슬을 반복해서 터뜨리는 함수.
			bomb();
			// 마지막 -1 제거.
			ball.pollLast();
			if (!ball.isEmpty()) {
				// 구슬을 분류하는 함수.
				change();
				put();
			}
		}

		// 최종 점수
		System.out.println(score);
	}

	private static void put() {
		// 현재 맵 초기화
		map = new int[N][N];
		// 0이 아닌 맵의 값을 소용돌이 모양으로 탐색하면서 큐에 넣는다.
		int nr = (N - 1) / 2;
		int nc = (N - 1) / 2;
		// 한번 이동해야하는 칸수
		int move = 0;
		// 현재 이동 방향
		int dir = -1;
		// 서남동북
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { -1, 0, 1, 0 };
		// 덱이 빌때까지 반복
		while (true) {
			// 두번 방향을 전환하면, 이동 칸수 + 1.
			move++;
			for (int i = 0; i < 2; i++) {
				// 방향 전환
				dir++;
				if (dir == 4) {
					dir = 0;
				}
				// 이동 칸 수 만큼 이동하면서 맵에 큐의 값 저장.
				for (int j = 0; j < move; j++) {
					nr = nr + dr[dir];
					nc = nc + dc[dir];
					// 탐사가 맵 밖으로 벗어나기 전까지 반복.
					if (!checkBoundary(nr, nc)) {
						return;
					}
					// 해당 위치에 값을 큐에서 뽑아서 대입.
					map[nr][nc] = result.poll();
					// 덱이 전부 비었다면 함수 종료.
					if (result.isEmpty()) {
						return;
					}
				}
			}
		}
	}

	private static void change() {
		result = new LinkedList<Integer>();
		// 종료 구슬을 넣는다.
		ball.offer(-1);
		// 구슬의 수만큼 반복한다.
		int size = ball.size();
		int temp = ball.poll();
		int iter = 1;
		int cnt = 0;
		for (int i = 0; i < size - 1; i++) {
			// 다른 종류의 구슬이 뽑혔다면, 현재 누적된 구슬의 개수와 구슬의 종류를 덱에 넣고 반복되는 구슬의 개수를 1로 초기화한다.
			int cur = ball.poll();
			if (cur != temp) {
				result.offer(iter);
				cnt++;
				if (cnt >= N * N - 1) {
					return;
				}
				result.offer(temp);
				cnt++;
				if (cnt >= N * N - 1) {
					return;
				}
				iter = 1;
				temp = cur;
			}
			// 같은 종류의 구슬이 뽑혔다면, 누적된 구슬의 개수를 + 1한다.
			else {
				iter++;
			}
		}
	}

	private static void bomb() {
		// 큐에서 추출과 삽입을 반복하며 4개이상의 구슬을 제거한다.
		// 큐에서 같은 값이 4개 이상 나온다면, 해당 값을 기억해 두었다가 넣었던 값을 다시 제거한다.
		// 구슬이 제거된 적 있는지 파악.
		boolean flag = true;
		// 구슬이 제거되지 않는 경우까지 반복.
		// 마지막 구슬 분기점
		ball.offer(-1);
		while (flag) {
			int size = ball.size();
			// 반복되는 구슬의 개수
			int iter = 0;
			// 앞 구슬의 종류
			int temp = -1;
			flag = false;
			for (int i = 0; i < size; i++) {
				// 구슬을 뽑는다.
				int cur = ball.poll();
				// 만일, 앞 구슬과 뽑힌 구슬이 다르다면 반복되는 구슬의 개수를 1로 초기화한다.
				if (cur != temp || cur == -1) {
					// 이때, 반복되는 구슬의 개수가 4이상이라면 해당 숫자만큼 덱의 끝 부분의 구슬을 제거.
					if (iter >= 4) {
						flag = true;
						for (int j = 0; j < iter; j++) {
							ball.pollLast();
						}
						// 폭발한 점수 계산
						score = score + iter * temp;
					}
					iter = 1;
					temp = cur;
				}
				// 만일, 앞 구슬과 뽑힌 구슬이 같다면 반복되는 구슬의 개수를 + 1 한다.
				else {
					iter++;
				}
				// 구슬을 덱의 제일 마지막으로 옮긴다.
				ball.offer(cur);
			}
		}
	}

	private static void search() {
		ball = new ArrayDeque<Integer>();
		// 0이 아닌 맵의 값을 소용돌이 모양으로 탐색하면서 큐에 넣는다.
		int nr = (N - 1) / 2;
		int nc = (N - 1) / 2;
		// 한번 이동해야하는 칸수
		int move = 0;
		// 현재 이동 방향
		int dir = -1;
		// 서남동북
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { -1, 0, 1, 0 };
		while (true) {
			// 두번 방향을 전환하면, 이동 칸수 + 1.
			move++;
			for (int i = 0; i < 2; i++) {
				// 방향 전환
				dir++;
				if (dir == 4) {
					dir = 0;
				}
				// 이동 칸 수 만큼 이동하면서 큐에 값 저장
				for (int j = 0; j < move; j++) {
					nr = nr + dr[dir];
					nc = nc + dc[dir];
					// 탐사가 맵 밖으로 벗어나기 전까지 반복.
					if (!checkBoundary(nr, nc)) {
						return;
					}
					// 해당 위치의 값을 큐에 저장, 단 해당 값이 0이면 안됨.
					if (map[nr][nc] != 0) {
						ball.offer(map[nr][nc]);
					}
				}
			}
		}
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	// 블리자드
	private static void blizzard(int dir, int dis) {
		// 중앙으로부터 해당 방향과 거리만큼을 0으로 바꾼다.
		for (int i = 1; i <= dis; i++) {
			int nr = (N - 1) / 2 + i * dr[dir];
			int nc = (N - 1) / 2 + i * dc[dir];
			map[nr][nc] = 0;
		}
	}
}
