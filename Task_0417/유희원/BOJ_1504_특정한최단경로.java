package algo0417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
 * G4
 * 방향성이 없는 그래프가 주어지고 1번->N번 정점으로 최단거리로 이동하려고함
 * 한번 이동했던 정점, 간선 모두 방문 가능 but 최단 경로
 * 주어지는 v1,v2는 꼭 거치면서 지나야함
 */
public class BOJ_1504_특정한최단경로 {

	static int N,E;
	static int[] distance;
	static ArrayList<Node>[] list;
	
	//우선순위큐로 성능개선(안하면 시간초과)
	static class Node implements Comparable<Node>{
		int v,w;
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.w-o.w;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //정점개수
		E = sc.nextInt(); //간선개수
		list = new ArrayList[N+1]; //정점 인접리스트
		distance = new int[N+1]; //시작점과 다른 정점간의 최단경로
		for (int i = 1; i <=N; i++) {
			list[i] = new ArrayList<>();
		}
		//초기화
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt(); //출발
			int b = sc.nextInt(); //도착
			int c = sc.nextInt(); //가중치
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}
		//꼭 지나야하는 정점.  (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 
		int v1 = sc.nextInt();
		int v2 = sc.nextInt();
		
		long answer1=0, answer2 = 0;
		//1->v1->v2->n
		answer1 += dijkstra(1,v1);
		answer1 += dijkstra(v1,v2);
		answer1 += dijkstra(v2,N);
		//1->v2->v1->n
		answer2 += dijkstra(1,v2);
		answer2 += dijkstra(v2,v1);
		answer2 += dijkstra(v1,N);
		
		long result = Math.min(answer1, answer2);
		System.out.println(result>=Integer.MAX_VALUE?-1:result);
		
	}
	private static long dijkstra(int start, int end) {
		distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		PriorityQueue<Node> que = new PriorityQueue<>();
		que.add(new Node(start,0));
		while(!que.isEmpty()) {
			Node cur = que.poll();
			int vertex = cur.v;
			int weight = cur.w;
			//지금이 더 가중치가 크면 패스
			if(distance[vertex]<weight) continue;
			//해당 정점과 연결된것들 탐색
			for (int i = 0; i < list[vertex].size(); i++) {
				int vertex2 = list[vertex].get(i).v;
				int weight2 = list[vertex].get(i).w+weight;
				//더 작은 값 나오면 갱신
				if(distance[vertex2]>weight2) {
					distance[vertex2] = weight2;
					que.add(new Node(vertex2,weight2));
				}
			}
		}
		
		return distance[end];
	}

}
