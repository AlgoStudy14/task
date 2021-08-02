package algo0801;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 * <문제 요약>
 * 문제 정의 : 슈퍼컴퓨터가 다른 컴퓨터들과 통신하는데 걸리는 시간이 최소가 되는 개수와 회선을 출력
 * 문제 유형 : 다익스트라
 * <풀이 요약>
 * 1번 조건에서 최소 개수의 회선만 복구해 연결만 하면 프림이 적절했겠지만,
 * 2번 조건 때문에 출발지(슈퍼컴퓨터)로부터 모든 정점으로의 최소 비용을 구할 수 있는 다익스트라 사용
 * 
 * 1. 다익스트라를 이용해 슈퍼컴퓨터로부터 모든 컴퓨터로의 최소 비용과 경로를 구해야 함
 * 2. 최소 비용 테이블(distance)가 갱신될 때마다 어디를 거쳐가는지(pre) 갱신
 * 3. 출력
 *  3-1. 슈퍼컴퓨터와 모든 점을 연결하기 위해 (N - 1)개의 회선을 복구해야 함
 *  3-2. pre 테이블에는 현재 인덱스로 올 때 직전에 어디를 방문했는지 기록이 되어있기 때문에
 *  	 인덱스와 pre[인덱스]를 출력
 */

public class BOJ_G2_2211_네트워크복구 {

	static int N, M, start = 1, INF = 10000002;
	static int[] distance, pre;
	static ArrayList<Pair>[] adj;
	
	static class Pair implements Comparable<Pair> {
		int to, w;

		public Pair(int to, int w) {
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Pair p) {
			return this.w - p.w;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int p1 = sc.nextInt();
			int p2 = sc.nextInt();
			int cost = sc.nextInt();
			
			adj[p1].add(new Pair(p2, cost));
			adj[p2].add(new Pair(p1, cost));
		}
		
		dijkstra();
		print();
	}
	
	static void print() {
		System.out.println(N - 1);
		for (int i = start + 1; i <= N; i++) {
			System.out.println(i + " " + pre[i]);
		}
	}
	
	static void dijkstra() {
		distance = new int[N + 1];
		pre = new int[N + 1];
		Arrays.fill(distance, INF);
		
		PriorityQueue<Pair> PQ = new PriorityQueue<>();
		PQ.add(new Pair(start, 0));
		distance[start] = 0;
		
		while (!PQ.isEmpty()) {
			int from = PQ.poll().to;
			
			for (Pair p : adj[from]) {
				if (distance[p.to] > distance[from] + p.w) {
					distance[p.to] = distance[from] + p.w;
					PQ.add(new Pair(p.to, distance[p.to]));
					pre[p.to] = from; 
				}
			}
		}
	}

}
