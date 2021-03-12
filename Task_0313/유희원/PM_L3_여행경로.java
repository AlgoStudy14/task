package BFSDFS;

/*
ICN에서 출발해서 항공권 모두 이용해서 여행경로 짜려고 하는데 두가지 이상일 경우 알파벳순서 따짐
경로 안나오는 경우 안줌

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PM_L3_여행경로 {

	static String[][] tickets = {{"ICN", "SFO"},{"ICN", "ATL"},{"SFO", "ATL"},{"ATL", "ICN"},{"ATL","SFO"}};
	static boolean[] visited;
	
	static ArrayList<String> answers = new ArrayList<>();
	public static void main(String[] args) {
		visited = new boolean[tickets.length];
		dfs(0, "ICN", "ICN", tickets);
		//답들 중 알파벳 순으로 빠른거 골라내기 위해 정렬
		Collections.sort(answers);
		//answer 공백 기준 잘라서 저장
		String[] answer = answers.get(0).split(" ");
		System.out.println(Arrays.toString(answer));
	}
	//들린 공항 개수, 출발지점, 붙여나갈 answer, tickets
	private static void dfs(int cnt, String present, String answer, String[][] tickets) {

		//다 돌았으면 이어붙인 answer을 answers에 추가
		if(cnt == tickets.length) {
			answers.add(answer);
			return;
		}
		for (int i = 0; i < tickets.length; i++) {
			if(!visited[i]&&tickets[i][0].equals(present)) {
				visited[i] = true;
				dfs(cnt+1,tickets[i][1],answer+" "+tickets[i][1], tickets);
				visited[i] = false;
			}
		}
		return;
	}

}
