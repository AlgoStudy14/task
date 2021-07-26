package algo0725;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력
 * 문제 유형 : 다익스트라
 * 주의 사항 :  
 * <풀이 요약>
 * 1~N에서 X로 가는 비용과 X에서 1~N로 가는 비용이 필요하므로 최단 거리 테이블이 2개 필요
 * 1. X에서 1~N으로의 최소 비용은 시작점을 X로 설정 후 다익스트라를 이용해 구함
 * 2. 1~N에서 X로의 최소 비용은 기존의 인접리스트를 뒤집어 시작점을 X로 설정 후 다익스트라를 이용해 구함
 */

public class BOJ_G3_1238_파티_다익스트라 {

	static int N, M, X, INF = 1000002;
	static ArrayList<Pair>[] adj, reverse;
	
	static class Pair implements Comparable<Pair> {
		int to, t;
		
		public Pair(int to, int t) {
			this.to = to;
			this.t = t;
		}
		
		@Override
		public int compareTo(Pair p) {
			return this.t - p.t;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt() - 1;
		
		adj = new ArrayList[N];
		reverse = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt() - 1;
			int to = sc.nextInt() - 1;
			int cost = sc.nextInt();
			adj[from].add(new Pair(to, cost));
			reverse[to].add(new Pair(from, cost));
		}
		
		int[] xton = dijkstra(adj);
		int[] ntox = dijkstra(reverse);
		
		int max = 0;
		for (int i = 0; i < N; i++)
			max = Math.max(max, ntox[i] + xton[i]);
		
		System.out.println(max);
	}
	
	static int[] dijkstra(ArrayList<Pair>[] adjList) {
		// 다익스트라를 사용해 최소 비용을 저장할 테이블
		int[] cost = new int[N];
		Arrays.fill(cost, INF);
		
		PriorityQueue<Pair> PQ = new PriorityQueue<>();
		PQ.add(new Pair(X, 0));
		cost[X] = 0;
		
		// X에서 모든 점으로 가는 최소 비용
		while (!PQ.isEmpty()) {
			int from = PQ.poll().to;
			
			for (Pair p : adjList[from]) {
				if (cost[p.to] > cost[from] + p.t) {
					cost[p.to] = cost[from] + p.t;
					PQ.add(new Pair(p.to, cost[p.to]));
				}
			}
		}

		return cost;
	}
}
