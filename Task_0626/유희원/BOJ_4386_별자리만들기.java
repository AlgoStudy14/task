package algo0626;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4386_별자리만들기 {
	
	static class Node implements Comparable<Node>{
		
		int a,b;
		double weight;

		public Node(int a, int b, double weight) {
			super();
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(weight, o.weight);
		}
		
	}
	static class Star{
		int n;
		double x,y;
		public Star(int n, double x, double y) {
			super();
			this.n = n;
			this.x = x;
			this.y = y;
		}

		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Star star[] = new Star[N];
		parent = new int[N];
		for (int i = 0; i <N; i++) {
			parent[i] = i;
		}
		for (int i = 0; i <N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());
			star[i] = new Star(i, a, b);
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for (int i = 0; i <N-1; i++) {
			for (int j = i+1; j < N; j++) {
				pq.offer(new Node(i, j, Math.sqrt(Math.pow(star[i].x-star[j].x,2)+Math.pow(star[i].y-star[j].y, 2))));
			}
		}
		double weight = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(find(cur.a)!=find(cur.b)) {
				union(cur.a,cur.b);
				weight+=cur.weight;
			}
		}
		System.out.println(Math.round(weight*100)/100.0);
	}
	static int parent[];
	public static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);
        
        if(p1 > p2)
            parent[p1] = p2;
        else
            parent[p2] = p1;
    }
    
    public static int find(int n) {
        if(parent[n] == n)
            return n;
        
        return parent[n] = find(parent[n]);
    }
	

}
