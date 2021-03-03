/***
 * <문제 요약> 
 * 구해야 하는 것 : 네트워크의 개수
 * 문제 핵심 요약 : 방문한 노드를 기억하면서 네트워크를 만들어주면 된다 + 반복문
 * <풀이법 요약>
 * 재귀
 * 1. 노드를 방문하면 true로 체크
 * 2. 나와  같은 네트워크 상에 있는 노드가 존재하고 방문하지 않았다면 재귀
 * 
 * Main
 * 1. 만약 노드를 방문하지 않았다면 재귀 시작!
 * 2. 재귀가 끝나고나면 answer 증가
 * 
 */


public class PM_L3_네트워크 {

	static boolean[] check = new boolean[200]; // 노드 방문했는지 체크

	public int solution(int n, int[][] computers) {
		int answer = 0;

		for (int i = 0; i < n; i++) { // 전체 노드 반복
			if (check[i]) // 만약 노드에 방문했다면
				continue; // 넘겨~
			dfs(n, computers, i);
			answer++; // cycle? 만들고 나면 answer 증가 
		}

		return answer;
	}

	private static void dfs(int n, int[][] computers, int node) {
		check[node] = true; // 방문 노드 체크
		for (int next = 0; next < n; next++) {
			if (!check[next] && computers[node][next] == 1) { // 만약 방문하지 않은 노드중에 간선이 존재한다면
				dfs(n, computers, next); // 재귀
			}
		}
	}

	public static void main(String[] args) {
		PM_L3_네트워크 pm = new PM_L3_네트워크();
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		System.out.println(pm.solution(n, computers));
	}

}
