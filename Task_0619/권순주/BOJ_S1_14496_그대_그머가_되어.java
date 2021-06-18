import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : a에서 b로 가는 최단 거리
 * 문제 유형 : 다익스트라
 * 주의 사항 : 최단 거리의 MAX 값 1000 -> 1005, 최단 거리가 존재하지 않을 떄 -1을 출력
 * 
 * <풀이 요약>
 * 1. 다익스트라를 실행한 뒤 a에서 b로 가는 최단거리를 출력한다.
 * 
 * <피드백>
 * 능숙함을 기르는데 아주 좋은 문제
 * 
 * 개인 문제 변경 지름길 -> 그대, 그머가 되어
 */

public class BOJ_S1_14496_그대_그머가_되어 {

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

	public static final int MAX = 1005;
	static int a, b, N, M;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		dist = new int[N + 1];
		Arrays.fill(dist, MAX);

		// 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, 1));
			graph.get(to).add(new Node(from, 1));
		}

		// 풀이
		System.out.println(solve());
	}

	// 문제 해결
	private static int solve() {
		// 다익스트라 실행
		dijkstra(a);
		// b까지의 최단 거리 출력
		return dist[b] == MAX ? -1 : dist[b];
	}

	// 다익스트라
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			// 현재 노드가 이미 처리된 적이 있는 노드라면 무시
			if (dist[current.index] < current.distance) {
				continue;
			}
			for (Node next : graph.get(current.index)) {
				// 현재 노드르 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
				if (dist[current.index] + next.distance < dist[next.index]) {
					dist[next.index] = dist[current.index] + next.distance;
					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}
	}

}
