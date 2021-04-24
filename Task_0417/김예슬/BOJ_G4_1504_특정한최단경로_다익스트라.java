package algo0424;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 1번 정점에서 N번 정점으로 갈 때, v1과 v2를 반드시 거쳐가는 최단 경로의 길이를 출력
 * 문제 유형 : 다익스트라 사용
 * <풀이법 요약>
 * 1. 다익스트라를 사용해 현재 위치에서 도착지까지 갈 수 있는 최단 경로의 길이를 저장
 * 2. (1->v1->v2->N) vs (1->v2->v1->N)
 *  2-1. 만약 INF의 값을 초과할 경우 경로가 없는 경우이므로 -1을 출력
 */

public class BOJ_G4_1504_특정한최단경로_다익스트라 {

	static final int INF = 800001;
	static int N, E;
	static ArrayList<Vertex>[] adjList;
	
	static class Vertex implements Comparable<Vertex> {
		int to, cost;

		public Vertex(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Vertex v) {
			return this.cost - v.cost;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			int e1 = sc.nextInt() - 1;
			int e2 = sc.nextInt() - 1;
			int cost = sc.nextInt();
			adjList[e1].add(new Vertex(e2, cost));
			adjList[e2].add(new Vertex(e1, cost));
		}
		
		int v1 = sc.nextInt() - 1;
		int v2 = sc.nextInt() - 1;
		
		int[] start = new int[N];
		int[] dv1 = new int[N];
		int[] dv2 = new int[N];
		int[] end = new int[N];
		
		start = dijkstra(0);
		dv1 = dijkstra(v1);
		dv2 = dijkstra(v2);
		end = dijkstra(N - 1);
		
		int onetotwo = start[v1] + dv1[v2] + dv2[N - 1];
		int twotoone = start[v2] + dv2[v1] + dv1[N - 1];
		int min = Math.min(onetotwo, twotoone);
		
		System.out.println(min >= INF ? -1 : min);
	}
	
	static int[] dijkstra(int start) {
		int[] distance = new int[N];
		Arrays.fill(distance, INF);
		
		PriorityQueue<Vertex> PQ = new PriorityQueue<>();
		PQ.add(new Vertex(start, 0));
		distance[start] = 0;
		
		while (!PQ.isEmpty()) {
			int from = PQ.poll().to;
			
			for (Vertex v : adjList[from]) {
				if (distance[v.to] > distance[from] + v.cost) {
					distance[v.to] = distance[from] + v.cost;
					PQ.add(new Vertex(v.to, distance[v.to]));
				}
			}
		}
		
		return distance;
	}
}
