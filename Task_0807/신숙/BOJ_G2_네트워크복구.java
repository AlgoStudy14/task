import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_G2_네트워크복구 {
	/*
	 * <문제>
	 * 1 <= N <= 1000
	 * 몇몇 컴퓨터들에 연결이 되어 통신이 가능함.
	 * 회선은 성능이 차이날 수 있고 그에 따라 걸리는 시간이 서로다름.
	 * 해커가 네트워크에 침입하여 모든 회선과 컴퓨터를 차단 후에 해커의 공격을 막을 수 있었는데, 공격을 받으면 슈퍼컴퓨터에 이 공격이 전달되고
	 * 그러면 보안패킷을 전송하는 방식으로 보안적용.
	 * 1. 최소 개수의 회선만을 사용해 복구해야함. 
	 * 2. 복구해서 통신이 되는것도 중요하지만 걸리는 시간도 중요함. 통신하는데 걸리는 최소 시간이 원래 네트워크 보다 걸리는 최소 시간보다 커져서는 안됨.
	 * 
	 * <문제>
	 * 다익스트라.
	 */
	static int N, M;
	static int[] dist, way;
	static ArrayList<Node>[] arr;
	
	static class Node implements Comparable<Node>{
		int to;
		int time;
		Node(int to, int time){
			this.to = to;
			this.time = time;
		}
		public int compareTo(Node n) {
			return this.time - n.time;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			arr[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			int f = sc.nextInt();
			int t = sc.nextInt();
			int time = sc.nextInt();
			arr[f].add(new Node(t, time));
			arr[t].add(new Node(f, time));
		}
		
		dijkstra();
		System.out.println(N - 1);
		for(int i = 2; i <= N; i++)
			System.out.println(i + " " + way[i]);
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>(); 
		dist = new int[N + 1];
		way = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.offer(new Node(1, 0));
		dist[1] = 0;
		
		while(!pq.isEmpty()) {
			int from = pq.poll().to;
			for(Node n : arr[from]) {
				if(dist[n.to] > dist[from] + n.time) {
					dist[n.to] = dist[from] + n.time;
					pq.offer(new Node(n.to, dist[n.to]));
					way[n.to] = from;
				}
			}
		}
	}
}
