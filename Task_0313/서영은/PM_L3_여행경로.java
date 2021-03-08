import java.util.Arrays;
import java.util.Comparator;

/**
 * <문제 요약> 
 * 구해야 하는 것: 방문하는 공항 경로 
 * 제약 사항: 주어진 항공권은 모두 사용해야 한다. / 가능한 경로가 2개 이상일 경우, 알파벳 순서가 앞서는 경로 반환  
 * 문제 유형: DFS(완전 탐색) 
 * 요구 개념: DFS(완전 탐색 -> * 백트래킹)
 * 
 * <풀이법 요약> 
 * 1. visited 배열 만들어주기 -> 방문 여부 체크해서 모두다 탐색 위해서 
 * 2. 출발지점이 같은 경우, 도착 지점을 알파벳 순으로 정렬 먼저 해주기 
 * 3. 맨 처음에 "ICN" 만나면 답 0,1 index 세팅
 * 4. 그 이후에 DFS를 돌면서 뒤의 경로가 오름차순으로 배열되었으므로 답이 나오면 그냥 반환 
 * 5. 혹시 끝까지 가도 답이 나오지 않았다면, 다음 "ICN"으로 시작하는 티켓 찾기 
 * 
 * => 프로그래머스에 있는 테케 다 써봤는데 다 통과해도 계속 1번 테케 틀림..
 * => 정렬 제대로 서 해결!
 */
public class PM_L3_여행경로 {

	public static void main(String[] args) {
		PM_L3_여행경로 pm = new PM_L3_여행경로();

//		System.out.println(pm.solution(new String[][] { { "B", "ICN" }, { "ICN", "B" }, { "B", "A" }, { "A", "ICN" }, { "ICN", "A" }}));
		System.out.println(
				pm.solution(new String[][] { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } }).toString());
//		System.out.println(pm.solution(new String[][] { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" },
//				{ "ATL", "ICN" }, { "ATL", "SFO" } }).toString());

	}

	static boolean[] visited;
	static boolean success;
	static String[] answer;
	public String[] solution(String[][] tickets) {
		
		answer = new String[tickets.length+1];
		
		// a 공항이 같은 경우, b 공항으로 가는 항공 오름차순 정렬 
		Arrays.sort(tickets, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				// 처음 안됐던 정렬. 이렇게 하면 안된다!
//				if(o1[0].equals(o2[0])) {
//					return o1[1].compareTo(o2[1]);
//				} else return 1;
				if(o1[0].equals(o2[0])) {
					return o1[1].compareTo(o2[1]);
				} else return o1[0].compareTo(o2[0]);
			}
		});
		
		//방문 체크 
		visited = new boolean[tickets.length];
		//출력 체크 
		for (int i = 0; i < tickets.length; i++) {
			System.out.println(tickets[i][0] + " " + tickets[i][1]);
		}
		
		String[] tempAnswer = new String[tickets.length+1];
		for (int i = 0; i < tickets.length; i++) {
			if(tickets[i][0].equals("ICN")) {
				
				initVisited();
				tempAnswer[0] = "ICN";
				tempAnswer[1] = tickets[i][1];
				visited[i]=true;
				
				DFS(i, tickets, 2, tempAnswer);
				
//				if(answerIdx!=tickets.length) visited[i]=false;
//				else if(answerIdx==tickets.length) break;
				if(success) break;
				else visited[i]=false;
			}
		}
		//출력 체크 
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
		return answer;
	}
	// visited 초기화 
	private void initVisited() {
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
	}
//	tickets = [['ICN','B'],['B','ICN'],['ICN','A'],['A','D'],['D','A']]
//			answer = ['ICN', 'B', 'ICN', 'A', 'D', 'A']
	private void DFS(int startIdx, String[][] tickets, int answerIdx, String[] tempAnswer) {
		if(answerIdx == tickets.length+1) {
			success = true;
			
			for (int i = 0; i < tempAnswer.length; i++) {
				answer[i] = tempAnswer[i];
			}
			return;
		}
		for (int i = 0; i < tickets.length; i++) {
			if(!visited[i] && tickets[startIdx][1].equals(tickets[i][0]) && !success) {
				visited[i] = true;
				tempAnswer[answerIdx] = tickets[i][1];
				DFS(i, tickets, answerIdx+1, tempAnswer);
				visited[i] = false;
			}
		}
	}
}
