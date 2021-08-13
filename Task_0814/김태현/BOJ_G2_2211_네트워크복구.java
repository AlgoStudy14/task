package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 조건을 만족하며 복구할 최소 회선을 구하여라.
 * 문제 유형 : 다익스트라 알고리즘.
 * <풀이 요약>
 * 1. 다익스트라 알고리즘을 통해 노드를 방문하는 모든 경우를 기록해준다(최소값이 갱신되는 모든 경우).
 * <피드백>
 * 어차피 갱신될 때 덮어쓰면 된다는 논리를 이용하여 pre 배열을 만든다!
 */

public class BOJ_G2_2211_네트워크복구 {
	static int N, M;
	static ArrayList<ArrayList<Node>> graph;

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Node>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 완전 쌍방향
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		// 최소 복구 회선의 개수 출력
		System.out.println(N - 1);
		// 다익스트라 알고리즘
		int[] dist = new int[N + 1];
		int[] pre = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (dist[cur.idx] < cur.cost)
				continue;

			for (int i = 0; i < graph.get(cur.idx).size(); i++) {
				Node nxt = graph.get(cur.idx).get(i);

				if (dist[nxt.idx] > cur.cost + nxt.cost) {
					dist[nxt.idx] = cur.cost + nxt.cost;
					pq.offer(new Node(nxt.idx, dist[nxt.idx]));
					// 도착지가 갱신되는 경우 해당 지점을 기억해둔다.
					pre[nxt.idx] = cur.idx;
				}
			}
		}
		
		for(int i = 2; i < N + 1; i++) {
			System.out.println(i + " " + pre[i]);
		}
	}
}
