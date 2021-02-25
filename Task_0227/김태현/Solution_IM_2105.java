package algo0225;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 규칙에 따라 적절히 움직였을때 최대로 먹을 수 있는 디저트 개수. 
 * 문제 유형 : 완전 탐색. 
 * 요구 개념 : 중복 순열, 방향. 
 * <풀이법 요약> 
 * 1. 해당 배열의 크기에서 대각선 최대 이동가능 횟수를 구한다(N - 2) 
 * 2. 최대 이동 가능 횟수에 대하여 2개를 선택한 순열을 구해둔다(사각형은 마주보는 변의 길이가 같다). 
 * 3. 각 점에 대하여 '문제의 조건에 유의 하면서' 디저트 카페를 투어한다(투어할 때 마다 최댓값 갱신). 
 * 4. 결과를 출력한다.
 */

public class Solution_IM_2105 {
	static int T, N;
	static int[][] cafe;
	// 모든 이동 경우를 넣어둘 배열.
	static ArrayList<int[]> per;
	static int[] temp = new int[2];
	// 디저트를 먹은 수.
	static int eat;
	// 이동 방향(남동, 남서, 북서, 북동)
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 값 초기화.
			N = sc.nextInt();
			cafe = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cafe[i][j] = sc.nextInt();
				}
			}

			// 해당 배열에서 대각선으로 움직일 수 있는 최대 횟수를 구한다.
			// 그 횟수에 대하여 2개를 선택하는 순열을 구한다.
			per = new ArrayList<int[]>();
			Permutation(0);

			// 초기 디저트를 먹은 값은 -1로 초기화.
			eat = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tour(i, j);
				}
			}

			// 결과 출력
			System.out.printf("#%d %d\n", t, eat);
		}
		sc.close();
	}

	private static void tour(int r, int c) {
		// 중복 체크용
		ArrayList<Integer> overlap;
		int nr, nc;

		// 순열의 크기 만큼 투어를 진행.
		for (int i = 0; i < per.size(); i++) {
			// 순열마다 중복 배열을 초기화.
			overlap = new ArrayList<Integer>();
			// 순열마다 현재 위치를 입력 위치로 초기화.
			nr = r;
			nc = c;
			// 움직임 횟수
			int cnt = 0;
			// 유효한 움직임인지 판단
			boolean flag = true;
			// 남동쪽 이동
			for (int j = 0; j < per.get(i)[0]; j++) {
				nr += dr[0];
				nc += dc[0];
				// 종료조건 : 인덱스를 벗어 나거나, 중복되는 경우.
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || overlap.contains(cafe[nr][nc])) {
					flag = false;
					break;
				}
				// 조건을 통과했으면, 움직임을 증가 시키고, 중복에 넣기.
				cnt++;
				overlap.add(cafe[nr][nc]);
			}
			// 유효하지 않은 움직임이면 다음 순열에 대하여 진행
			if (!flag) {
				continue;
			}
			// 남서쪽 이동
			for (int j = 0; j < per.get(i)[1]; j++) {
				nr += dr[1];
				nc += dc[1];
				// 종료조건 : 인덱스를 벗어 나거나, 중복되는 경우.
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || overlap.contains(cafe[nr][nc])) {
					flag = false;
					break;
				}
				// 조건을 통과했으면, 움직임을 증가 시킨다.
				cnt++;
				overlap.add(cafe[nr][nc]);
			}
			// 유효하지 않은 움직임이면 다음 순열에 대하여 진행
			if (!flag) {
				continue;
			}
			// 북서쪽 이동
			for (int j = 0; j < per.get(i)[0]; j++) {
				nr += dr[2];
				nc += dc[2];
				// 종료조건 : 인덱스를 벗어 나거나, 중복되는 경우.
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || overlap.contains(cafe[nr][nc])) {
					flag = false;
					break;
				}
				// 조건을 통과했으면, 움직임을 증가 시킨다.
				cnt++;
				overlap.add(cafe[nr][nc]);
			}
			// 유효하지 않은 움직임이면 다음 순열에 대하여 진행
			if (!flag) {
				continue;
			}
			// 북동쪽 이동
			for (int j = 0; j < per.get(i)[1]; j++) {
				nr += dr[3];
				nc += dc[3];
				// 종료조건 : 인덱스를 벗어 나거나, 중복되는 경우.
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || overlap.contains(cafe[nr][nc])) {
					flag = false;
					break;
				}
				// 조건을 통과했으면, 움직임을 증가 시킨다.
				cnt++;
				overlap.add(cafe[nr][nc]);
			}
			// 유효하지 않은 움직임이면 다음 순열에 대하여 진행
			if (!flag) {
				continue;
			}

			// 현재 최대 움직임 횟수와 비교
			eat = Math.max(eat, cnt);
		}
	}

	private static void Permutation(int cnt) {
		if (cnt == 2) {
			per.add(new int[] { temp[0], temp[1] });
			return;
		}
		for (int i = 0; i < N - 2; i++) {
			temp[cnt] = i + 1;
			Permutation(cnt + 1);
		}

	}
}
