package s0327;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * <문제 요약>
 * 구해야 하는 것 : 가장 먼 노드의 개수.
 * 요구 개념/문제 유형 : 다익스트라 알고리즘.
 * <풀이법>
 * 1. 다익스트라 알고리즘을 이용하여 1번 노드로부터 모든 노드까지의 최단거리를 구한다(회소 그래프이므로 우선순위큐 이용).
 * 2. 비용의 최댓값을 구하고, 최댓값의 개수를 구한다.
 */

public class PM_L3_가장먼노드 {
	public static void main(String[] args) {
		int n = 6;
		int[][] vertex = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		solution(n, vertex);
	}

	public static int solution(int n, int[][] edge) {
		int answer = 0;
		// 인접리스트를 이용한 그래프 만들기
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < edge.length; i++) {
			// 양방향 그래프
			graph.get(edge[i][0]).add(edge[i][1]);
			graph.get(edge[i][1]).add(edge[i][0]);
		}

		// 다익스트라 알고리즘 진행.
		// 거리 비용 초기화.
		int[] dist = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;

		// 비용이 낮은것을 택하기 위해 오름차순 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> (Integer.compare(o1[1], o2[1])));
		// 1번 정점으로 가는데 필요한 비용은 0.
		pq.offer(new int[] { 1, 0 });
		while (!pq.isEmpty()) {
			// 최소 비용을 갖는 노드를 추출한다.
			int[] cur = pq.poll();
			// 만일, 방문한 적 있는 노드라면 제외한다.
			if (cur[1] > dist[cur[0]]) {
				continue;
			}
			// 해당 노드의 인접 노드를 방문하며 갱신할 수 있는 비용은 갱신한다.
			for (int i = 0; i < graph.get(cur[0]).size(); i++) {
				// 다음 방문 노드
				int nxt = graph.get(cur[0]).get(i);
				// 만일 다음 방문 노드의 현재 비용 > 현재 노드 + 다음 노드로의 방문 비용인 경우만 큐에 넣고 다음을 고려.
				if (dist[nxt] > dist[cur[0]] + 1) {
					dist[nxt] = dist[cur[0]] + 1;
					pq.offer(new int[] { nxt, dist[nxt] });
				}
			}

		}

		// 비용의 최댓값을 구하고, 최댓값의 개수를 구한다.
		int max = 0;
		for (int i = 1; i < n + 1; i++) {
			max = Math.max(max, dist[i]);
		}
		for (int i = 1; i < n + 1; i++) {
			if (dist[i] == max) {
				answer++;
			}
		}
		return answer;
	}
}
