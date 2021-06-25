package algo0626;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1916_최소비용구하기 {

	static ArrayList<Node>[] list;
	static int[] dist;
	static class Node implements Comparable<Node>{
		int i,j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return o.j-this.j;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		list = new ArrayList[N+1];
		dist = new int[N+1];
		
		for (int i = 1; i <= N; i++) dist[i] = 999999999;
		for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int value = sc.nextInt();
			
			list[u].add(new Node(v,value));
			
		}
		
		int start = sc.nextInt();
		int end = sc.nextInt();
		
		dijk(start);
		System.out.println(dist[end]);
	}
	private static void dijk(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start,0));
		dist[start]=0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(dist[cur.i]<cur.j) continue;
			
			int size = list[cur.i].size();
			for (int i = 0; i < size; i++) {
				Node cur2 = list[cur.i].get(i);
				if(dist[cur2.i]>dist[cur.i]+list[cur.i].get(i).j) {
					dist[cur2.i]=dist[cur.i]+list[cur.i].get(i).j;
					pq.add(new Node(cur2.i, dist[cur2.i]));
				}
			}
		}
		
	}

}
