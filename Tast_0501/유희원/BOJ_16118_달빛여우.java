package algo0501;
/*
 * G2
 * 
 * 여우와 늑대가 1번나무에서 시작해 목표지점으로 이동
 * 늑대는 하나는 여우의 속도 두배, 그다음은 절반의 속도로 이동->반복
 * 여우가 늑대보다 먼저 도착할 수 있는 나무 개수
 * 
 * 구글링&시간초과 대환장
 */
import java.util.*;
public class BOJ_16118_달빛여우 {

	static int N,M;
	static class Animal implements Comparable<Animal>{
		int idx;
		long time;
		boolean isFasted;
		int state;

		public Animal(int idx, long time) {
			super();
			this.idx = idx;
			this.time = time;
		}

		public Animal(int idx, long time, int state) {
			super();
			this.idx = idx;
			this.time = time;
			this.state = state;
		}

		@Override
		public int compareTo(Animal o) {
			// TODO Auto-generated method stub
			return Long.compare(this.time, o.time);
		}
		
	}
	static List<Animal>[] graph;
	static long[] fox;
	static long[][] wolf;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		graph = new ArrayList[N+1];
		fox = new long[N+1];
		wolf = new long[2][N+1];
		
		Arrays.fill(fox, Long.MAX_VALUE);
		Arrays.fill(wolf[0], Long.MAX_VALUE);
		Arrays.fill(wolf[1], Long.MAX_VALUE);
		
		for (int i = 1; i <=N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i <M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			long weight = sc.nextInt()*2;
			graph[from].add(new Animal(to, weight));
			graph[to].add(new Animal(from, weight));
		}
		dijkstra(N);
		dijkstraWolf(N);
		int count = 0;
		for (int i = 2; i <=N; i++) {
			if(fox[i]<Math.min(wolf[0][i], wolf[1][i])) count++;
		}
		System.out.println(count);
	}
	private static void dijkstra(int n) {
		Queue<Animal> que = new PriorityQueue<>();
		que.offer(new Animal(1, 0));
		fox[1] = 0;
		while(!que.isEmpty()) {
			Animal cur = que.poll();
			if(fox[cur.idx]<cur.time) continue;
			for(Animal a : graph[cur.idx]) {
				long cost = fox[cur.idx]+a.time;
				if(fox[a.idx]>cost) {
					fox[a.idx] = cost;
					que.offer(new Animal(a.idx, cost));
				}
			}
		}
		
	}
	private static void dijkstraWolf(int n) {
		Queue<Animal> que = new PriorityQueue<>();
		que.offer(new Animal(1, 0, 0));
		wolf[0][1] = 0;
		while(!que.isEmpty()) {
			Animal cur = que.poll();
			if(wolf[cur.state][cur.idx]<cur.time) continue;
			for(Animal a : graph[cur.idx]) {
				int state = 1 - cur.state;
				long cost = wolf[cur.state][cur.idx]
						+(cur.state==0?a.time/2:a.time*2);
				if(wolf[state][a.idx]>cost) {
					wolf[state][a.idx] = cost;
					que.offer(new Animal(a.idx, cost, state));
				}
			}
		}
		
	}

}
