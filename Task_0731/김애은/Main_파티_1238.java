package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_파티_1238 {
	static int N,M,X;
	static int max = Integer.MIN_VALUE;
	static boolean check[];
	static List<Node> list[];
	static int num[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		list= new ArrayList[N+1];
		
		for (int i = 1; i < N+1; i++) {
			list[i]=new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to,w));
		}
		dikstra(X);//돌아오는거 먼저 계산하기
		int[]back=new int[N];
		back=num.clone();
		for (int i = 1; i <= N; i++) {//가는거 계산하기
			if(i==X)continue;
			dikstra(i);
			max=Math.max(max, back[i]+num[X]);
		}
		System.out.println(max);
	}
	private static void dikstra(int start) {
		check=new boolean[N+1];
		num= new int[N+1];
		Arrays.fill(num, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start,0));
		num[start]=0;
		while(!pq.isEmpty()) {
			Node curnode = pq.poll();
			int cur=curnode.end;
			if(check[cur])continue;
			check[cur]=true;
			for(Node no : list[cur]) {
				if(num[no.end]>num[cur]+no.val) {
					num[no.end]=num[cur]+no.val;
					pq.add(new Node(no.end,num[no.end]));
				}
			}
		}
	}

}
