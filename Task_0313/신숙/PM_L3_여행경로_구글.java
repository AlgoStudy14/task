package DFS_BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PM_L3_여행경로_구글 {
	/*
	 * 항공권이 2차원 배열로 주어짐. {"ICN", "JFK"}, {"HND", "IAD"}
	 * 						   출발지      도착지
	 * 티켓을 모두 사용해야함 => 모두 순환해야함
	 * 항상 시작은 ICN 에서 해야 함.
	 * 경로가 2개 이상일 때 알파벳 순서가 앞서는 경로 return.
	 * 
	 * <해결법>
	 * 1. dfs로 count가 tickets.length + 1일 때, 해달 순서?를 배열에 담아서 넘겨주기.	=> 인자로 cnt 같이 넘겨주기.
	 * 2. dfs 순환 조건은 tickets[i][1].equals(tickets[j][0]) 일때 
	 * 3. 앞에것도 확인 + 전부 사용 해야하니까 boolean 배열(visit)로 이 티켓을 사용했는지 확인
	 * 4. 순환하면서 만약 돌 수 없다면, 넣었던 값을 빼야함. => 무조건 항공이름은 3글자.
	 * 
	 * 		=> 어디에서 배열에 티켓 도착지를 넣어야 할까?
	 * 		=> 가능한 경로가 2개 이상일때 알파벳 순서가 앞서야한다... => 정렬이 필요?
	 */
	static List<String> list = new ArrayList<>();
	static String route = "";
	static boolean[] visit;
		
	public static String[] solution(String[][] tickets) {
		for(int i = 0; i < tickets.length; i++) {
			//다음 티켓편부터 시작하기 위해 볼 때 배열 초기화.
			visit = new boolean[tickets.length];
			String start = tickets[i][0];
			String end = tickets[i][1];
			//무조건 ICN에서만 시작해야하므로 ICN일 때 함수 실행.
			if(start.equals("ICN")) {
				//문자열에 "ICN, 을 넣음.
				route = start + ","; 
				visit[i] = true; 
				dfs(tickets, end, 1);
			}
		}
		//정렬
		Collections.sort(list);
		String[] answer = list.get(0).split(",");
		
		return answer;
	}
	private static void dfs(String[][] tickets, String end, int cnt) {
		//ICN에서 시작하고 무조건 그 다음 도착지를가야하므로 함수에 들어오면, (end,)
		route += end + ",";
		//시작점과 도착점을 미리 넣었고, cnt는 1부터 시작이므로 cnt == tickets.length 일 떄 전부 돈 것. 
		if(cnt == tickets.length) {
			list.add(route); 
			return;
		}
		//미리 [0][0], [0][1]을 문자열에 넣어서 1부터일 줄 알았는데, 그럼 답이 다름. 갈수있는 경우가 2개인데 정렬이안됨.
		for(int i = 0; i < tickets.length; i++) {
			String s = tickets[i][0], e = tickets[i][1];
			if(s.equals(end) && !visit[i]) {
				visit[i] = true;
				dfs(tickets, e, cnt + 1);
				visit[i] = false; 
				route = route.substring(0, route.length()-4);
			}
		}
	}
	
	public static void main(String[] args) {
		//String[] arr = solution(new String[][] { {"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"} });
		String[] arr = solution(new String[][] { {"ICN", "SFO"},{"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}});
		System.out.println(Arrays.toString(arr));
	}
}
