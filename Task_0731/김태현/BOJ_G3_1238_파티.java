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
 * 문제 정의 : 자신의 위치에서 출발하여 자신에게 돌아오는데 걸리는 최장 시간은?
 * 문제 유형 : 다익스트라 알고리즘.
 * <풀이 요약>
 * 1. 우선 도착 노드에서 출발 노드로 가는 모든 최소 비용을 구한다.
 * 2. 모든 노드에 대해서 다익스트라 알고리즘을 진행한다.
 * -> 모든 노드에 대하여 도착 노드로 가는 최소 거리를 구한다.
 * -> 노드에 도착하는 순간 이동한 거리를 위의 값과 더하여 최대를 갱신한다.
 * <피드백>
 * 아.. 다익스트라 다 잊어버림..
 * 1. Node(현재 노드, 해당 노드까지의 비용).
 * 2. 비교 : 다음 노드까지의 최소 비용 > 현재 노드까지의 비용 + 다음 노드 까지의 비용
 * 이때, 갱신되어가는 노드와 다음 노드가 약간 달라서 헷갈릴 수 있음.
 */

public class BOJ_G3_1238_파티 {
	static int N, M, X;
	static ArrayList<ArrayList<Node>> graph;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		graph = new ArrayList<ArrayList<Node>>();
		max = Integer.MIN_VALUE;

		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 단방향 그래프
			graph.get(a).add(new Node(b, c));
		}

		int[] back = new int[N + 1];
		for (int j = 0; j < N + 1; j++) {
			back[j] = Integer.MAX_VALUE;
		}

		// 시작 지점으로 들어가는 비용은 0
		back[X] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		// 우선 도착 노드에 대하여 모두 계산한다.
		pq.offer(new Node(X, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			for (int j = 0; j < graph.get(cur.nxt).size(); j++) {
				Node nxt = graph.get(cur.nxt).get(j);
				// 다음 노드의 현재까지의 최소 거리 > 다음 노드까지의 비용 + 현재 노드까지의 최소 비용 인 경우에만
				if (back[nxt.nxt] > back[cur.nxt] + nxt.cost) {
					back[nxt.nxt] = back[cur.nxt] + nxt.cost;
					pq.offer(nxt);
				}
			}
		}

		// 모든 노드에 대해서 진행
		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;

			int[] dist = new int[N + 1];
			for (int j = 0; j < N + 1; j++) {
				dist[j] = Integer.MAX_VALUE;
			}

			// 시작 지점으로 들어가는 비용은 0
			dist[i] = 0;

			pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
			// 첫 번째 노드를 먼저 저장한다.
			pq.offer(new Node(i, 0));

			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				if (dist[cur.nxt] < cur.cost) {
					continue;
				}

				if (cur.nxt == X) {
					max = Math.max(max, dist[X] + back[i]);
					break;
				}

				for (int j = 0; j < graph.get(cur.nxt).size(); j++) {
					Node nxt = graph.get(cur.nxt).get(j);
					// 다음 노드의 현재까지의 최소 거리 > 다음 노드까지의 비용 + 현재 노드까지의 최소 비용 인 경우에만
					if (dist[nxt.nxt] > cur.cost + nxt.cost) {
						dist[nxt.nxt] = cur.cost + nxt.cost;
						pq.offer(new Node(nxt.nxt, dist[nxt.nxt]));
					}
				}
			}
		}

		System.out.println(max);
	}

	static class Node {
		int nxt, cost;

		Node(int nxt, int cost) {
			this.nxt = nxt;
			this.cost = cost;
		}
	}
}
