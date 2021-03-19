import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * <다익스트라 알고리즘>
 * 비용 배열을 초기화한다.
 * 1. 비용이 가장 작은 노드를 선택한다(우선순위 큐 이용).
 * 2. 해당 노드의 인접노드의 비용을 갱신한다.
 */

class Vertex {
	int idx;
	int cost;

	Vertex(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

public class BOJ_G5_1753 {
	static int V, E, start;
	static ArrayList<ArrayList<Vertex>> graph;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		start = sc.nextInt();
		graph = new ArrayList<ArrayList<Vertex>>();
		for (int i = 0; i < V + 1; i++) {
			graph.add(new ArrayList<Vertex>());
		}
		// 그래프 초기화
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();

			graph.get(a).add(new Vertex(b, c));
		}

		// 비용 배열 초기화.
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		int[] dist = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		pq.offer(new Vertex(start, 0));

		while (!pq.isEmpty()) {
			Vertex curVertex = pq.poll();
			// 만일, 해당 노드 방문이 완료된 상태라면 무시.
			if (curVertex.cost > dist[curVertex.idx]) {
				continue;
			}
			// 그게 아니라면, 인접 노드 갱신 단계 진행.
			for (int i = 0; i < graph.get(curVertex.idx).size(); i++) {
				Vertex nxtVertex = graph.get(curVertex.idx).get(i);
				// 갱신 가능하다면 갱신.
				if (dist[nxtVertex.idx] > dist[curVertex.idx] + nxtVertex.cost) {
					dist[nxtVertex.idx] = dist[curVertex.idx] + nxtVertex.cost;
					// 갱신한 대상은 다음 pq의 대상.
					pq.offer(new Vertex(nxtVertex.idx, dist[nxtVertex.idx]));
				}
			}
		}

		for (int i = 1; i < V + 1; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}

		sc.close();
	}
}
