package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * <풀이 : 백트래킹>
 * 1. 2차원 배열을 만들고, 각 날에 들고가는 떡을 저장해둔다.
 * 2. 백 트래킹을 통해 떡을 가져가는 경우를 구해본다.
 * -> 각 날이 지났다면, 해당 날을 방문처리 한다.
 * -> 이전 날에 선택한 떡을 고르지 못한다면, 현재 날 방문 처리를 해제하고 이전 날로 돌아가 다른 떡을 선택한다.
 * -> 이때 각 날의 방문마다 고른 떡은 스택 자료구조에 넣고 뺀다.
 * 3. 최종 날까지 도달했다면, 스택을 모두 출력한다.
 * -> 도달하지 못했다면, -1을 출력한다.
 * <피드백>
 * 어케 풀어영? ㅋㅋ
 */

public class BOJ_G5_16432_떡장수와호랑이 {
	static int N;
	static int[][] days;
	static boolean[] visited;
	static Stack<Integer> ans;
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		days = new int[N][];
		visited = new boolean[N];
		ans = new Stack<Integer>();
		flag = false;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			days[i] = new int[size];
			for (int j = 0; j < size; j++) {
				days[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
		if (!flag) {
			System.out.println(-1);
		} else {
			while (!ans.isEmpty()) {
				System.out.println(ans.pop());
			}
		}
	}

	private static void dfs(int day, int before) {
		// 최종 종료 날짜에 도달한 경우
		if (day == N) {
			flag = true;
			return;
		}

		// 날짜별 떡 선택
		for (int i = 0; i < days[day].length; i++) {
			if (flag)
				continue;
			if (before != days[day][i]) {
				visited[day] = true;
				dfs(day + 1, days[day][i]);
				if (flag)
					ans.push(days[day][i]);
				visited[day] = false;
			}
		}
	}
}
