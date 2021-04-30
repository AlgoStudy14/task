package algo0430;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 구해야 하는 것 : 여우가 늑대보다 먼저 도착할 수 있는 그루터기의 개수를 출력
 * 제약 사항 : 여우는 일정한 속도, 늑대는 오솔길 하나마다 두 배의 속도와 절반의 속도로 반복, 둘의 이동 경로가 서로 다를 수도 있음 
 * 문제 유형 : 다익스트라 알고리즘 사용
 * <풀이법 요약>
 * 0. 여우와 늑대의 속도 차이를 위해 각 오솔길에 x2를 해줌
 * 1. 여우와 늑대가 각 그루터기까지 가는데 필요한 최소 비용을 다익스트라를 사용해 계산
 * 2. 늑대의 경우, 빠르게 이동하는 경우와 느리게 이동하는 경우를  나눠서 탐색해야 하므로 비용을 배열을 2개에 저장!
 * 
 * c.f) 스캐너 사용 시 시간초과;
 * 		더 이상 볼 필요없는 케이스 가지치기 하지 않을 경우 시간초과 발생
 */

public class BOJ_G2_16118_달빛여우 {

	static final int INF = 800000001;
	static int N, M;
	static ArrayList<V>[] adj;
	static int[] foxDistance;
	static int[][] wolfDistance;
	
	static class V implements Comparable<V> {
		int to, d, fast;
		
		V (int to, int d) {
			this.to = to;
			this.d = d;
		}
		
		V (int to, int d, int fast) {
			this.to = to;
			this.d = d;
			this.fast = fast;
		}
		
		@Override
		public int compareTo(V v) {
			return this.d - v.d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) * 2;
			adj[v1].add(new V(v2, d));
			adj[v2].add(new V(v1, d));			
		}
		
		fDijkstra();
		wDijkstra();

		System.out.println(count());
	}
	
	// 여우 다익스트라
	static void fDijkstra() {
		foxDistance = new int[N];
		Arrays.fill(foxDistance, INF);
		
		PriorityQueue<V> PQ = new PriorityQueue<>();
		PQ.add(new V(0, 0));
		foxDistance[0] = 0;
		
		while (!PQ.isEmpty()) {
			V cur = PQ.poll();
			int from = cur.to;

			// 현재 큐에서 가져온 비용이 지금까지 저장한 최소 비용보다 크다면 더 볼 필요 없음
			if (cur.d > foxDistance[from]) continue;
			
			for (V v : adj[from]) {
				if (foxDistance[v.to] > foxDistance[from] + v.d) {
					foxDistance[v.to] = foxDistance[from] + v.d;
					PQ.add(new V(v.to, foxDistance[v.to]));
				}
			}
		}
	}
	
	// 늑대 다익스트라
	static void wDijkstra() {
		// 늑대가 빠르게 이동하는 경우 + 느리게 이동할 경우
		wolfDistance = new int[2][N];
		Arrays.fill(wolfDistance[0], INF);
		Arrays.fill(wolfDistance[1], INF);
		
		PriorityQueue<V> PQ = new PriorityQueue<>();
		// 처음 빠르게 이동하는 경우(상태 = 1)
		PQ.add(new V(0, 0, 1));
		wolfDistance[1][0] = 0;
		
		while (!PQ.isEmpty()) {
			V cur = PQ.poll();
			int from = cur.to;
			int fast = cur.fast;

			// 현재 큐에서 가져온 비용이 지금까지 저장한 최소 비용보다 크다면 더 볼 필요 없음
			if (cur.d > wolfDistance[fast][from]) continue;
			
			for (V v : adj[from]) {
				int distance = fast == 1 ? v.d / 2 : v.d * 2;	// 늑대의 상태에 따라 걸리는 시간 계산
				int state = (fast + 1) % 2;						// 현재 상태와 반대로(빨->느, 느->빨)
				
				if (wolfDistance[state][v.to] > wolfDistance[fast][from] + distance) {
					wolfDistance[state][v.to] = wolfDistance[fast][from] + distance;
					PQ.add(new V(v.to, wolfDistance[state][v.to], state));
				}
			}
		}
	}
	
	// 여우가 늑대보다 먼저 도착할 수 있는 그루터기의 개수 카운팅
	static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (foxDistance[i] < Math.min(wolfDistance[0][i], wolfDistance[1][i])) ++cnt;
		}
		return cnt;
	}
}
