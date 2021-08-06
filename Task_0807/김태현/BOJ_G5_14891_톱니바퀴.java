package ss;

import java.util.Scanner;
import java.util.Stack;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 주어진 조건에 따라 나오는 톱니바퀴의 최종 상태를 구하여라.
 * 문제 유형 : 시뮬레이션.
 * <풀이 요약>
 * 1. 각 톱니바퀴의 12시에 위치한 톱니의 인덱스를 가리키는 변수를 선언한다.
 * 2. K번 회전 시킨다. 각 회전은 아래와 같은 로직으로 구성된다.
 * -> 1번 톱니는 2번을, 2번은 1번과 3번을, 3번은 2번과 3번을, 4번은 3번을 회전 시킨다.
 * -> 톱니가 회전하면 회전 여부를 true로 만들고, 해당 회전 방향을 저장해둔다.
 * -> true인 톱니는 다음 회전에 포함시키지 않는다.
 * -> false인 톱니는 톱니의 극이 맞는 경우에만 다음 회전에 포함시킨다.
 * 3. 각 최종 톱니의 인덱스를 바라보면 결과를 계산한다.
 * <피드백>
 * 시뮬레이션 귀찮앙
 */

public class BOJ_G5_14891_톱니바퀴 {
	static int[] top, rotate;
	static int[][] gears;
	static boolean[] visited;
	static Stack<Integer> s;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 각 톱니의 12시는 초기에 모두 인덱스 0을 가리킨다.
		top = new int[4];
		gears = new int[4][8];
		// 톱니 초기화
		for (int i = 0; i < 4; i++) {
			String gear = sc.nextLine();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = gear.charAt(j) - '0';
			}
		}

		// K번 회전한다.
		int K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			// 현재 톱니
			int target = sc.nextInt() - 1;
			// 1은 시계, -1은 반시계.
			int dir = sc.nextInt();

			// 톱니의 방문 여부를 처리할 배열
			visited = new boolean[4];
			// 톱니의 회전 방향을 저장할 배열
			rotate = new int[4];
			rotate[target] = dir;

			// 회전 시킬 톱니 작업을 넣을 스택
			s = new Stack<Integer>();
			s.push(target);

			// 스택이 빌 때 까지 반복한다.
			while (!s.isEmpty()) {
				// 현재 톱니
				int cur = s.pop();
				// 방문 처리
				visited[cur] = true;

				// 현재 톱니에 따라 회전 시킬 대상을 달리한다.
				switch (cur) {
				case 0:
					simulation(cur, 1);
					continue;
				case 1:
					simulation(cur, 0);
					simulation(cur, 2);
					continue;
				case 2:
					simulation(cur, 1);
					simulation(cur, 3);
					continue;
				case 3:
					simulation(cur, 2);
					continue;
				}
			}

			// 모든 톱니를 회전시킨다.
			for (int j = 0; j < 4; j++) {
				top[j] = check(top[j] - rotate[j]);
			}
		}

		// 톱니의 최종 값을 계산한다
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (gears[i][top[i]] == 1) {
				score += Math.pow(2, i);
			}
		}
		System.out.println(score);
		sc.close();
	}

	private static void simulation(int cur, int nxt) {
		if (visited[nxt])
			return;

		int cur_idx = 0, nxt_idx = 0;

		// 현재 톱니와 대상 톱니 비교 수행
		// 대상이 오른쪽 톱니인 경우
		if (cur < nxt) {
			cur_idx = (top[cur] + 2) % 8;
			nxt_idx = (top[nxt] + 6) % 8;
		}
		// 대상이 왼쪽 톱니인 경우
		else {
			cur_idx = (top[cur] + 6) % 8;
			nxt_idx = (top[nxt] + 2) % 8;
		}

		// 다른 경우 해당 톱니의 회전 방향을 저장하고 다음 톱니 넣기, 방문처리
		if (gears[cur][cur_idx] != gears[nxt][nxt_idx]) {
			rotate[nxt] = rotate[cur] * (-1);
			s.push(nxt);
			visited[nxt] = true;
		}
	}

	private static int check(int idx) {
		if (idx >= 8) {
			idx %= 8;
		}
		if (idx < 0) {
			idx += 8;
		}

		return idx;
	}
}
