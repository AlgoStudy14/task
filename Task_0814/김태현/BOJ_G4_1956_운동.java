package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 사이클의 비용이 가장 작은 경우를 구하여라.
 * 문제 유형 : 다익스트라 알고리즘.
 * 주의 사항 : 도로는 일방 통행 도로이다.
 * <풀이 요약>
 * 1. 모든 점에 대하여 자신의 점으로 돌아올 때 까지 다익스트라 알고리즘을 진행한다.
 * -> 이때, 출발 지점에서의 dist 값을 0으로 초기화하지 않는다.
 * 2. 자신의 정점으로 돌아오는 순간의 값을 최소값과 비교하여 갱신한다.
 * -> 이때, 값이 갱신되지 않았다면 불가능한 경우이다.
 * <피드백>
 * 자신을 방문하면 끝내는 디테일이 2배의 속도차를 만든다 ㅅㄱ
 */
public class BOJ_G4_1956_운동 {
	static int V, E, min;
	static ArrayList<ArrayList<Node>> graph;

	static class Node {
		int idx, cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		min = 1_000_000_000;

		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, c));
		}

		for (int v = 1; v <= V; v++) {
			int[] dist = new int[V + 1];
			for (int i = 0; i <= V; i++) {
				dist[i] = Integer.MAX_VALUE;
			}

			PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
			pq.offer(new Node(v, 0));

			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				if (dist[cur.idx] < cur.cost)
					continue;
				if (cur.idx == v && cur.cost != 0)
					break;

				for (Node nxt : graph.get(cur.idx)) {
					if (dist[nxt.idx] > cur.cost + nxt.cost) {
						dist[nxt.idx] = cur.cost + nxt.cost;
						pq.offer(new Node(nxt.idx, dist[nxt.idx]));
					}
				}
			}

			min = Math.min(min, dist[v]);
		}
		if (min == 1_000_000_000)
			System.out.println(-1);
		else
			System.out.println(min);
	}
}
