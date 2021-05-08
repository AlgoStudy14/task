package study0501;
/*
 * 진짜 어렵다...
 * 다익스트라 변형문제
 * 다시 풀어볼것
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_16118_달빛여우 {
	static class Edge implements Comparable<Edge>{
		int y,r;
		int w;

		public Edge(int y, int fox_arr) {
			super();
			this.y = y;
			this.w = fox_arr;
		}
		
		public Edge(int y, int w, int r) {
			super();
			this.y = y;
			this.w = w;
			this.r = r;
		}


		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}
	static List<Edge>[]list;
	static int N,M;
	static int[] fox_arr;
	static int[][] n_arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		fox_arr=new int[N+1];
		list=new ArrayList[N+1];
		n_arr=new int[N+1][2];
		for(int i=1;i<=N;i++) {
			list[i]=new ArrayList<>();
			fox_arr[i]=Integer.MAX_VALUE;
			Arrays.fill(n_arr[i],Integer.MAX_VALUE);
		}
		for(int i=0;i<M;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int d=sc.nextInt();
			list[a].add(new Edge(b,d*2));
			list[b].add(new Edge(a,d*2));
		}
		dikstra(1);
		dikstra(1,0);
		int cnt=0;
		for(int i=1;i<=N;i++) {
			if(fox_arr[i]<Math.min(n_arr[i][1], n_arr[i][0]))cnt++;
		}
		System.out.println(cnt);
	}
	private static void dikstra(int start, int cnt) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		n_arr[start][cnt]=0;
		pq.add(new Edge(start,cnt,0));
		while(!pq.isEmpty()) {
			Edge st=pq.poll();
			int cur=st.y;
			int dist=st.w;
			int re=st.r;
			
			if(n_arr[cur][re]<dist)continue;
			for(Edge ed : list[cur]) {
				int next=ed.y;
				int d=dist;
				int r=-1;
				if(re==1) {
					d+=ed.w*2;
					r=0;
				}
				else {
					d+=ed.w/2;
					r=1;
				}
				if(n_arr[next][r]>d) {
					n_arr[next][r]=d;
					pq.add(new Edge(next,d,r));
				}
			}
			
		}
	}
	private static void dikstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start,0));
		fox_arr[start]=0;
		while(!pq.isEmpty()) {
			Edge st = pq.poll();
			int cur=st.y;
			if(fox_arr[cur]<st.w)continue;
			for(Edge ed:list[cur]) {
				if(fox_arr[ed.y]>fox_arr[cur]+ed.w) {
					fox_arr[ed.y]=fox_arr[cur]+ed.w;
					pq.add(new Edge(ed.y,fox_arr[ed.y]));
				}
			}
		}
	}

}
