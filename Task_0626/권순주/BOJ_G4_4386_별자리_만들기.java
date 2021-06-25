import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 2차원 평면 위에 놓여있는 별들로 만들 수 있는 별자리의 최소 비용
 * 문제 유형 : MST
 * 
 * <풀이 요약>
 * 1. 입력받은 별자리로 만들 수 있는 간선 정보를 저장한다.
 * 2. 모든 간선 정보를 오름차순으로 정렬
 * 3. 가중치가 가장 작은 간선 선택
 * 4. 사이클이 생성되지 않는다면, 두 노드를 연결
 * 5. V-1 반복
 * 
 */

public class BOJ_G4_4386_별자리_만들기 {

	static class Edge implements Comparable<Edge> {
		int nodeA;
		int nodeB;
		double weight;

		public Edge(int nodeA, int nodeB, double weight) {
			this.nodeA = nodeA;
			this.nodeB = nodeB;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.weight < o.weight) {
				return -1;
			}
			return 1;
		}

	}

	// n : 정점 간선의 개수
	static int n;
	static int[] parent; // 부모 테이블
	static double[][] star;
	static ArrayList<Edge> edges = new ArrayList<>();
	static double answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());

		// 부모 테이블 초기화 (부모를 자기 자신으로)
		parent = new int[n];
		for (int i = 1; i < n; i++) {
			parent[i] = i;
		}

		// 노드 입력
		star = new double[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}

		// 모든 간선에 대한 정보 입력
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				double distance = Math
						.sqrt(Math.pow(star[i][0] - star[j][0], 2) + Math.pow(star[i][1] - star[j][1], 2));
				edges.add(new Edge(i, j, distance));
			}
		}

		// 가중치 기준으로 정렬
		Collections.sort(edges);

		// 간선을 하나씩 확인
		for (Edge edge : edges) {
			int a = edge.nodeA;
			int b = edge.nodeB;
			double distance = edge.weight;

			// 사이클이 발생하지 않는 경우만 포함
			if (!isConnect(a, b)) {
				answer += distance;
				unionParent(a, b);
			}
		}

		System.out.printf("%.2f", answer);
	}

	// 루트 노드 찾기
	private static int findParent(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = findParent(parent[x]);
	}

	// 두 집합 합치기
	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	// 사이클 확인
	private static boolean isConnect(int a, int b) {
		return findParent(a) == findParent(b);
	}

}
