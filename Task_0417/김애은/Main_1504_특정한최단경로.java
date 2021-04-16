package study0417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1504_특정한최단경로 {
	static int N,E;
	static class Node implements Comparable<Node>{
		int a,b;
		
		public Node(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Node o) {
			return this.b-o.b;
		}
	}
	static ArrayList<Node> []list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		E=sc.nextInt();
		
		list= new ArrayList[N+1];
		for(int i=1;i<N;i++) {
			list[i]=new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			int z= sc.nextInt();
			list[x].add(new Node(y,z));
			list[y].add(new Node(x,z));
		}
		int p1 = sc.nextInt();
		int p2 = sc.nextInt();
		
		int len1 = dijkstra(1,p1)+dijkstra(p1,p2)+dijkstra(p2,N);
		int len2 = dijkstra(1,p2)+dijkstra(p2,p1)+dijkstra(p1,N);
		
		int min = Math.min(len1, len2);
		if(min==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);

	}
	private static int dijkstra(int start, int end) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(start,0));
		int dist[] = new int[N+1];
		boolean[] V= new boolean[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start]=0;
		
		while(!que.isEmpty()) {
			Node cur = que.poll();
			if(V[cur.a])continue;
			V[cur.a]=true;
			for(int i=0;i<list[cur.a].size();i++) {
				Node next = list[cur.a].get(i);
				if(!V[next.a] && dist[next.a]>dist[cur.a]+next.b) {
					dist[next.a]=dist[cur.a]+next.b;
					que.offer(new Node(next.a,dist[next.a]));
				}
			}
		}
		return dist[end];
	}

}
