import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_Gold4_1504 {
	/*
	 * <특정한 최단 경로>
	 * 양방향 간선들이 주어진 그래프.
	 * 1번 정점으로부터 N번까지 최단경로로 이동하고자 함.
	 * 반드시 지나야하는 두 점이 주어지는데 이 두 점을 반드시 거치면서 최단경로로 이동하는 거리는?
	 * 
	 * <풀이방법>
	 * 1번에서 시작하는 최단경로 : 다익스트라.
	 * 다익스트라를 최단경로를 3구간으로 나누어서 풀이.
	 * 1 : 1번 -> 반드시 지나야 하는 것중 1 -> 2 -> N
	 * 2 : 1번 -> 반드시 지나야 하는 것중 2 -> 1 -> N
	 * 둘 중 최소값 비교.
	 * 해당하는 경로가 없을 경우 -1 출력.
	 * 
	 * <결과>
	 * 메모리 : 306068KB
	 * 시간 : 1332ms
	 * 
	 * 별로인거같은데
	 */
	
	public static class Node implements Comparable<Node>{
		int index;
		int distance;
		
		Node(int i, int d){
			index =  i;
			distance = d;
		}
		@Override
		//거리가 짧은 순으로 정렬.
		public int compareTo(Node n) {
			return distance <= n.distance ? -1 : 1;
		}
	}
	
	static int N, E;
	static int[][] graph;
	static ArrayList<Node>[] arr;
	static PriorityQueue<Node> pq;
	static boolean[] visit;
	static int[] min_d;
	static int limit = 200000000;		// Node E 개수 20만개, 최대 거리 1000 최악의 경우 2억.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		min_d = new int[N+1];
		arr = new ArrayList[N+1];
		for(int i = 1; i <= N; i++)
			arr[i] = new ArrayList<>();
		
		int f, t, v;
		for(int i = 0; i < E; i++) {
			f = sc.nextInt();
			t = sc.nextInt();
			v = sc.nextInt();
			arr[f].add(new Node(t, v));
			arr[t].add(new Node(f, v));
		}
		//반드시 거쳐야하는 서로 다른 정점 번호.
		int mustA = sc.nextInt();
		int mustB = sc.nextInt();
		
		int ans1 = dijkstra(1, mustA) + dijkstra(mustA, mustB) + dijkstra(mustB, N);
		int ans2 = dijkstra(1, mustB) + dijkstra(mustB, mustA) + dijkstra(mustA, N);
		int ans = Math.min(ans1, ans2);
		System.out.println(ans > limit ? -1 : ans);
	}
	
	static int dijkstra(int start, int dest) {
		pq = new PriorityQueue<Node>();
		pq.offer(new Node(start, 0));
		visit = new boolean[N+1];
		Arrays.fill(min_d, Integer.MAX_VALUE);
		min_d[start] = 0;
		visit[start] = true;
		
		Node n;
		int cur, w;
		while(!pq.isEmpty()) {
			n = pq.poll();
			cur = n.index;
			w = n.distance;
			//현재 가중치가 더 크다면 갱신할필요 X
			if(min_d[cur] < w)
				continue;
			
			for(Node node : arr[cur]) {
				//갱신할 조건. 위에서 뽑은 노드에 연결된 것들 중 현재 저장되어있는 최소 거리가 더 짧게 가능 방법이 있을 경우에.
				if(min_d[node.index] > min_d[cur] + node.distance) {
					min_d[node.index] = min_d[cur] + node.distance;
					pq.offer(new Node(node.index, min_d[node.index]));
				}
			}
			
		}
		return min_d[dest];
	}
}
