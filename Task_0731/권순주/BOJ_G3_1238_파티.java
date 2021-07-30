import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 :
 * 문제 유형 : 다익스트라
 * 제약 조건 : 1000(학생 수)*100(최대 시간)=100000
 */

public class BOJ_G3_1238_파티 {

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

	static final int INF = 100005;
	static int N, M, X;
	// startD : 출발
	// endD : 도착 -> 반대로 구할 예정
	static int[] startD, endD;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static ArrayList<ArrayList<Node>> reserveGraph = new ArrayList<>(); // 반대 그래프 저장

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		startD = new int[N + 1];
		endD = new int[N + 1];
		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
			reserveGraph.add(new ArrayList<Node>());
		}
		// 최단 거리 테이블 초기화
		Arrays.fill(startD, INF);
		Arrays.fill(endD, INF);
		// 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			reserveGraph.get(b).add(new Node(a, c));
		}
		// 다익스트라 실행
		// X에서 출발
		dijkstra(X, startD, graph);
		// X에 도착
		dijkstra(X, endD, reserveGraph);
		// 출력
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}
			answer = Math.max(answer, startD[i] + endD[i]);
		}
		System.out.println(answer);
	}

	static void dijkstra(int start, int[] d, ArrayList<ArrayList<Node>> graph) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (d[now.index] < now.distance) {
				continue;
			}
			for (Node next : graph.get(now.index)) {
				if (d[now.index] + next.distance < d[next.index]) {
					d[next.index] = d[now.index] + next.distance;
					pq.offer(new Node(next.index, d[next.index]));
				}
			}
		}
	}

}
