import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_Gold2_달빛여우 {
	/*
	 * <달빛 여우>
	 * 
	 * ???? 
	 * 다익스트라 2개 돌리는건 알겠는데.... 구글 참조...
	 * 이번꺼 전체적으로 난이도 높았던듯? 이분분법은 처음이라...
	 * 
	 * 늑대는 각 정점에 대해 2번 방문 가능. 짝수번째로 도착할 때와 홀수번째로 도착할 때.	=> 여우 : 1차원 배열, 늑대 : 2차원 배열 
	 * 
	 */
	static class Edge implements Comparable<Edge>{
		int tg, d, rest;
		public Edge(int target, int dist) {
			tg = target;
			d = dist;
		}
		public Edge(int target, int dist, int r) {
			this(target, dist);
			rest = r;
		}
		
		@Override
		public int compareTo(Edge o) {
			return d - o.d;
		}
	}
	
	static int N, M;
	static final int INF = 2000000000;
	static List<Edge>[] list = new ArrayList[4001];
	static int[][] wolf = new int[4001][2];
	static int[] fox = new int[4001];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			fox[i] = INF;
			Arrays.fill(wolf[i], INF);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[h].add(new Edge(t, w * 2));
			list[t].add(new Edge(h, w * 2));
		}
		
		Dijkstra_fox();
		Dijkstra_wolf();
		
		int cnt = 0;
		for(int i = 1; i <= N; i++)
			if(fox[i] < Math.min(wolf[i][1],  wolf[i][0]))
				cnt++;
		System.out.println(cnt);
	}
	
	private static void Dijkstra_fox() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		fox[1] = 0;
		
		pq.add(new Edge(1, 0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int cur = e.tg;
			int dist = e.d;
			
			if(fox[cur] < dist)
				continue;
			
			for(Edge ee : list[cur]) {
				int next = ee.tg;
				int nDist = dist + ee.d;
				if(fox[next] > nDist) {
					fox[next] = nDist;
					pq.add(new Edge(next, nDist));
				}
			}
		}
	}

	private static void Dijkstra_wolf() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		wolf[1][0] = 0;
		pq.add(new Edge(1, 0, 0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int cur = e.tg;
			int dist = e.d;
			int rest = e.rest;
			
			if(wolf[cur][rest] < dist)
				continue;
			
			for(Edge ee : list[cur]) {
				int next = ee.tg;
				int nDist = dist;
				int nRest = -1;
				//0이 홀수번째 => 빨리, 1이 짝수번째 => 느리게. 
				if(rest == 0) {
					nDist += ee.d / 2;
					nRest = 1;
				}else {
					nDist += ee.d * 2;
					nRest = 0;
				}
				
				if(wolf[next][nRest] > nDist) {
					wolf[next][nRest] = nDist;
					pq.add(new Edge(next, nDist, nRest));
				}
			}
		}
	}
}