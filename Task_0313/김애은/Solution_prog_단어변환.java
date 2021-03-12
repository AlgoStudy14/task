package algo0312;

/*
 * 단어 변환 문제
 * bfs라고 바로 생각함
 * 하지만 queue로 제대로 접근을 못함
 * 
 * 문제 설명: begin단어가 target단어가 되기 위해 하나씩 words 단어를 거쳐서 바껴야 한다. cnt가 가장 짧은 경우를 return
 * 
 * dfs와 bfs의 차이점은?
 * dfs:나(현재 노드)를 먼저 방문하고 나와 연결된 다른 노드들 중 하나를 방문하는 것입니다.
 * bfs:인접한 모든 노드들을 동시에(?) 방문하며 탐색을 진행한다고 생각하면 된다.
 * 
 * 단어들을(words) 불러오고 이미 사용한 단어가 아닌 경우 현재 단어와 한글자씩 비교하고 변환이 가능한 경우 해당 글자를 큐에 추가하고 방문처리 해준다.
 * (contain()) 이때 몇번 변환했는지 알 수 있게 word 클래스의 cnt를 증가시켜 큐에 추가해준다.
 * 
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_prog_단어변환 {
	static int answer;
	static boolean check;
	static boolean[] V;
	public static void main(String[] args) {
		String begin = "hit";
		String target="cog";
		String[] words= {"hot","dot","dog","lot","log","cog"};

		V=new boolean[words.length];//방문했는지 확인하는 용도
		List<String> wordList = Arrays.asList(words); //array로 바꾸면 contains도 쓸수 있고 좋다.
		if(!wordList.contains(target))
			answer=0;//여기서 끝내야 도니까 return으로 바꿔줘야 된다.
		
		//bfs
		Queue<word> que = new LinkedList<>(); //시작하는 애랑 하나 차이나는 애들 담는 곳
		
		que.offer(new word(begin,0));//처음 begin부터 시작
		while(!que.isEmpty()) { 
			word current = que.poll();
			if(current.word.equals(target)) {
				answer=current.cnt;
				break;
			}
			for(int i=0;i<words.length;i++) {
				if(!V[i] && contain(current.word,words[i])) {//안가보고 하나 차이나는거
					V[i]=true;
					que.offer(new word(words[i],current.cnt+1));
				}
			}
		}
		System.out.println(answer);
	}

	private static boolean contain(String target, String st) {
		int count=0;
		for(int i=0;i<target.length();i++) {
			if(target.charAt(i)!=st.charAt(i)) {
				count++;
			}
		}
		if(count>1)
			return false;
		return true;//count가 1인걸로 갈거기 때문에
	}
	static class word{
		String word;
		int cnt;
		public word(String word, int cnt) {
			this.word = word;
			this.cnt = cnt;
		}
	}
}
