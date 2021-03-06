/**
 * <문제 요약> 구해야 하는 것: begint에서 target으로 변환하는 가장 짧은 변환 과정(몇단계인지) 
 * 제약 사항: 한번에 한개의 알파벳만 바꿀 수 있고, words에 있는 단어로만 변환할 수 있다. 
 * 문제 유형: DFS(완전 탐색) 
 * 요구 개념: DFS(완전 탐색 -> * 백트래킹)
 * 
 * <풀이법 요약> 
 * 1. visited 배열 만들어주기 -> 방문 여부 체크해서 모두다 탐색 위해서 
 * 2. DFS 돌면서 단계 찾기!!
 */

public class PM_L3_단어변환 {

	public static void main(String[] args) {
		PM_L3_단어변환 pm = new PM_L3_단어변환();

		System.out.println(pm.solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log", "cog" }));
		System.out.println(pm.solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log" }));
	}

	static boolean[] visited;
	static int cntAnswer = Integer.MAX_VALUE;

	public int solution(String begin, String target, String[] words) {

		visited = new boolean[words.length];

		int same = begin.length() - 1;

		// 1. 먼저 target이 words에 있나 확인
		boolean success = false;
		for (int i = 0; i < words.length; i++) {
			if (target.equals(words[i])) {
				success = true;
			}
		}

		// 변환할 수 없는 경우에는 0을 return
		if (!success)
			return 0;

		// 2. DFS를 돌며 확인
		DFS(0, words, begin, target);
		
		int answer=cntAnswer;

		return answer;
	}

	private void DFS(int cnt, String[] words, String begin, String target) {

		// 유도 조건
		for (int i = 0; i < words.length; i++) {
			if (!visited[i] && check(begin, words[i], begin.length()-1)) {
				if(check(target, words[i], target.length())) {
					cntAnswer = Math.min(cnt+1, cntAnswer);
					return;
				}
				visited[i] = true;
				DFS(cnt + 1, words, words[i], target);
				visited[i] = false;
			}
		}
		
	}

	private boolean check(String comp, String word, int len) {
		int sameCnt = 0;
		for (int i = 0; i < comp.length(); i++) {
			if (comp.charAt(i) == word.charAt(i)) {
				sameCnt++;
			}
		}

		if (sameCnt == len)
			return true;
		else
			return false;
	}
}
