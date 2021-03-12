import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 최단 경로
 * 문제 핵심 요약 : 다익스트라 알고리즘
 * 
 * <풀이법 요약>
 * 다익스트라 알고리즘 순서
 * 1. 출발 노드를 설정한다.
 * 2. 출발 노드를 기준으로 각 노드의 최소 비용을 저장한다.
 * 3. 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택한다.
 * 4. 해당 노드를 거쳐서 특정한 노드로 가는 경우를 고려하여 최소 비용을 갱신한다.
 * 5. 위 과정에서 3~4번을 반복한다.
 * 
 * 아래 코드는 나동빈님의 강의를 참고하여 작성하였습니다!
 */


public class BOJ_G5_1753_최단경로 {

	static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10먹을 설정
	static int V, E, K;
	// 각 모드에 연결되어 있는 노드에 대한 정보를 담는 배열 (2차원 배열)
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	// 최단 거리 테이블 만들기
	static int[] d;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		d = new int[V + 1];
		
		// 그래프 초기화
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<Node>());
        }
		
		for (int i = 0; i < E; i++) {
			// 간선 정보 입력받기
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// u번 노드에서 v번 노드로 가는 비용이 w
			graph.get(u).add(new Node(v, w));
		}

		// 최단 거리 테이블을 모두 무한으로 초기화
		Arrays.fill(d, INF);

		// 다익스트라 알고리즘을 수행
		dijkstra(K);

		// 모든 노드로 가기 위한 최단 거리를 출력
		for (int i = 1; i <= V; i++) {
			// 도달할 수 없는 경우 INF 출력
			if (d[i] == INF) {
				System.out.println("INF");
			}
			// 도달할 수 있는 경우 거리를 출력
			else {
				System.out.println(d[i]);
			}
		}
	}

	public static void dijkstra(int start) {
		// 가장 작은 값을 가져오는 시간 복잡도가 logN이기 때문에 사용
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
		pq.offer(new Node(start, 0));
		d[start] = 0; // 자기자신으로 가는 경우
		while (!pq.isEmpty()) { // 큐가 비어있지 않다면
			// 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
			Node node = pq.poll();
			int dist = node.getDistance(); // 현재 노드까지의 비용
			int now = node.getIndex(); // 현재 노드
			// 현재 노드가 이미 처리된 적이 있는 노드라면 무시
			if (d[now] < dist)
				continue;
			// 현재 노드와 연결된 다른 인접한 노드들을 확인
			for (int i = 0; i < graph.get(now).size(); i++) {
				int cost = d[now] + graph.get(now).get(i).getDistance();
				// 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
				if (cost < d[graph.get(now).get(i).getIndex()]) {
					d[graph.get(now).get(i).getIndex()] = cost;
					pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
				}
			}
		}
	}

}

class Node implements Comparable<Node> {

	private int index;
	private int distance;

	public Node(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}

	public int getIndex() {
		return this.index;
	}

	public int getDistance() {
		return this.distance;
	}

	// 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
	@Override
	public int compareTo(Node other) {
		return this.distance - other.distance;
	}

}
