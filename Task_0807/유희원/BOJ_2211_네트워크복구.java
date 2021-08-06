package algo0806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2211_네트워크복구 {
	
	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static int N, M, cnt;
	static int[] dist, path;
	static List<Edge>[] edgeList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			edgeList = new ArrayList[N + 1];
			for (int i = 1; i <= N; ++i) {
				edgeList[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				edgeList[A].add(new Edge(B, C));
				edgeList[B].add(new Edge(A, C));
			}

			dijkstra();

			StringBuilder sb = new StringBuilder();
			for (int v = 2; v <= N; ++v) {
				if (path[v] == 0) continue;
				cnt++;
				sb.append(v + " " + path[v] + "\n");
			}
			
			System.out.println(cnt);
			System.out.println(sb);
		
	}

	public static void dijkstra() {
		dist = new int[N + 1];
		path = new int[N + 1];
		Arrays.fill(dist, (int)1e9);
		dist[1] = 0;
		Queue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0));

		while(!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.cost > dist[cur.to]) continue;

			for (Edge edge : edgeList[cur.to]) {
				if (dist[edge.to] > cur.cost + edge.cost) {
					dist[edge.to] = cur.cost + edge.cost;
					path[edge.to] = cur.to; //경로 저장
					pq.offer(new Edge(edge.to, dist[edge.to]));
				}
			}
		}
	}
}
