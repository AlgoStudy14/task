package BFSDFS;

import java.util.Arrays;
import java.util.Stack;

/***
 * <문제 요약>
 * 구해야 하는 것 : 항공권을 모두 이용하여 짠 여행 경로.
 * 문제 유형 : DFS.
 * <풀이법 요약>
 * 1. 도착점을 기준으로 사전식 오름차순 정렬을한다(문제의 조건).
 * 2. 출발지와 도착지를 바꿔주며 DFS탐색을 한다.
 * <난해했던 점>
 * 티켓을 사용하면 끝이므로, visit 배열을 true인 상태로 유지해도 된다고 생각하였는데,
 * 이상한 순서로 탐색하는 경우 모든 티켓을 못 사용하는 경우가 있기 때문에 dfs에서 탈출하는 조건을 추가해 주었다.
 */

public class PM_L3_여행경로 {
	public static void main(String[] args) {
		PM_L3_여행경로 doit = new PM_L3_여행경로();
		String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
		doit.solution(tickets);
	}

	Stack<String> result = new Stack<String>();
	int idx = 1;
	boolean[] visited;
	boolean flag = false;

	public String[] solution(String[][] tickets) {
		String[] answer = new String[tickets.length + 1];
		// 출발지는 항상 ICN공항이다.
		result.push("ICN");

		// 해당 티켓을 썼는지 판단하기 위한 방문 배열.
		visited = new boolean[tickets.length];

		// 도착점을 기준으로 오름 차순으로 정렬한다(DFS 탐색에서 도착지의 알파벳 순서가 더 빠른 지점을 먼저 탐색하기 위함).
		Arrays.sort(tickets, (x, y) -> x[1].compareTo(y[1]));

		// dfs 탐색.
		dfs(tickets, 0, "ICN");
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}
		System.out.println(Arrays.toString(answer));

		return answer;
	}

	private void dfs(String[][] tickets, int depth, String cur) {
		// 티켓을 다 썼으면 재귀를 종료한다.
		if (depth == tickets.length) {
			flag = true;
			return;
		}
		for (int i = 0; i < tickets.length; i++) {
			// 티켓을 사용했다면 스킵.
			if (visited[i]) {
				continue;
			}
			// 만일 출발지가 현재 위치와 같다면, 티켓을 사용하고 경로를 기록한뒤 dfs 탐색을 진행.
			if (cur.equals(tickets[i][0])) {
				visited[i] = true;
				result.push(tickets[i][1]);
				dfs(tickets, depth + 1, tickets[i][1]);
				// 티켓을 다 썼으면, 재귀를 빠져나온다.
				if (flag) {
					return;
				}
				result.pop();
				visited[i] = false;
			}
		}
	}
}
