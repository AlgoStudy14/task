package algo210403;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <문제 요약> 
 * 구해야 하는 것: 1번 노드로브터 가장 멀리 떨어진 노드가 몇개인지 return 
 * 제약 사항: n은 2 이상 20000 이하(노드의 개수) / 간선은 양방향(=무방향이라는 뜻) 
 * 문제 유형: 다익스트라 
 * 요구 개념: 다익스트라
 * 
 * <풀이법 요약> 
 * 1. 간선 리스트 이용 하는 방법으로 풀어보기 
 * -> pq 이용하는 방법으로!
 */
public class PM_Level3_가장먼노드_pq {

	public static void main(String[] args) {
		PM_Level3_가장먼노드_pq pm = new PM_Level3_가장먼노드_pq();

		System.out.println(pm.solution(6, new int[][] {{3,6},{4,6},{3,2},{1,3},{1,2},{2,4},{5,2}}));

	}

	class Edge implements Comparable<Edge>{
		int v;
		int w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}

	public int solution(int n, int[][] edge) {
		List<Edge>[] edgeList = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			edgeList[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < edge.length; i++) {
			edgeList[edge[i][0]-1].add(new Edge(edge[i][1]-1, 1));
			edgeList[edge[i][1]-1].add(new Edge(edge[i][0]-1, 1));
		}
		
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[n];
		
		dist[0] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.offer(new Edge(0, 0));
		
		while(!pq.isEmpty()) {
			Edge curEdge = pq.poll();
			int cur = curEdge.v;
			
			if(visited[cur]) continue;
			
			visited[cur] = true;
			
			for(Edge next : edgeList[cur]) {
				int v = next.v;
				if(dist[v] > dist[cur] + next.w) {
					dist[v] = dist[cur] + next.w;
					pq.offer(new Edge(v, dist[v]));
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			if(dist[i] > max) {
				max = dist[i];
			}
		}

		int answer = 0;
		
		for (int i = 0; i < n; i++) {
			if(max == dist[i]) ++answer;
		}
		return answer;
	}

}
