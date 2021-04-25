package algo0421;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/***
 * <문제 요약>
 * 구해야 하는 것 : 출발지인 s에서 출발해서 A와 B까지 갈 때(합승가능), 최저 예상 택시요금을 출력
 * 문제 유형 : 다익스트라 사용
 * <풀이법 요약>
 * 1. 다익스트라로 시작점에서 갈 수 있는 모든 점들의 최소 비용을 구함
 * 2. A와 B에서 갈 수 있는 모든 점들의 최소 비용을 구함
 * 3. (S->A와 B) vs (S->i->A와 B)
 */

public class PM_2021KAKAO_합승택시요금_다익스트라 {
	
	static int INF = 20000002;
	static int[] start, ad, bd;
	static ArrayList<V>[] adj;
	
	static class V implements Comparable<V>{
		int to, cost;

		public V(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(V v) {
			return this.cost - v.cost;
		}
	}

	public static void main(String[] args) {
		int[][] fares = new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
			{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };
		System.out.println(solution(6, 4, 6, 2, fares));
	}
	
	public static int solution(int N, int S, int A, int B, int[][] fares) {
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		
		for (int i = 0; i < fares.length; i++) {
			int e1 = fares[i][0] - 1;
			int e2 = fares[i][1] - 1;
			int cost = fares[i][2];
			adj[e1].add(new V(e2, cost));
			adj[e2].add(new V(e1, cost));
		}
		
		start = new int[N];
		ad = new int[N];
		bd = new int[N];
		
		start = dijkstra(S - 1, N);
		ad = dijkstra(A - 1, N);
		bd = dijkstra(B - 1, N);
		
		return getMin(N, S - 1, A - 1, B - 1);
	}
	
	static int[] dijkstra(int start, int N) {
		int[] distance = new int[N];
		Arrays.fill(distance, INF);
		
		PriorityQueue<V> PQ = new PriorityQueue<>();
		PQ.add(new V(start, 0));
		distance[start] = 0;
		
		while (!PQ.isEmpty()) {
			int from = PQ.poll().to;
			
			for (V v : adj[from]) {
				if (distance[v.to] > distance[from] + v.cost) {
					distance[v.to] = distance[from] + v.cost;
					PQ.add(new V(v.to, distance[v.to]));
				}
			}
		}
		
		return distance;
	}
	
	static int getMin(int N, int S, int A, int B) {
		int ans = start[A] + start[B];
		for (int i = 0; i < N; i++) {
			ans = Math.min(ans, start[i] + ad[i] + bd[i]);
		}
		return ans;
	}
}