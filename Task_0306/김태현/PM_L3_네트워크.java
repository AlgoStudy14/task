package BFSDFS;

/***
 * <문제 요약>
 * 구해야 하는 것 : 총 네트워크의 개수
 * 문제 유형 : DFS
 * <풀이법 요약>
 * n개의 컴퓨터에 대하여 아래 과정을 dfs한다.
 * -> 만일, 컴퓨터가 방문되지 않은 상태라면 네트워크 개수 + 1.
 * -> 해당 컴퓨터와 연결 되어있는 컴퓨터가 있다면 해당 컴퓨터의 행(row)부터 탐색을 시작한다(DFS).
 * -> 컴퓨터를 방문할 것이라면, 방문 체크를 한다.
 */

public class PM_L3_네트워크 {
	public static void main(String[] args) {
		PM_L3_네트워크 doit = new PM_L3_네트워크();
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		doit.solution(n, computers);
	}

	static boolean visited[];
	static int cnt = 0;

	public int solution(int n, int[][] computers) {
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			// 만일, 초기 방문 대상이 false라면, true로 바꾸고 네트워크의 개수 + 1, dfs 진행.
			if (!visited[i]) {
				visited[i] = true;
				cnt++;
				dfs(n, computers, i);
			}
		}
		return cnt;
	}

	private void dfs(int n, int[][] computers, int row) {
		// 만일 모든 컴퓨터를 방문했다면, 더 이상 볼 필요가 없으므로 재귀를 종료한다.
		if (check(visited)) {
			return;
		}
		// 해당 행(해당 컴퓨터)의 1열부터 차례로 연결되어있는 컴퓨터가 있는지 확인한다.
		for (int i = 0; i < n; i++) {
			// 컴퓨터가 연결되어 있고
			if(computers[row][i] == 1) {
				// 해당 컴퓨터를 방문하지 않았다면
				if(!visited[i]) {
					// 해당 컴퓨터를 방문처리하고
					visited[i] = true;
					// 해당 컴퓨터부터 다시 dfs.
					dfs(n, computers, i);
				}
			}
		}
	}

	// 전체 방문이 완료 되었는지 체크.
	private boolean check(boolean[] visited) {
		for (boolean x : visited) {
			if (!x) {
				return false;
			}
		}
		return true;
	}
}
