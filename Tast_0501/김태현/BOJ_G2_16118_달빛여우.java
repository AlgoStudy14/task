package s0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 달빛 여우가 먼저 도착할 수 있는 그루터기의 개수.
 * 문제 유형 : 다익스트라 알고리즘.
 * 주의 조건 : 거리 비용은 long형으로 선언하자, 무향 그래프.
 * <풀이법>
 * 1. 규칙에 따라 늑대의 움직임에 따른 최소 비용, 여우의 음직임에 따른 최소 비용을 저장한다.
 * 2. 여우의 움직임의 최소비용이 더 적은 지점을 찾는다(동시는 제외).
 * <주의사항>
 * 1. 늑대의 움직임에 대한 2차원 배열을 선언하는 기법은 다른 BFS문제에서도 많이 나왔듯이 잘 익혀야 겠다.
 * 2. Double로 선언하기보다는 2배를 해주는 기법을 사용하는 것이 좋음(최대한 부동소수점 연산은 지양하기). 
 */

public class BOJ_G2_16118_달빛여우 {
	static int N, M;
	static ArrayList<ArrayList<Node>> graph;

	static class Node {
		int idx, spd;
		long cost;

		Node(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}

		Node(int idx, long cost, int spd) {
			this(idx, cost);
			this.spd = spd;
		}
	}

	public static void main(String[] args) throws IOException {
		// 입력
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
			int d = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, d * 2));
			graph.get(b).add(new Node(a, d * 2));
		}

		// 양과 늑대의 거리 비용 초기화(long)
		long[] fox = new long[N + 1];
		long[][] wolf = new long[2][N + 1];
		for (int i = 0; i < N + 1; i++) {
			fox[i] = Long.MAX_VALUE;
			wolf[0][i] = Long.MAX_VALUE;
			wolf[1][i] = Long.MAX_VALUE;
		}
		fox[1] = 0;
		wolf[0][1] = 0;

		// 양 다익스트라 알고리즘
		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> (Long.compare(o1.cost, o2.cost)));
		pq.offer(new Node(1, 0)); // 시작 점은 비용 0.
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			// 현재 비용이 기존에 저장된 최소비용보다 큰 경우, 스킵.
			if (cur.cost > fox[cur.idx]) {
				continue;
			}

			for (int i = 0; i < graph.get(cur.idx).size(); i++) {
				// 다음 방문 노드
				Node nxt = graph.get(cur.idx).get(i);
				// 다음 방문지의 현재 최소 비용이 현재 선택된 최소 비용 + 다음 방문지까지의 거리보다 큰 경우.
				if (fox[nxt.idx] > cur.cost + nxt.cost) {
					fox[nxt.idx] = (cur.cost + nxt.cost);
					pq.offer(new Node(nxt.idx, fox[nxt.idx]));
				}
			}
		}

		// 늑대 다익스트라 알고리즘
		pq.clear();
		pq.offer(new Node(1, 0, 0)); // 시작 점은 비용 0, 달리는 상태(= 0).
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			// 현재 비용이 기존에 저장된 최소비용보다 큰 경우, 스킵.
			if (cur.cost > wolf[cur.spd][cur.idx]) {
				continue;
			}

			for (int i = 0; i < graph.get(cur.idx).size(); i++) {
				// 다음 방문 노드
				Node nxt = graph.get(cur.idx).get(i);
				// 다음 상태
				int nxtSpd = (cur.spd + 1) % 2;
				long nCost = ((cur.spd == 0) ? nxt.cost / 2 : nxt.cost * 2);
				// 늑대의 현 상태에 따라서 해당 비용 배열 갱신
				// 늑대의 다음 목적지의 특정 상태에서의 최소 비용이
				// 늑대의 현재 위치 상태의 최소비용 + 현재 위치 상태에서의 다음 노드로의 비용보다 큰 경우.
				if (wolf[nxtSpd][nxt.idx] > wolf[cur.spd][cur.idx] + nCost) {
					wolf[nxtSpd][nxt.idx] = wolf[cur.spd][cur.idx] + nCost;
					pq.offer(new Node(nxt.idx, wolf[nxtSpd][nxt.idx], nxtSpd));
				}
			}
		}
		
		// 최소 지점의 개수 세기
		int cnt = 0;
		for(int i = 2; i < N + 1; i++) {
			if(fox[i] < Math.min(wolf[0][i], wolf[1][i])) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
