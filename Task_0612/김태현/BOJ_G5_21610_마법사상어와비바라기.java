package s0522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 주어진 시뮬레이션에 맞게 바구니에 물을 채우고, 최종 물의 양을 계산해라.
 * 문제 유형 : 시뮬레이션, 완전 탐색.
 * 주의 사항 : N의 크기가 최대 50인걸로 보아 완전 탐색이 가능하다.
 * <풀이 요약 : 시뮬레이션, 완전 탐색>
 * 1. 구름의 현재 위치를 저장할 배열을 만든다(구름의 수는 유동하기 때문에 ArrayList로 만들기).
 * 2. 구름의 이동과 물의 양을 증가시키는 함수를 만든다.
 * 3. 물 복사 마법을 시전할 함수를 만든다.
 * 4. M번의 이동만큼 2번과 3번을 반복한다.
 * 5. 최종 물의 양의 합을 구한다.
 * <피드백>
 * 자료구조를 정할 때, 정적인 것으로 할지 동적인 것으로 할지는 문제를 자세히 읽어보면 선택할 수 있다. 반복되는 실수를 줄이자.
 */

public class BOJ_G5_21610_마법사상어와비바라기 {
	static int N, M;
	static int[][] map;
	static ArrayList<int[]> cloud;
	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

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
		// 초기 구름의 위치.
		cloud = new ArrayList<int[]>();
		cloud.add(new int[] { N - 1, 0 });
		cloud.add(new int[] { N - 1, 1 });
		cloud.add(new int[] { N - 2, 0 });
		cloud.add(new int[] { N - 2, 1 });

		// M번의 이동만큼 반복한다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			// 비바라기
			rain(dir, dis);
			// 물 복사 버그
			copy();
			// 다음 구름의 좌표 갱신
			check();
		}
		// 전체 물의 양의 합 구하기
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
	}

	private static void check() {
		int before = cloud.size();
		// 모든 맵을 탐색하며 물이 2이상인 곳을 찾고, 해당 위치를 cloud에 추가한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2) {
					// 단, 해당 위치가 이미 존재하던 좌표와 중복되면 안된다.
					boolean flag = true;
					for (int a = 0; a < before; a++) {
						if (cloud.get(a)[0] == i && cloud.get(a)[1] == j) {
							flag = false;
							break;
						}
					}
					if (flag) {
						cloud.add(new int[] { i, j });
						map[i][j] -= 2;
					}
				}
			}
		}
		// 이미 존재하던 cloud의 좌표를 제거한다.
		for (int i = 0; i < before; i++) {
			cloud.remove(0);
		}
	}

	private static void copy() {
		// 모든 구름의 좌표에 대하여 반복한다.
		for (int i = 0; i < cloud.size(); i++) {
			// 인접한 4개의 대각선에 대하여 물이 있는 바구니의 수를 구한다.
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				int r = cloud.get(i)[0] + dr[2 * j + 1];
				int c = cloud.get(i)[1] + dc[2 * j + 1];
				if (checkBoundary(r, c) && map[r][c] > 0) {
					cnt++;
				}
			}
			// 해당 바구니의 물의 양을 증가시킨다.
			map[cloud.get(i)[0]][cloud.get(i)[1]] += cnt;
		}
	}

	private static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static void rain(int dir, int dis) {
		// 모든 구름의 좌표에 대하여 반복한다.
		for (int i = 0; i < cloud.size(); i++) {
			// 좌표를 이동시킨다.
			int r = cloud.get(i)[0] + dis * dr[dir - 1];
			int c = cloud.get(i)[1] + dis * dc[dir - 1];
			// N을 넘어가면 적절히 좌표를 조정한다.
			while (r >= N) {
				r = r - N;
			}
			while (c >= N) {
				c = c - N;
			}
			// 0 아래로 넘어가면 적절히 좌표를 조정한다.
			while (r < 0) {
				r = N + r;
			}
			while (c < 0) {
				c = N + c;
			}
			cloud.set(i, new int[] { r, c });
			// 각 칸의 물의 양을 1 증가시킨다.
			map[r][c]++;
		}
	}
}
