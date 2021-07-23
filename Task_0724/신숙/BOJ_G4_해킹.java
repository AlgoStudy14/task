import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_G4_해킹 {
	/*
	 * <문제>
	 * 해킹한 컴퓨터의 번호 C가 주어짐. 이 컴퓨터에 의존하는 컴퓨터들은 감염되기 시작하는데, 계속 전파됨. 
	 * 각 의존성이 주어지면 해킹당한 컴퓨터를 포함해 총 몇대의 컴퓨터가 감염되며 이에 걸리는 시간은? 
	 * 
	 * <문제풀이>
	 * 방향이 있는 a가 b에 의존적일 때 각 연결 거리(감염시간)가 s인 그래프.
	 * c부터 시작할테니(시작점이 정해짐) 다익스트라
	 *  
	 */
	static int T, N, D, C, a, b, s, ans;
	static int[] dist;
	static ArrayList<ArrayList<Node>> list;
	static PriorityQueue<Node> pq;
	static class Node implements Comparable<Node>{
		int link;
		int distance;
		
		public Node(int link, int d) {
			this.link = link;
			this.distance = d;
		}
		
		@Override
		public int compareTo(Node node) {
			return this.distance - node.distance;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			D = sc.nextInt();
			C = sc.nextInt();
			dist = new int[N + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			list = new ArrayList<>();
			for(int i = 0; i <= N; i++)
				list.add(new ArrayList<>());
			
			for(int i = 0; i < D; i++) {
				a = sc.nextInt();
				b = sc.nextInt();
				s = sc.nextInt();
				list.get(b).add(new Node(a, s));
			}
			dijkstra();
			
			ans = 0;
			int sum_d = 0;
			for(int i = 1; i <= N; i++) {
				if(dist[i] != Integer.MAX_VALUE) {
					ans++;
					sum_d = Math.max(sum_d, dist[i]);
				}
			}
			System.out.println(ans + " " + sum_d);
		}
	}
	
	static void dijkstra() {
		pq = new PriorityQueue<>();
		pq.offer(new Node(C, 0));
		dist[C] = 0;
		
		while(!pq.isEmpty()) {
			Node newN = pq.poll();
			if(newN.distance > dist[newN.link])
				continue;
			for(Node next : list.get(newN.link)) {
				if(dist[next.link] > dist[newN.link] + next.distance) {
					dist[next.link] = dist[newN.link] + next.distance;
					pq.offer(new Node(next.link, dist[next.link]));
				}
			}
		}
	}
}
