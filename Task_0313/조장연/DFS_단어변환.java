/*
 * <문제 요약>
 * 시작 단어, 최종단어, 단어집합이 주어졌을 때 1글자만 다른 단어집합 안의 단어로 바꿔가며 가장짧은 변환과정 크기 구하기
 * 
 * <풀이법 요약>
 * 뺑뺑이 도는 것을 막기위해 사용했는지 안했는지를 체크하는 boolean[]을 만듬
 * dfs 사용
 */
public class DFS_단어변환 {
	static int answer;

	public static void main(String[] args) {
		DFS_단어변환 doit = new DFS_단어변환();
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println(doit.solution(begin, target, words));

	}

	public int solution(String begin, String target, String[] words) {
		answer = Integer.MAX_VALUE;
		boolean[] flags = new boolean[words.length];
		dfs(begin, target, words, flags, 0);

		if (answer == Integer.MAX_VALUE)
			answer = 0;
		return answer;
	}

	public void dfs(String begin, String target, String[] words, boolean[] flags, int count) {
		for (int i = 0; i < words.length; i++) {
			if (checkAlp(begin, target) && target.equals(words[i])) {
				if (answer > count + 1)
					answer = count + 1;
				return;
			}

			if (flags[i] == false && checkAlp(begin, words[i])) {
				flags[i] = true;
				dfs(words[i], target, words, flags, count + 1);
				flags[i] = false;
			}
		}
	}

	public boolean checkAlp(String str1, String str2) {
		int count = 0;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i))
				count++;
		}

		if (count == 1)
			return true;
		else
			return false;
	}
}
