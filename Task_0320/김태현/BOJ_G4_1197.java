import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_1197 {

	static int parent[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(stz.nextToken());
		int e = Integer.parseInt(stz.nextToken());
		parent = new int[v + 1];
		for (int i = 1; i <= v; i++)
			parent[i] = i;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < e; i++) {
			stz = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stz.nextToken());
			int b = Integer.parseInt(stz.nextToken());
			int w = Integer.parseInt(stz.nextToken());
			// 간선 정보를 저장한다.
			pq.offer(new Edge(a, b, w));
		}

		int weight = 0;
		// 모든 간선을 확인할 때 까지 반복.
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			// 만일, 정점 v1과 v2의 부모가 같지 않다면
			if (find(now.v1) != find(now.v2)) {
				// 합집합.
				union(now.v1, now.v2);
				// 전체 가중치는 더해주기.
				weight += now.weight;
			}
		}

		// 최소 스패닝 트리 가중치.
		System.out.println(weight);
	}

	public static void union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);

		// 더 작은 노드는 최종 부모로 결정해준다.
		if (p1 < p2)
			parent[p2] = p1;
		else
			parent[p1] = p2;
	}

	public static int find(int n) {
		// 내가 부모 그 자체라면 재귀 종료.
		if (parent[n] == n)
			return n;
		// 압축 기법.
		return parent[n] = find(parent[n]);
	}

	static class Edge implements Comparable<Edge> {
		int v1, v2, weight;

		Edge(int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		// 해당 노드의 정렬 기준을 미리 정해준다(자바).
		public int compareTo(Edge e) {
			return weight - e.weight;
		}
	}
}