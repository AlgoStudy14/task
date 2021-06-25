import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 최소 스패닝 트리의 가중치 구하기
 * 문제 유형 : MST
 * 
 * <풀이 요약>
 * 1. 모든 간선 정보를 오름차순으로 정렬
 * 2. 가중치가 가장 작은 간선 선택
 * 3. 사이클이 생성되지 않는다면, 두 노드를 연결
 * 4. V-1 반복
 * 
 * <피드백>
 * 크루스칼 정리..!
 */

public class BOJ_G4_1197_최소_스패닝_트리 {

	static class Edge implements Comparable<Edge> {
		int nodeA;
		int nodeB;
		int weight;

		public Edge(int nodeA, int nodeB, int weight) {
			this.nodeA = nodeA;
			this.nodeB = nodeB;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	// V : 정점의 개수
	// E : 간선의 개수
	static int V, E;
	static int[] parent; // 부모 테이블
	static ArrayList<Edge> edges = new ArrayList<>(); // 간선 정보 저장 list
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 부모 테이블 초기화 (부모를 자기 자신으로)
		parent = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		// 모든 간선에 대한 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			edges.add(new Edge(A, B, C));
		}

		// 가중치 기준으로 정렬
		Collections.sort(edges);

		// 간선을 하나씩 확인하며
		for (Edge edge : edges) {
			int a = edge.nodeA;
			int b = edge.nodeB;
			int weight = edge.weight;
			// 사이클이 발생하지 않는 경우에만 집합에 포함
			if (!isConnect(a, b)) {
				unionParent(a, b);
				answer += weight;
			}
		}

		System.out.println(answer);
	}

	// 특정 원소가 속한 집합 찾기
	private static int findParent(int x) {
		// 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
		if (x == parent[x])
			return x;
		return parent[x] = findParent(parent[x]);
	}

	// 두 원소가 속한 집합을 합치기
	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	// 사이클이 발생하는지 확인
	private static boolean isConnect(int a, int b) {
		return findParent(a) == findParent(b);
	}

}
