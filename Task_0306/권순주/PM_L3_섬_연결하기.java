import java.util.Arrays;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 최소 신장 트리 -> 최단 거리 구하기
 * 문제 핵심 요약 : 크루스칼 알고리즘의 정석적인 문제
 * <풀이법 요약> 
 * *** 혹시 저의 풀이법을 보고 있으시다면!! ***
 *  저의 허접한 설명보다는 "나동빈님의 크루스칼 알고리즘" 설명을 보시는 것을 추천드립니다!
 * 	그 설명 그대로 코딩한 결과입니다
 * 	저도 다시 한번 공부했습니다,,
 */

public class PM_L3_섬_연결하기 {

	static int[] node;

	// union-find
	public static int find(int position) {
		// 자신의 노드가 최상위 -> 그대로
		if (node[position] == position)
			return position;
		// 자신이 최상위가 아니면 부모노드를 찾음
		else
			return node[position] = find(node[position]);
	}

	public int solution(int n, int[][] costs) {
		int answer = 0;
		// 비용이 작은 순서대로 정렬
		Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
		// cycle을 찾기 위해 union-find
		node = new int[n];
		for (int i = 0; i < n; i++) {
			node[i] = i;
		}
		// cycle이 생기는지 체크하면서 탐색
		for (int i = 0; i < costs.length; i++) {
			int start = find(costs[i][0]);
			int end = find(costs[i][1]);
			int cost = costs[i][2];

			// 이미 방문한 노드가 아니라면 -> 즉 사이클이 생기지 않는다면
			if (start != end) {
				node[end] = start;
				answer += cost;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		PM_L3_섬_연결하기 pm = new PM_L3_섬_연결하기();
		int n = 4;
		int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		System.out.println(pm.solution(n, costs));
	}

}
