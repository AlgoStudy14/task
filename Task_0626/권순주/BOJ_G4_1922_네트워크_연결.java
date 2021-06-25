import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 모든 컴퓨터를 연결하는 최소 비용
 * 문제 유형 : MST
 * 
 * <풀이법 요약>
 * 1. 우선 순위 큐를 사용하여 가중치가 낮은 노드를 선택
 * 2. 임의의 시작점을 우선순위 큐에 넣고 시작
 * 3. 이미 방문한 노드라면 pass
 * 4. 방문 처리
 * 5. 가중치 결과값에 증가
 * 6. 현재 노드와 연결된 노드 중 방문하지 않은 노드만 우선순위 큐에 넣기
 * 7. N개의 노드를 방문할 때까지 3~6 반복
 * 
 * <피드백>
 * 프림 정리..!
 */

public class BOJ_G4_1922_네트워크_연결 {

	static class Node implements Comparable<Node> {
		int vertex;
		int weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static int N, M;
	static ArrayList<Node> list[];
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		// 각 정점간 연결
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, weight));
			list[to].add(new Node(from, weight));
		}

		// 시작점을 임의로 1로 두고 시작
		System.out.println(prim(1));
	}

	private static int prim(int start) {
		int count = 0, answer = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			// 꺼내기
			Node current = pq.poll();

			// 이미 방문한 노드라면 pass
			if (visited[current.vertex])
				continue;

			// 방문 처리
			visited[current.vertex] = true;

			// 방문했으니까, 가중치 증가
			answer += current.weight;

			// 현재 노드와 연결된 노드 중 방문하지 않은 노드만 큐에 넣어준다.
			for (Node next : list[current.vertex]) {
				if (!visited[next.vertex]) {
					pq.add(next);
				}
			}

			// N개의 노드를 모두 방문했을 때 끝
			if (++count == N)
				break;
		}

		return answer;
	}

}
