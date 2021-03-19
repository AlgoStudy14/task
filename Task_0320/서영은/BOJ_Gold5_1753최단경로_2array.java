package algo210313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_Gold5_1753최단경로_2array {
	static int[] dist;
	static int[][] ad;
	static int V, E, startV;
	static int INF = 1000;

	static void DijkstraMethod(int start) {
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		dist[start] = 0;
		pq.offer(new Vertex(start, dist[start]));
		
		while(!pq.isEmpty()) {
			int here = pq.peek().index;
			int cost = pq.peek().distance;
			pq.poll();
			
			if(cost > dist[here]) {
				continue;
			}
			
			for (int i = 1; i <= V; i++) {
				if(ad[here][i]!=0 && dist[i] > dist[here] + ad[here][i]) {
					dist[i] = dist[here] + ad[here][i];
					pq.offer(new Vertex(i, dist[i]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		startV = Integer.parseInt(in.readLine());
		
		dist=new int[V+1];
		ad=new int[V+1][V+1];

		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			ad[u][v]=w;
		}
		
		//입력 끝 
		
		// 로직 
		Arrays.fill(dist, INF);
		
		DijkstraMethod(startV);
		
		//촐력 
		for (int i = 1; i <= V; i++) {
			if(dist[i]==1000) {
				System.out.println("INF");
				continue;
			}
			System.out.println(dist[i]);
		}
	}
}

class Vertex implements Comparable<Vertex>{
	int index;
	int distance;

	public Vertex(int index, int distance) {
		super();
		this.index = index;
		this.distance = distance;
	}

	@Override
	public int compareTo(Vertex o) {
		return this.distance - o.distance;
	}
	
	
}