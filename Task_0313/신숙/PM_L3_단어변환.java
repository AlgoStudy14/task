package DFS_BFS;

public class PM_L3_단어변환 {
	/*
	 * begin 과 target 은 문자열. 그리고 문자열의 집합 words가 주어짐.
	 * begin에서 target으로 변하는 가장 짧은 변환가정을 찾고자 함.
	 * 
	 * <규칙>
	 * 1. 한 번에 한 개의 알파벳만 바뀜.
	 * 2. words에 있는 단어로만 변환가능.
	 * 		ex) begin : hit, target : cog. 문자열 : {"hot", "dot", "dog", "lot", "log", "cog"}.
	 * 			hit -> hot -> dot -> dog -> cog 로 변환가능. 답 : 4
	 * 
	 * <방법>
	 * 1. 먼저 words 안에 target이 없으면 답은 0.
	 * 2. words안에 답이 있는 경우, target과 같아질 때까지, 2개가 같고 한 글자만 다른경우 그것으로 변환하고, boolean은 true로 바꾸며 계속 변환시킴.
	 * 				=> 수정. 한번에 한개의 알파벳만 변환 가능.
	 * 3. 진행하면서 수++ 하다가,  target 과 같아질 경우 Math.min(answer, 수); 
	 * 				words 개수가 50개. dfs로 괜찮을까?
	 * 
	 * <결과>
	 * 1. 답이 0이나옴. 왜? => answer이 0으로 되어잇어서 무조건 최소값 0인 상태.
	 */
	static boolean[] check;
	static boolean flag;
	static int answer;
	public static int solution(String begin, String target, String[] words) {
		answer = Integer.MAX_VALUE;
        check = new boolean[words.length];
        //1번 words안에 target이 없는 경우.
        for(String w : words) {
        	if(w.equals(target)) {
        		flag=true;
        		break;
        	}
        }
        if(!flag)
        	return 0;
        //2 dfs
        dfs(begin, target, words, 0);
        
        return answer;
    }
	
	static void dfs(String word, String target, String[] words, int count) {
		if(word.equals(target)) {
			answer = Math.min(answer, count);
			return;
		}
		for(int i = 0; i < words.length; i++) {
			//음... 여기에서 체크를 안한 단어여야하고, 다음에 올 단어를 비교하는데, 이 단어가 2개만 달라야함. 
			if(!check[i] && check2(word, words[i])) {
				check[i] = true;
				dfs(words[i], target, words, count+1);
				check[i] = false;
			}
		}
	}
	
	static boolean check2(String word, String index) {
		int cnt = 0;
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) != index.charAt(i))
				cnt++;
		}
		return cnt == 1 ? true : false;
	}
	
	public static void main(String[] args) {
		int ans = solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"});
		System.out.println(ans);
	}
}
