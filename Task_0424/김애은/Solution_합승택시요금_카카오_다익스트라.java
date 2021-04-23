package 다익스트라;
/*
 * 할말하않.... 다익스트라로 풀었다가 진짜 엄청 오래걸렸다...
 * 최대한 많은 중복 조건을 삭제했다.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_합승택시요금_카카오 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n=6,s=4,a=6,b=2;
		int fares[][]= {{4,1,10},{3,5,24},{5,6,2},{3,1,41},{5,1,24},{4,6,50},{2,4,66},{2,3,22},{1,6,25}};
		System.out.println(solution(n,s,a,b,fares));
	}
	static class Edge implements Comparable<Edge>{
		int y,w;

		public Edge(int y, int w) {
			super();
			this.y = y;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}
	static List<Edge> list[];
    public static int solution(int n, int s, int a, int b, int[][] fares) {
    	list = new ArrayList[n+1];
    	for(int i=1;i<n+1;i++) {
    		list[i]=new ArrayList<>();
    	}
    	for(int i=0;i<fares.length;i++) {
    		int fare[] = fares[i];
    		int start = fare[0];
    		int end = fare[1];
    		int weight =fare[2];
    		list[start].add(new Edge(end,weight));
    		list[end].add(new Edge(start,weight));
    	}
    	ArrayList<Integer> ar = new ArrayList<>();
    	for(int i=1;i<n+1;i++) { 
    		int[] p = dijkstra(n,i,a);
    		int[] t = dijkstra(n,s,i);
    		ar.add(p[a]+p[b]+t[i]);
    	}
    	Collections.sort(ar);;
    	return ar.get(0);
    }
	private static int[] dijkstra(int n,int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int dist[] = new int[n+1];
		Arrays.fill(dist, 20000001);
		pq.add(new Edge(start,0));
		dist[start]=0;
		
		while(!pq.isEmpty()) {
			Edge q=pq.poll();
			int cur=q.y;
            if(q.w>dist[cur])continue;
			for(Edge ed:list[cur]) {
				int cost = dist[cur]+ed.w;
				if(ed.w!=0 && cost<dist[ed.y]) {
					dist[ed.y]=cost;
					pq.add(new Edge(ed.y,dist[ed.y]));
				}
			}
		}
		return dist;
	}
}
