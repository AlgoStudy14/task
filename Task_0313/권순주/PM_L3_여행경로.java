import java.util.Arrays;
import java.util.Comparator;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 공항 경로 구하기
 * 문제 핵심 요약 : 오름차순 정렬 + 이미 경로가 만들어졌을 때 체크
 * 
 * <풀이법 요약>
 * 기저조건
 * 1. 여행 경로가 "이미" 만들어졌다면 return
 * 2. 여행 경로가 만들어 졌을 "때" 경로가 만들어 졌다고 체크
 * 3. 경로가 만들어 지지 않거나 방문한 경로라면 넘겨준다
 * 
 * 재귀
 * 1. 방문 체크
 * 2. dfs(index 증가시켜서)
 * 2. 방문 취소
 * 
 * 뭔가 좋은 코드는 아닌 느낌? 억지로 flag를 넣은 느낌? 더 좋은 코드가 있을 것만 같은 느낌?
 */

public class PM_L3_여행경로 {
	String[] answer;
	boolean flag; // 한번 경로 만들었을 때 체크해주기 위해

	public String[] solution(String[][] tickets) {
		answer = new String[tickets.length + 1];
		Arrays.sort(tickets, new Comparator<String[]>() { // 내림차순 정렬 -> 알파벳 순서가 앞서는 경로로 해야하기 떄문에
			@Override
			public int compare(String[] first, String[] second) {
				// compare the first element
				int comparedTo = first[0].compareTo(second[0]);
				// if the first element is same (result is 0), compare the second element
				if (comparedTo == 0)
					return first[1].compareTo(second[1]);
				else
					return comparedTo;
			}
		});
		dfs(tickets, new boolean[tickets.length + 1], "ICN", 0);
		return answer;
	}

	public void dfs(String[][] tickets, boolean[] visited, String airport, int index) {
		if (flag) // 여행 경로가 "이미" 만들어졌다면 return
			return;
		answer[index] = airport; // 경로 추가
		if (index == tickets.length) // 여행 경로가 만들어 졌을 "때"
			flag = true; // 경로가 만들어졌다고 체크
		for (int i = 0; i < tickets.length; i++) {
			if (!tickets[i][0].equals(airport) || visited[i]) // 만약 경로가 만들어 지지 않거나 방문한 경로라면 넘겨~
				continue;
			visited[i] = true; // 방문 체크
			dfs(tickets, visited, tickets[i][1], index + 1);
			visited[i] = false; // 방문 취소
		}
	}

	public static void main(String[] args) {
		PM_L3_여행경로 pm = new PM_L3_여행경로();
		String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
				{ "ATL", "SFO" } };
		System.out.println(Arrays.toString(pm.solution(tickets)));
	}

}
