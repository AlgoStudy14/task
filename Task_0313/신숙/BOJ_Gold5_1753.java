package baekjoon;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_Gold5_1753 {
	/*
	 * 최단경로 - 다익스트라 알고리즘.
	 * https://swexpertacademy.com/main/visualcode/main.do#/home/editor//
	 * 시각화 참고자료.
	 * 
	 * 다익스트라 알고리즘
	 * 	 	ㄴ 어떤 변도 가중치를 갖지 않는 유향 그래프에서 주어진 출발점과 도착점 사이의 최단 경로 문제를 푸는 알고리즘.
	 * 1. distance는 min 비교를 하기위해 MAX_VALUE;
	 * 2. 자기자신인 시작노드의 거리를 0으로 표시. 시작노드 위치의 check 배열을 true로 바꾸기.
	 * 3. 1차로 시작노드와 연결된 노드들의 distance로 값 갱신.
	 * 4. 그 후 방문하지 않은 노드들 중에서 distance 값이 최소인 노드인 min_node를 찾음.
	 * 5. 마찬가지로 방문한 것이므로 min_node의 방문배열을 true로 변경.
	 * 6. 연결된 것들을 distance 값 갱신해야 함.
	 * 		주의점은 min_node와 연결된 distance값이  distacne[min_node] + min_node와 그 거리보다 큰 경우에
	 * 										distacne[min_node] + min_node로 변경
	 * 그 후 4-6 반복
	 * 
	 * 위 자료에선 Map[from][to] 로 하며 짯으나, 해당 문제는 20000*20000이 되어버리기에, 배열로.
	 */
	
	public static class Node implements Comparable<Node>{
		int index;
		int distance;
		
		Node(int i, int d){
			index =  i;
			distance = d;
		}
		@Override
		//거리가 짧은 순으로 정렬.
		public int compareTo(Node n) {
			return distance <= n.distance ? -1 : 1;
		}
	}
	
	static ArrayList<Node>[] list;
	static int min_d[];
	static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();	//점 개수
		int e = sc.nextInt();	//연결 개수
		int k = sc.nextInt();	//시작 번호
		//1~v 까지 넣을 것이기 때문에 초기화는 v+1
		list = new ArrayList[v+1];		//리스트를 2차원으로
		min_d = new int[v+1];
		//초기화
		for(int i = 1; i <= v; i++) {
			min_d[i] = INF;
			list[i] = new ArrayList<Node>();		
		}
		//리스트에 넣기.
		for(int i = 0; i < e; i++)
			list[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));
		
		dijkstra(k);
		//1번 노드부터 출력.
		for(int i = 1; i <= v; i++) {
			if(min_d[i] == INF)
				System.out.println("INF");
			else
				System.out.println(min_d[i]);
		}
	}
	
	static void dijkstra(int k) {
		// 4번 항목인 그다음 최소 distance를 방문하기 위하여 우선순위 큐를 사용하는 듯.
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		min_d[k] = 0;
		pq.offer(new Node(k, min_d[k]));
		
		while(!pq.isEmpty()) {
			Node n = pq.peek();
			int cur = n.index;
			int d = n.distance;
			pq.poll();
			
			if(min_d[cur] < d)
				continue;
			
			for(int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i).index;
				int nextD = d + list[cur].get(i).distance;
				
				if(nextD < min_d[next]) {
					min_d[next] = nextD;
					pq.offer(new Node(next, nextD));
				}
			}
		}
	}
}
