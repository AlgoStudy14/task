package algo0802;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 문제 정의 : 톱니바퀴를 회전시킨 후, 최종 톱니바퀴 상태의 점수의 합을 출력
 * 문제 유형 : 시뮬레이션
 * 주의 사항 : 톱니바퀴를 회전하고 나서 회전 시키면 안되고 한 번에 회전해야 함
 * <풀이 요약>
 * 반대극 반대로 회전 / 같은극 그대로
 * N(0)S(1) / 시계(1)반시계(-1)
 * 0. 톱니바퀴를 회전시키면 추가, 삭제가 빈번하므로 ArrayList를 사용
 * 1. 톱니바퀴를 한 번에 회전해야 하므로 톱니바퀴를 회전할 수 있는지 없는지 재귀로 체크
 *  1-1. 현재 톱니바퀴를 기준으로 왼쪽/오른쪽 톱니바퀴를 회전시킬 수 있는지 체크 후 canRotate에 저장
 *  1-2. 회전시킬 수 없으면 재귀를 멈춤
 * 2. 4개의 톱니바퀴 중 회전 시킬 수 있다면 회전
 */

public class BOJ_G5_14891_톱니바퀴 {
	
	static final int CLOCKWISE = 1, COUNTERWISE = -1;
	static int[] canRotate;
	static ArrayList<Character>[] gear = new ArrayList[4];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			gear[i] = new ArrayList<>();
			for (int j = 0; j < 8; j++) {
				gear[i].add(s.charAt(j));
			}
		}
		
		int T = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			// n번 톱니바퀴를 기준으로 다른 톱니바퀴들을 회전할지 체크
			// 그 다음 톱니바퀴를 회전 시켜야 함(회전 시키고 난 후 회전X)
			canRotate = new int[4];
			direction(n, d);
			rotate();
		}

		calculateScore();
	}

	static void direction(int now, int d) {
		canRotate[now] = d;
		
		int left = now - 1;
		int right = now + 1;

		// 현재 톱니바퀴에서 왼쪽으로 갈 수 있는지 체크(범위를 벗어나거나, 이미 방문한 톱니바퀴이거나, 같은 극일 경우 방문X)
		if (left >= 0 && canRotate[left] == 0 && gear[left].get(2) != gear[now].get(6)) {
			direction(left, -d);
		}
		// 현재 톱니바퀴에서 오른쪽으로 갈 수 있는지 체크(범위를 벗어나거나, 이미 방문한 톱니바퀴이거나, 같은 극일 경우 방문X)
		if (right < 4 && canRotate[right] == 0 && gear[now].get(2) != gear[right].get(6)) {
			direction(right, -d);
		}
	}
	
	static void rotate() {
		for (int n = 0; n < 4; n++) {
			int d = canRotate[n];

			if (d == CLOCKWISE) {
				gear[n].add(0, gear[n].remove(7));
			} else if (d == COUNTERWISE) {
				gear[n].add(gear[n].remove(0));
			}
		}
		
	}
	
	static void calculateScore() {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (gear[i].get(0) == '1') {
				score += Math.pow(2, i);
			}
		}
		System.out.println(score);
	}
}
