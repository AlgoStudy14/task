import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 의존 관계에 있는 컴퓨터가 주어질 때, 감염되는 모든 컴퓨터의 개수와 그 시간.
 * 문제 유형 : 다익스트라 알고리즘.
 * 주의 사항 : 마지막 컴퓨터가 감염되는데 걸리는 최소 시간을 구한다.
 * <풀이 요약>
 * 1. 입력을 바탕으로 각 테스트케이스마다 그래프를 선언한다.
 * 2. 주어진 번호부터 시작하여, 다익스트라 탐색을 진행한다.
 * -> 해당 번호와 연결된 모든 다음 노드는 감염 대상이다. 감염 개수를 + 1한다.
 * -> 동시에 감염 시간도 더해준다.
 * <피드백>
 * 다익스트라 오랜만이네..? 잘 지냈지?
 */

public class BOJ_G4_10282_해킹 {
	static int T, n, d, c;
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
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			graph = new ArrayList<ArrayList<Node>>();
			for (int i = 0; i < n + 1; i++) {
				graph.add(new ArrayList<Node>());
			}
			// 그래프 입력
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				graph.get(b).add(new Node(a, c));
			}

			// 다익스트라.
			// 각 노드까지 걸리는 최소 시간.
			int[] dist = new int[n + 1];
			for (int i = 1; i < n + 1; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			dist[c] = 0;
			
			// 마지막 노드 기억
			PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
			q.offer(new Node(c, 0));
			while (!q.isEmpty()) {
				Node cur = q.poll();

				for (int i = 0; i < graph.get(cur.idx).size(); i++) {
					// 다음 방문 노드
					Node nxt = graph.get(cur.idx).get(i);

					// 다음 방문 노드의 현재 비용이, 현재 방문지점에서 다음 노드로 방문하는 비용보다 큰 경우 작은 값으로 갱신
					if(dist[nxt.idx] > dist[cur.idx] + nxt.cost) {
						dist[nxt.idx] = dist[cur.idx] + nxt.cost;
						// 다음 방문을 위에 노드 번호 큐에 넣기.
						q.offer(new Node(nxt.idx, dist[nxt.idx]));
					}

				}
			}
			
			// 감염된 노드의 개수
			int cnt = 0;
			// 최대 걸린 시간
			int max = 0;
			for(int i = 1; i < n + 1; i++) {
				if(dist[i] != Integer.MAX_VALUE) {
					cnt++;
					max = Math.max(max, dist[i]);
				}
			}

			// 결과 출력
			System.out.printf("%d %d\n", cnt, max);
		}
	}
}
