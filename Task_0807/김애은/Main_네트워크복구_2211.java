package 백준;
/*
 * 프림으로 풀려고 했지만 실패
 * 보니까 하나씩 하는게 아니라 하나에서 두개가 나올 수 있기 때문에 아니다
 * 다익스트라로 접근
 * 다익스트라 원래 코드와 비슷한데 다른점은? 도착하는 배열을 만들어서 출발 값을 넣는것
 * 이걸 왜하나? 알려주기 위해서 도착하는 애들...출발..
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node3 implements Comparable<Node3>{
	int end,w;

	public Node3( int end, int w) {
		this.end = end;
		this.w = w;
	}

	@Override
	public int compareTo(Node3 o) {
		return this.w-o.w;
	}
	
}
public class Main_네트워크복구_2211 {
	static List<Node3> list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		list=new ArrayList[N+1];
		int ans[] = new int[N+1];
		boolean check[] = new boolean[N+1];
		for (int i = 1; i < N+1; i++) {
			list[i]=new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[s].add(new Node3(e,w));
			list[e].add(new Node3(s,w));
		}
		int dist[] = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1]=0;
		PriorityQueue<Node3> pq = new PriorityQueue<>();
		pq.add(new Node3(1,0));
		while(!pq.isEmpty()) {
			Node3 n = pq.poll();
			for(Node3 no:list[n.end]) {
				if(dist[no.end]>dist[n.end]+no.w) {
					dist[no.end]=dist[n.end]+no.w;
					ans[no.end]=n.end;
					pq.add(new Node3(no.end,dist[no.end]));
				}
			}
		}
//		check[1]=true;
//		Queue<int[]>que= new LinkedList<int[]>();
//		que.add(new int[] {1,Integer.MAX_VALUE});
//		int index=0;
//		while(!que.isEmpty()) {
//			if(ans.size()==N-1)break;
//			int cur[] = que.poll();
//			int start=cur[0];
//			int min=Integer.MAX_VALUE;
//			for(Node3 no : list[start]) {
//				if(check[no.end])continue;
//				if(min>no.w) {
//					min=no.w;
//					index=no.end;
//				}
//			}
//			check[index]=true;
//			que.add(new int[] {index,min});
//			ans.add(new int[] {start,index});
//		}
		System.out.println(N-1);
		for (int i = 2; i < ans.length; i++) {
			System.out.println(i+" "+ans[i]);
		}
//		
	}

}
