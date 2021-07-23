import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* (완)
 * <문제 요약>
 * 문제 정의 : 해킹당한 컴퓨터의 개수와 걸리는 시간 구하기
 * 문제 유형 : 다익스트라
 * 제약 조건 : 10000(컴퓨터 최대)*1000(감염 시간 최대)=10000000
 * 
 * <피드백>
 * 문제 잘읽자 
 * 
 */

public class BOJ_G4_10282_해킹 {

	static class Node implements Comparable<Node> {
		int index;
		int distance;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}

	}

	static final int INF = 10000005;
	static int T, n, d, c;
	static ArrayList<ArrayList<Node>> graph;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			dist = new int[n + 1];
			// 그래프 초기화
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<Node>());
			}
			// 최단 거리 테이블 초기화
			Arrays.fill(dist, INF);
			// 입력
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				graph.get(b).add(new Node(a, s));
			}
			// 다익스트라 실행
			dijkstra(c);
			// 출력
			int max = -1, cnt = 0;
			for (int i = 1; i <= n; i++) {
				if (dist[i] != INF) {
					cnt++;
					max = Math.max(max, dist[i]);
				}
			}
			System.out.println(cnt + " " + max);
		}
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (dist[now.index] < now.distance) {
				continue;
			}
			for (Node next : graph.get(now.index)) {
				if (dist[now.index] + next.distance < dist[next.index]) {
					dist[next.index] = dist[now.index] + next.distance;
					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}
	}

}
