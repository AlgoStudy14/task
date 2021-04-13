package algo210417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 1->N 정점으로 가는 두 개의 정점을 지나는 최단 경로의 길이
 * 제약 사항: 한번 이동했던 정점, 이동했던 간선 다시 이동 가능. 반드시 주어진 두 정점을 거치면서 최단경로로 이동해야 한다.
 * 경로가 없을 때에는 -1을 출력
 * 문제 유형: 다익스트라, 조합
 * 요구 개념: 다익스트라
 * 
 * <풀이법 요약>
 * 다익스트라 -> 정점 중심의 그래프 => 인접 행렬 or 인접 리스트 사용 => 간선이 몇개 없으므로 인접리스트 사용하자
 * 한 정점에서 다른 정점으로 가는 최단경로를 구해야 함 
 * 
 * 주어진 두 정점이 a,b라면,
 * 1->a->b->N
 * 1->b->a->N
 * 두 경우 중 하나를 구하면 되므로, 각 -> 에서 모두 다익스트라를 이용하여 최단경로 구하기(한번 이동했던 정점과 간선을 다시 이동해도 되므로 가능한 풀이)
 * 
 * 와 제발 문제 꼼꼼히 읽자 변수 잘못써서 계속 틀린거 말 되냐..
 */
public class BOJ_Gold4_1504_특정한최단경로 {

	static class Vertex implements Comparable<Vertex>{
		int v;
		int dist;

		public Vertex(int v, int dist) {
			super();
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.dist-o.dist;
		}
		
	}
	
	static int N, E;
	static List<Vertex>[] adjList;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<Vertex>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Vertex(to, d));
			adjList[to].add(new Vertex(from, d));
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int OneToA=0,AtoB=0,BtoA=0,BtoN=0,oneToB=0,AtoN=0;
		
		//1->a->b->N
		boolean successFirst = true;
		boolean AtoBSuccess = true;
		int oneToA = Dijkstra(1,a);
		if(oneToA == INF) {
			successFirst = false;
		}
		
		if(successFirst) {
			AtoB = Dijkstra(a,b);
			
			if(AtoB == INF) {
				successFirst = false;
				AtoBSuccess = false;
			}
		}
		
		if(!AtoBSuccess) {
			System.out.println(-1);
			return;
		}
		
		if(successFirst) {
			BtoN = Dijkstra(b,N);
			
			if(BtoN == INF) {
				successFirst = false;
			}
		}
		
		//1->b->a->N
		boolean successSecond = true;
		oneToB = Dijkstra(1,b);
		if(oneToA == INF) {
			successSecond = false;
		}
		
		if(successSecond) {
			AtoN = Dijkstra(a,N);
			
			if(BtoN == INF) {
				successSecond = false;
			}
		}
		
		if(!successFirst && !successSecond) {
			System.out.println(-1);
			return;
		} else if(successFirst && !successSecond) {
			System.out.println(oneToA + AtoB + BtoN);
			return;
		} else if(!successFirst && successSecond) {
			System.out.println(oneToB + AtoB + AtoN);
			return;
		} else if(successFirst && successSecond) {
			System.out.println(Math.min(oneToA + AtoB + BtoN, oneToB + AtoB + AtoN));
			return;
		}
	}

	private static int Dijkstra(int start, int dest) {
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		
		Arrays.fill(distance, INF);

		//시작 정점 세팅
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		distance[start] = 0;
		pq.offer(new Vertex(start, 0));
		
		
		while(!pq.isEmpty()) {
			// 남아 있는 정점 중 가장 작은 정점 선택
			Vertex minVertex = pq.poll();
			
			if(visited[minVertex.v]) continue;
			visited[minVertex.v] = true;
			
			if(minVertex.v == dest) break;
			
			for(Vertex next: adjList[minVertex.v]) {
				if(distance[next.v] > next.dist + distance[minVertex.v]) {
					distance[next.v] = next.dist + distance[minVertex.v];
					pq.offer(new Vertex(next.v, distance[next.v]));
				}
			}
		}
		return distance[dest];
	}
}
