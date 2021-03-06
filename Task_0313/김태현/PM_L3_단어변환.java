package BFSDFS;

/***
 * <문제 요약>
 * 구해야 하는 것 : 최소 단계를 거쳐서 begin을 target단어로 교체하는 횟수.
 * 문제 유형 : DFS, 구현
 * <풀이법 요약>
 * 단어를 변환 하고 다음 변환 대상을 찾는다 : 재귀적으로 다음 변환 단어를 탐색한다 : DFS.
 * 1. 방문 체크를 하면서 DFS 탐색을 진행한다.
 * 2. words의 개수 만큼 방문이 완료 되었거나, 목표 단어가 완성되면 재귀를 종료한다.
 * -> 이때, 값을 백트래킹으로 쌓아두며 현재 최솟값과 비교한다.
 */

public class PM_L3_단어변환 {
	public static void main(String[] args) {
		PM_L3_단어변환 doit = new PM_L3_단어변환();
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		doit.solution(begin, target, words);
	}

	boolean[] visited;
	int min = Integer.MAX_VALUE;

	public int solution(String begin, String target, String[] words) {
		// 방문 상태를 체크할 배열 만들기.
		visited = new boolean[words.length];
		// dfs 탐색.
		dfs(begin, target, words, 0);
		// 만일, 변환이 가능한 경우가 없으면 0을 리턴한다.
		if (min == Integer.MAX_VALUE) {
			min = 0;
		}
		return min;
	}

	private void dfs(String cur, String target, String[] words, int depth) {
		// 만일, words의 값이 target과 같다면 값을 갱신하고 재귀를 종료.
		if (cur.equals(target)) {
			min = Math.min(min, depth);
			return;
		}
		// 만일, depth가 최대라면 재귀를 종료.
		if (depth == words.length) {
			return;
		}
		// 방문하지 않은 words 요소에 대하여 비교를 수행.
		for (int i = 0; i < words.length; i++) {
			if (visited[i]) {
				continue;
			}
			// 만일, 단어가 하나만 차이가 난다면(문제 조건), 방문 체크를 하고 dfs탐색을 진행.
			if (checkGap(cur, words[i])) {
				visited[i] = true;
				dfs(words[i], target, words, depth + 1);
				// 방문을 완료했다면 방문 해제 해준다.
				visited[i] = false;
			}
		}
	}

	private boolean checkGap(String cur, String next) {
		int cnt = 0;
		for (int i = 0; i < cur.length(); i++) {
			if (cur.charAt(i) != next.charAt(i)) {
				cnt++;
			}
			if (cnt > 1) {
				return false;
			}
		}
		if (cnt == 1) {
			return true;
		} else {
			return false;
		}
	}
}
