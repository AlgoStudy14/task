package s0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <문제요약>
 * 구해야 하는 것 : 주어진 두 정점을 거치면서 1번에서 N번 정점으로 이동하는 최단 경로.
 * 문제 유형 : 다익스트라 알고리즘.
 * 주요 조건 : 무방향 그래프, 정점^2 > 간선의 수(우선순위큐), 반드시 두 정점을 지나야 함, 경로가 없으면 -1 출력.
 * <풀이법>
 * 1. 1 -> A -> B -> N의 최소 경로를 구한다(다익스트라).
 * 2. 1 -> B -> A -> N의 최소 경로를 구한다(다익스트라).
 * 3. 두 경로 중 작은 경로를 선택한다. 이때, 이동할 수 없는 경우라면 -1을 출력한다.
 * <주의 사항>
 * A, B로 가는 경로 중 짧은 경로를 먼저 택하는 그리디한 방법을 사용하면 안된다.
 */

public class BOJ_G4_1504_특정한최단경로 {
	static int N, E;
	static ArrayList<ArrayList<Node>> graph;
	static int A, B;
	static int[] dist;
	static int ans1, ans2;

	static class Node {
		int idx;
		int cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Node>());
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 무방향 그래프
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		ans1 = 0;
		ans2 = 0;

		// 다익스트라 알고리즘
		dijkstra(1);
		// 두 경유지의 도착 순서에 따라 두 가지 경우 모두 고려
		ans1 += dist[A];
		ans2 += dist[B];
		dijkstra(A);
		ans1 += dist[B];
		ans2 += dist[N];
		dijkstra(B);
		ans1 += dist[N];
		ans2 += dist[A];
		int ans = Math.min(ans1, ans2);
		if (ans >= 200_000_001) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}

	private static void dijkstra(int start) {
		// 비용 배열 초기화
		dist = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			dist[i] = 200_000_001;
		}
		dist[start] = 0;

		// 우선순위큐 비용 오름차순
		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		pq.offer(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			// 만일, 방문한적 있으면 스킵(현재 꺼낸 노드까지의 비용이 현재 기록된 최소 비용보다 큰 경우).
			if (dist[cur.idx] < cur.cost) {
				continue;
			}
			for (int i = 0; i < graph.get(cur.idx).size(); i++) {
				Node nxt = graph.get(cur.idx).get(i);
				// 만일, 현재 노드에서 다음 방문 노드까지의 거리 합이 다음 노드의 기존 거리 비용의 최소 값보다 작은 경우만 갱신후 큐에 넣기.
				if (dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
					dist[nxt.idx] = dist[cur.idx] + nxt.cost;
					pq.offer(new Node(nxt.idx, dist[nxt.idx]));
				}
			}
		}
	}
}
