package s0515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 게임을 시뮬레이션하면서 얻은 최종 점수를 구하라.
 * 문제 유형 : 시뮬레이션, BFS.
 * 주의 사항 : N의 크기가 최대 20인걸로보아, 완전 탐색이 가능하다.
 * <풀이 요약 : 시뮬레이션, BFS>
 * 1. 저장된 블록 그룹은 우선 순위큐로 조건에 맞게 구현한다.
 * 2. BFS를 이용하여 블록 그룹을 저장한다. 이때, 시뮬레이션 조건에 맞게 모든 정보를 기억한다.
 * 3. 선정된 블록을 제거하고, 중력 함수를 호출한다.
 * -> 중력 함수는 각 열에 대해서 탐색을 진행하였다.
 * -> 가장 아래 행부터, 빈 공간이라면 가장 가까운 검정색 블록이 아닌 블록과 위치를 교환하는 로직을 사용했다.
 * 4. 격자를 반 시계 방향으로 회전하고, 중력 함수를 호출한다.
 * -> 반 시계 방향 회전은 인덱스 번호만 잘 활용하면 된다.
 * <주의 사항>
 * 아무리 생각해도 틀린 점이 없다면 눈을 크게 뜨고 조건을 다시 천천히 읽어보자... 슈발..
 */

public class BOJ_G2_21609_상어중학교 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int score;
	static PriorityQueue<Block> pq;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Block {
		int tot;
		int rainbow;
		int r, c;

		Block(int tot, int rainbow, int r, int c) {
			this.tot = tot;
			this.rainbow = rainbow;
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		// 입력
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

		// 블록 그룹이 존재하지 않을 때 까지 반복한다.
		while (true) {
			// 1. 조건에 맞는 우선순위 큐를 구현한다.
			pq = new PriorityQueue<Block>(new Comparator<Block>() {
				@Override
				public int compare(Block o1, Block o2) {
					// 블록의 개수 내림차순
					int a = Integer.compare(o2.tot, o1.tot);
					if (a == 0) {
						// 무지개 블록의 개수 내림차순
						int b = Integer.compare(o2.rainbow, o1.rainbow);
						if (b == 0) {
							// 행 내림 차순
							int c = Integer.compare(o2.r, o1.r);
							if (c == 0) {
								// 열 내림 차순
								return Integer.compare(o2.c, o1.c);
							} else {
								return c;
							}
						} else {
							return b;
						}
					} else {
						return a;
					}
				}
			});

			// 2. BFS를 이용하여 블록 그룹을 저장한다.
			// 전체 맵을 방문하면서 넘버링한다.
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 방문한 적 없고, 무지개 색이 아니고, 벽돌이 아니고, 빈 공간이 아니라면
					if (!visited[i][j] && map[i][j] > 0) {
						numbering(i, j);
					}
				}
			}

			// 우선순위 큐에 저장된 블록이 없으면 시뮬레이션 종료
			if (pq.isEmpty()) {
				break;
			}

			// 3. 선정된 블록을 제거하고, 중력 함수를 호출한다.
			Block b = pq.poll();
			
			// 점수 올리기
			score += (b.tot) * (b.tot);
			// 블록 제거(-2로 표시)
			bfs(b);
			// 중력 함수
			down();
			// 4. 격자를 반 시계 방향으로 회전하고, 중력 함수를 호출한다.
			// 반 시계 회전
			rotate();
			// 중력 함수
			down();
		}
		System.out.println(score);
	}

	private static void rotate() {
		int[][] temp = new int[N][N];
		// 열의 끝번호를 행의 시작번호로 대응 시키기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[j][i] = map[i][N - j - 1];
			}
		}
		// map 변환
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}

	private static void down() {
		// 각 열에 대해서 진행한다.
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j > 0; j--) {
				// 가장 아래 블럭부터 생각한다. 우선, 해당 공간이 빈 공간(-2)인지 판단한다.
				if (map[j][i] == -2) {
					// 그렇다면, 맵을 벗어나거나 검은 블럭을 만날때까지 위쪽의 블럭을 탐색한다.
					int idx = j - 1;
					while (idx > -1 && map[idx][i] != -1) {
						// 만일, 블럭을 만난다면 해당 블럭과 빈공간의 위치 교체하고 반복문 탈출.
						if (map[idx][i] != -2) {
							int temp = map[idx][i];
							map[idx][i] = map[j][i];
							map[j][i] = temp;
							break;
						}
						// 그게 아니라면 인덱스 - 1
						else {
							idx--;
						}
					}
				}
			}
		}
	}

	private static void bfs(Block b) {
		// 초기 블록의 색 기억하기
		int color = map[b.r][b.c];
		// bfs를 이용하여 같은 색과 무지개 색 제거하기
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { b.r, b.c });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 범위 내에 있고, 방문한 적 없으며, 같은 색이거나, 무지개 색이면 블록 제거하기
				if (checkBoundary(nr, nc) && map[nr][nc] != -2 && (map[nr][nc] == color || map[nr][nc] == 0)) {
					map[nr][nc] = -2;
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

	private static void numbering(int row, int col) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { row, col });
		int tot = 1;
		int rainbow = 0;
		int minR = row;
		int minC = col;
		// 임시 방문 배열
		boolean[][] check = new boolean[N][N];
		check[row][col] = true;
		visited[row][col] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				// 범위 체크, 방문하지 않았고, 같은 색이거나, 무지개 색이라면
				if (checkBoundary(nr, nc) && !check[nr][nc] && (map[row][col] == map[nr][nc] || map[nr][nc] == 0)) {
					// 무지개 색이라면, 무지개 색의 개수 + 1만 하기.
					if (map[nr][nc] == 0) {
						rainbow++;
					}
					// 무지개 색이 아니라면, 방문 처리 및 가장 큰 행과 가장 작은 열 갱신
					else {
						// 전체 방문처리(무지개 색 제외)
						visited[nr][nc] = true;
						// 가장 큰 행, 가장 작은 열
						if (minR > nr) {
							minR = nr;
							minC = nc;
						} else if (minR == nr) {
							if (minC > nc) {
								minC = nc;
							}
						}
					}
					// 현 블록 방문 처리
					check[nr][nc] = true;
					// 총 블럭의 개수 + 1
					tot++;
					// 큐에 다음 블럭 넣기
					q.offer(new int[] { nr, nc });
				}
			}
		}
		// 우선순위 큐에 블록 정보 넣기. 단, 블록의 개수가 2개 이상인 경우만 넣기.
		if (tot >= 2) {
			pq.offer(new Block(tot, rainbow, minR, minC));
		}
	}

	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
