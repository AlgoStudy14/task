package s0619;

import java.util.Scanner;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : '왼쪽'부터 들어서 '다른 임의의 계란'을 '한 번씩'만 치는 경우 최대로 깰 수 있는 계란의 개수. 
 * 문제 유형 : DFS.
 * 주의 사항 : 계란을 치지않고 넘어가는 경우는 없다. 계란은 반드시 한 번은 쳐야 한다.
 * <풀이 요약>
 * 1. 가장 왼쪽의 계란에 대하여, 다른 모든 계란을 단 한 번만 치는 모든 경우를 DFS를 이용하여 구한다.
 * -> 가장 왼쪽의 계란에 대하여, 다음 계란을 치고 다음 계란을 집는다.
 * -> 이 과정을 모든 계란을 들고 내려놓을 때 까지 반복한다.
 * -> 다시 처음 과정으로 돌아와서, 그 다음 계란을 치고 다음 계란을 잡는다.
 * -> 위의 세 과정을 반복하여 모든 경우의 수에 대하여 계란이 깨지는 최대 개수를 구한다.
 * 2. 주어진 계란의 개수만큼 모든 계란이 깨졌다면, 재귀를 종료한다.
 * <피드백>
 * 문제 이해 자체가 잘 안되는 문제였다.. 다만 문제를 이해하고 백트래킹을 구현하기에는 나쁘지 않은 것 같다.
 */

public class BOJ_S1_16987_계란으로계란치기 {
	static int N;
	static int[][] eggs;
	static int[][] temp;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		eggs = new int[N][2];
		temp = new int[N][2];
		for (int i = 0; i < N; i++) {
			eggs[i][0] = sc.nextInt();
			eggs[i][1] = sc.nextInt();
			temp[i][0] = eggs[i][0];
			temp[i][1] = eggs[i][1];
		}
		max = 0;

		dfs(0);
		System.out.println(max);
		sc.close();
	}

	private static void dfs(int idx) {
		// idx가 계란의 개수를 넘어가면 현재 깨진 계란의 개수의 최대 값을 갱신하고 종료.
		if (idx >= N) {
			// 깨진 계란의 개수 세기
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (eggs[i][0] <= 0) {
					cnt++;
				}
			}
			max = Math.max(max, cnt);
			return;
		}
		// 현재 계란이 깨진 계란이라면, 다음 계란을 집기.
		if (eggs[idx][0] <= 0)
			dfs(idx + 1);
		// 그렇지 않다면, 현재 계란에 대하여 재귀적으로 탐색 반복.
		else {
			boolean flag = false;
			// 현재 계란에 대하여, 왼쪽부터 차례로 계란을 치면서 재귀를 반복하기.
			for (int i = 0; i < N; i++) {
				// 만일, 현재 들고 있는 계란과 같은 계란을 대상으로 한다면 스킵.
				if (i == idx)
					continue;
				// 만일, 이미 깨진 계란을 칠려고 한다면 스킵.
				if (eggs[i][0] <= 0)
					continue;
				// 칠 계란이 있는 경우.
				flag = true;
				// 계란 치기
				eggs[idx][0] -= eggs[i][1];
				eggs[i][0] -= eggs[idx][1];
				// 다음 계란을 집기.
				dfs(idx + 1);
				// 계란을 친 경우, 재귀가 최종 종료되면 계란 원상 복구.
				eggs[idx][0] += eggs[i][1];
				eggs[i][0] += eggs[idx][1];
			}
			// 모든 계란을 치지 못하는 경우에만, 다음 계란을 집기.
			if (!flag)
				dfs(idx + 1);
		}
	}
}
