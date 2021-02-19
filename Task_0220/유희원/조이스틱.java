class Solution {
    public int solution(String name) {
     int answer = 0;
     //최대로 가질 수 있는 min값은 끝까지 가는것
		int len = name.length();
		int min = len-1;
		for (int i = 0; i < len; i++) {
			answer+=Math.min(name.charAt(i)-'A', 'Z'-name.charAt(i)+1);
        	int next = i+1;
          //내 다음이 A면 계속 진행(next++)
			while(next<len&&name.charAt(next)=='A') next++;
      //현재까지 왔다가 다시 돌아가서 남은거까지 가는 이동 횟수
			min = Math.min(min, i+len-next+i);
		}
		answer += min;
		return answer;
    }
}
