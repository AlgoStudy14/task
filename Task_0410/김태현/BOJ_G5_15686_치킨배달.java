package s0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 유형>
 * 요구 하는 것 : 도시의 치킨 거리의 최소 값.
 * 제한 조건 : 선택 가능한 치킨 집은 최대 M개로 주어진다.
 * 문제 유형 : 조합, 맨해튼 거리.
 * <풀이법>
 * 1. 현재 존재하는 치킨 집에서 최대 개수만큼 치킨집을 선택하는 조합을 구한다.
 * 2. 각 조합마다 각 집까지 맨해튼 거리를 구하며 최소값을 갱신한다.
 */
public class BOJ_G5_15686_치킨배달 {
	static int N, M;
	static int[][] map;
	static int[][] home;
	static int[][] chicken;
	static boolean[] selected;
	static int h_cnt, c_cnt;
	static int min;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		home = new int[2 * N][2];
		chicken = new int[13][2];
		selected = new boolean[13];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 집과 치킨 집 위치는 따로 저장해두기(조합으로 선택하기 위해서).
				if (map[i][j] == 1) {
					home[h_cnt++] = new int[] { i, j };
				} else if (map[i][j] == 2) {
					chicken[c_cnt++] = new int[] { i, j };
				}
			}
		}
		min = Integer.MAX_VALUE;

		// 조합을 이용하여 최대 선택 가능한 치킨집 수 만큼 조합 경우를 구한다.
		Combination(0, 0);

		System.out.println(min);
	}

	private static void Combination(int cnt, int idx) {
		// 최대 치킨집의 수 만큼 선택했으면, 도시의 치킨 거리 구해보기.
		if (cnt == M) {
			count();
			return;
		}
		// 최대 치킨집의 개수 만큼 반복
		for (int i = idx; i < c_cnt; i++) {
			if (!selected[i]) {
				selected[i] = true;
				Combination(cnt + 1, i + 1);
				selected[i] = false;
			}
		}
	}

	private static void count() {
		// 각 집에서 각 치킨 집까지의 거리의 최소값을 선택하며 총 합을 구한다.
		int sum = 0;
		for (int i = 0; i < h_cnt; i++) {
			int[] cur_home = home[i];
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < c_cnt; j++) {
				if (selected[j]) {
					int[] cur_chicken = chicken[j];
					min = Math.min(min,
							Math.abs(cur_home[0] - cur_chicken[0]) + Math.abs(cur_home[1] - cur_chicken[1]));
				}
			}
			sum += min;
		}
		min = Math.min(min, sum);
	}
}
