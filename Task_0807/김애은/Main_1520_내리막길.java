package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1520_내리막길 {
	static class com implements Comparable<com>{
		int x,y,h;

		public com(int x, int y, int h) {
			super();
			this.x = x;
			this.y = y;
			this.h = h;
		}

		@Override
		public int compareTo(com o) {
			return o.h-this.h;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int arr[][] = new int[N][M];
		int count[][] = new int[N][M];
		boolean v[][] = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		count[0][0]=1;
		int dy[]= {0,0,-1,1};
		int dx[] = {1,-1,0,0};
		PriorityQueue<com> que = new PriorityQueue<>();
		que.add(new com(0,0,arr[0][0]));
		while(!que.isEmpty()) {
			com cur = que.poll();
			int y=cur.y;
			int x=cur.x;
	
			for (int i = 0; i < 4; i++) {
				int my=y+dy[i];
				int mx=x+dx[i];
				if(my<0 ||mx<0 || my>=N || mx>=M) continue;
				if(arr[my][mx]>=arr[y][x])continue;
				count[my][mx]+=count[y][x];
				if(v[my][mx])continue;
				v[my][mx]=true;
				que.add(new com(mx,my,arr[my][mx]));
			}
			
		}
		System.out.println(count[N-1][M-1]);
	}

}
