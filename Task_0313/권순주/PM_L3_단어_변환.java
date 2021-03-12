/***
 * <문제 요약> 
 * 구해야 하는 것 : begin에서 target으로 변환하는 가장 짧은 변환 과정
 * 문제 핵심 요약 : 알파벳 하나 다름 + 방문하지 않았음 = 방문해!
 * 
 * <풀이법 요약>
 * 기저조건
 * 1. 방문했다면 넘긴다
 * 2. 알파벳이 두개이상 다르다면 넘긴다
 * 3. target과 같고 answer보다 방문 횟수가 작다면 answer를 바꿔준다
 * 
 * 재귀
 * 1. 방문 체크
 * 2. dfs(방문 횟수 증가시켜서)
 * 2. 방문 취소
 */

public class PM_L3_단어_변환 {
	int answer = 0;

	public int solution(String begin, String target, String[] words) {
		answer = words.length + 1; // 최대가 words의 길이이기 때문에 answer+1한 값을 넣어준다.
		dfs(begin, target, words, new boolean[words.length], 1);
		return (answer == (words.length + 1)) ? 0 : answer; // 만약 answer가 초기값과 같다면 존재하지 않는 것이기 때문에 0을 return 한다.
	}

	public void dfs(String begin, String target, String[] words, boolean[] visited, int count) {
		for (int i = 0; i < words.length; i++) {
			int cnt = 0; // 한 개의 알파벳만 다른지 확인하려는 변수
			if (visited[i])// 이미 방문했다면 넘겨~~
				continue;
			for (int j = 0; j < begin.length(); j++) {
				if (begin.charAt(j) != words[i].charAt(j)) // 알파벳이 다르면 cnt 증가
					cnt++;
				if (cnt >= 2) // 다른 알파벳이 2개 이상이면 더 찾지 않고 빠져나옴
					break;
			}
			if (cnt == 1) { // 한 개의 알파벳만 다르다면
				if (words[i].equals(target) && answer > count) { // 단어가 target과 같고 단계의 과정이 더 작다면
					answer = count; // answer 엎어쳐~~
					return;
				}
				visited[i] = true; // 방문 체크
				dfs(words[i], target, words, visited, count + 1); // 방문 횟수 증가 시키면서 dfs
				visited[i] = false; // 방문 취소
			}
		}
	}

	public static void main(String[] args) {
		PM_L3_단어_변환 pm = new PM_L3_단어_변환();
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println(pm.solution(begin, target, words));
	}

}
