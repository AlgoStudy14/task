package BFSDFS;

/*
begin 단어에서 target 단어로 바꿀 최소 변환 횟수.
한번에 한 글자만 바꿀수 있고 변환 못하는 경우 0 return
*/

public class PM_L3_단어변환 {

	static String begin="hit";
	static String target="cog";
	static String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
	//static String[] words = {"hot", "dot", "dog", "lot", "log"};
	
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE; //단어 갯수 최대 50개라 여기에 51 넣어도 됨
	public static void main(String[] args) {
		visited = new boolean[words.length];
		dfs(begin,target,0,words);
		//answer가 바뀌지 않으면 target과 같은 단어가 없는거
		System.out.println(answer==Integer.MAX_VALUE?0:answer);
}
	//시작 지점, targer, 탐색 횟수, words
	private static void dfs(String begin, String target, int cnt, String[] words) {
		if(begin.equals(target)) {
			answer = answer>cnt?cnt:answer;
			return;
		}
		for (int i = 0; i < words.length; i++) {
			//사용하지 않았고 글자 하나차이인 경우
			if(!visited[i]&&find(begin,words[i])) {
				visited[i] = true;
				dfs(words[i],target,cnt+1,words);
				visited[i] = false;
			}
		}
		
	}
	//글자 하나 차이나는 단어인지 확인하는 함수
	private static boolean find(String begin, String next) {
		int cnt = 0;
		for (int i = 0; i < begin.length(); i++) {
			if(begin.charAt(i)!=next.charAt(i)) cnt++;
		}
		return cnt==1?true:false;
	}
}
