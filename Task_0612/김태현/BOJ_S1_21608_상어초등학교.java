package s0508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* (완)
 * <문제 요약>
 * 문제 정의 : 문제의 조건에 맞게 학생을 배치하고, 만족도를 계산해라.
 * 문제 유형 : (시뮬레이션, 우선순위 큐).
 * 주의 사항 : N의 크기로 보아 완전 탐색이 가능하다.
 * <풀이 요약 : 시뮬레이션, 우선순위 큐>
 * 1. 조건에 맞는 정보를 저장할 수 있는 클래스를 만든다.
 * 2. 조건에 맞는 정보를 도출할 수 있는 우선순위큐를 만든다. 
 * 3. 인접한 방향(4방향)의 선호 학생의 수, 빈 칸의 수, 위치를 계산하고 우선순위 큐에 저장한다.
 * 4. 학생을 배치한다. 2, 3, 4를 모든 학생에 대하여 반복한다.
 * 5. 선호도를 계산하고 출력한다.
 * <피드백>
 * 1. Comparator 문법 : Comparator<>(){}의 형태로 선언한다. new로 선언 해주어야 하며, 내부에는 compare 메서드를 오버라이딩해주어야 한다.
 */

public class BOJ_S1_21608_상어초등학교 {
	static int N;
	static int[][] map;
	static int[][] like;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	// 선호도 점수
	static int[] score = { 0, 1, 10, 100, 1000 };
	// 결과
	static int res;

	// 1. 조건에 맞는 정보를 저장할 수 있는 클래스를 만든다.
	static class Student {
		// 행, 열, 선호학생의 수, 빈 자리의 수(4방향).
		int r, c, favor, empty;

		Student(int r, int c, int favor, int empty) {
			this.r = r;
			this.c = c;
			this.favor = favor;
			this.empty = empty;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		like = new int[N * N + 1][4];
		res = 0;

		for (int i = 0; i < N * N; i++) {
			// 2. 조건에 맞는 정보를 도출할 수 있는 우선순위 큐를 만든다.
			PriorityQueue<Student> pq = new PriorityQueue<Student>(new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					// 인접한 학생의 수 내림차순.
					int a = Integer.compare(o2.favor, o1.favor);
					if (a == 0) {
						// 인접한 빈 자리의 수 내림차순.
						int b = Integer.compare(o2.empty, o1.empty);
						if (b == 0) {
							// 행 번호 오름차순.
							int c = Integer.compare(o1.r, o2.r);
							if (c == 0) {
								// 열 번호 오름차순.
								return Integer.compare(o1.c, o2.c);
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

			// 3. 학생의 수 만큼 선호 학생의 수, 빈 칸의 수, 위치를 계산하고 우선순위 큐에 저장한다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			for (int a = 0; a < 4; a++) {
				like[no][a] = Integer.parseInt(st.nextToken());
			}
			// 좌 상단부터 우 하단까지 모든 칸을 탐색한다(완전 탐색).
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < N; b++) {
					// 빈 자리가 아니라면 스킵한다.
					if (map[a][b] != 0) {
						continue;
					}
					int f = 0; // 선호 학생의 수.
					int e = 0; // 빈 자리의 수.
					// 상하좌우를 탐색한다.
					for (int c = 0; c < 4; c++) {
						int nr = a + dr[c];
						int nc = b + dc[c];
						// 단, 교실 범위 내에서만 탐색한다.
						if (checkBoundary(nr, nc)) {
							if (map[nr][nc] == 0) {
								e++;
							}
							for (int d = 0; d < 4; d++) {
								if (map[nr][nc] == like[no][d]) {
									f++;
									break;
								}
							}
						}
					}
					// 계산된 학생을 우선 순위 큐에 저장한다.
					pq.offer(new Student(a, b, f, e));
				}
			}

			// 4. 학생을 배치한다.
			Student s = pq.poll();
			map[s.r][s.c] = no;

		}
		// 5. 선호도를 계산한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int f = 0;
				// 상하좌우를 탐색한다.
				for (int c = 0; c < 4; c++) {
					int nr = i + dr[c];
					int nc = j + dc[c];
					// 단, 교실 범위 내에서만 탐색한다.
					if (checkBoundary(nr, nc)) {
						for (int d = 0; d < 4; d++) {
							if (map[nr][nc] == like[map[i][j]][d]) {
								f++;
								break;
							}
						}
					}
				}
				res += score[f];
			}
		}

		// 결과 출력
		System.out.println(res);
	}

	// 교실 범위 체크
	private static boolean checkBoundary(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
