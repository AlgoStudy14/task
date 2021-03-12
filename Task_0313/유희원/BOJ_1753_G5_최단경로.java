package dijkstra;
/*
 * 첫째줄부터 V개의 줄에 걸쳐 i번째 줄에 i번 정점으로의 최단 경로의 값을 출력. 
 * 경로 존재하지 않을 경우 INF
 * 시작점 자신은 0
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1753_최단경로 {

	static int V,E,K, u,v,w;
	static final int INF = 100000000;
	static int[] distance;
	static List<List<Node>> list;
	static class Node implements Comparable<Node>{
		int index, dist;
		
		public Node(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node n) {
			
			return this.dist-n.dist;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//정점의 개수
		V = sc.nextInt();
		//간선의 개수
		E = sc.nextInt();
		//시작 정점의 번호
		K = sc.nextInt();
		
		distance = new int[V+1];
		list = new ArrayList<List<Node>>();
		list.add(new ArrayList<Node>());
		//배열 초기화
		Arrays.fill(distance, INF);
		for (int i = 1; i <=V; i++) {
			list.add(new ArrayList<Node>());
		}
		for (int i = 0; i <E; i++) {
			//u->v 가중치 w, u!=v, w<=10
			u = sc.nextInt();
			v = sc.nextInt();
			w = sc.nextInt();
			list.get(u).add(new Node(v,w));
		}
		dijkstra(list,distance,K);
		
		//출력
		for (int i = 1; i <=V; i++) {
			if(distance[i] == INF) System.out.println("INF");
			else System.out.println(distance[i]);
		}

	}
	private static void dijkstra(List<List<Node>> list, int[] distance, int K) {
		boolean[] visited = new boolean[V+1];
		//우선순위큐 사용
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		distance[K] = 0;
		pq.add(new Node(K,0));
		
		while(!pq.isEmpty()) {
			int now = pq.poll().index;
			//방문하지 않은 노드 중에서 가장 비용이 작은 노드 선택
			if(visited[now]) continue;
			visited[now] = true;
			
			for(Node node: list.get(now)) {
				if(distance[node.index]>distance[now]+node.dist) {
					distance[node.index] = distance[now] + node.dist;
					pq.add(new Node(node.index, distance[node.index]));
				}
			}
		}
		
	}

}
