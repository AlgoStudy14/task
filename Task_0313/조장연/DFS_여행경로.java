import java.util.*;
/*
 * <문제 요약>
 * 주어진 항공권을 모두 이용하여 경로짜기
 * ---조건---
 * 1. 주어진 항공권은 모두 이용해야만 함
 * 2. 가능한 경로가 2개 이상인 경우 알파벳 순서가 앞서는 경로를 return
 * 
 * <풀이법 요약>
 * 주어진 항공권을 시작지점, 도착지점을 기준으로 정렬
 * 티켓을 사용했는지 체크하는 flag배열을 만듬
 * 시작지점 비교 및 flag = true를 만족하는 dfs를 돌림.
 * dfs를 돌린 횟수가 티켓수와 같으면 true를 리턴.(-> 만약 경로가 짜여지면 나머지 과정을 수행하지 않음)
 */
public class DFS_여행경로 {

	public static void main(String[] args) {
		DFS_여행경로 doit = new DFS_여행경로();
		String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
		String[] arr = doit.solution(tickets);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "   ");
		}
	}

	static boolean[] flag;
	static String[] answer;

	public String[] solution(String[][] tickets) {
		answer = new String[tickets.length + 1];
		flag = new boolean[tickets.length];
		answer[0] = "ICN";

		Arrays.sort(tickets, new Comparator<String[]>() { // 주어진 배열을 sorting하는 과정
			@Override
			public int compare(String[] arr1, String[] arr2) {
				for (int i = 0; i < Math.min(arr1[0].length(), arr2[0].length()); i++) {
					if ((int) arr1[0].charAt(i) > (int) arr2[0].charAt(i))
						return 1;
					else if ((int) arr1[0].charAt(i) < (int) arr2[0].charAt(i))
						return -1;
				}

				for (int i = 0; i < Math.min(arr1[1].length(), arr2[1].length()); i++) {
					if ((int) arr1[1].charAt(i) > (int) arr2[1].charAt(i))
						return 1;
					else if ((int) arr1[1].charAt(i) < (int) arr2[1].charAt(i))
						return -1;
				}
				
				return 0;
			}
		});

		boolean end = dfs("ICN", 0, tickets, tickets.length); // dfs 돌린다~!
		return answer;
	}

	public boolean dfs(String start, int count, String[][] tickets, int end) {
		if (count == end) // 티켓 다 쓰면 return true;
			return true;

		for (int i = 0; i < end; i++) {
			if (tickets[i][0].equals(start) && flag[i] == false) { // 시작지점 만족하고 사용하지 않은 티켓
				flag[i] = true;
				answer[count + 1] = tickets[i][1];
				boolean check = dfs(tickets[i][1], count + 1, tickets, end);

				if (check == true) // 만약 경로 짜여지면 stop!
					return true;

				flag[i] = false;
			}
		}
		return false;
	}
}
