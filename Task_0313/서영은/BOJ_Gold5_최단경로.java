package algo210313;

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
 * 구해야 하는 것: 시작점에서 다른 모든 정점으로의 최단 경로 구하기 
 * 제약 사항: 모든 간선의 가중치는 10 이하의 정수  
 * 문제 유형: 다익스트라 
 * 요구 개념: 다익스트라 
 * 
 * <풀이법 요약> 
 * 1. 다익스트라 기본 공식 사용 
 * -> 메모리 초과발생(정점의 수가 최대 2만개인 것을 고려하지 않고 이차원 배열로 했음)
 * 2. 현재 코드로 고려하여 다시 짬. 해당 시작 점을 인덱스로 하는 ArrayList로 교체하여 메모리초과문제 해결 
 * -> 계속 틀림!
 * -> 원인 : 정점의 개수가 많아지면 당연히 최단경로의 경로값도 증가... 너무 작게 잡아줘서(1000)틀렸던 것 
 * -> 최대값 10억으로 고친 후 통과
 */
class Node implements Comparable<Node> {
	int end;
	int weight;

	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class BOJ_Gold5_최단경로 {
	static int[] dist;
	static List<Node>[] list;
	static int v, e, startV;
	static int INF = 1000000000;

	static void DijkstraMethod(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] check = new boolean[v + 1];
		queue.offer(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int cur = curNode.end;

			if (check[cur] == true)
				continue;
			check[cur] = true;

			for (Node node : list[cur]) {
				if (dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					queue.offer(new Node(node.end, dist[node.end]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		startV = Integer.parseInt(in.readLine());

		dist = new int[v + 1];
		list = new ArrayList[v + 1];

		// 초기화
		Arrays.fill(dist, INF);
		for (int i = 1; i <= v; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			list[start].add(new Node(end, weight));
		}
		// 입력 끝

		// 로직

		DijkstraMethod(startV);

		StringBuilder sb = new StringBuilder();
		
		// 촐력
//		for (int i = 1; i <= v; i++) {
//			if (dist[i] == 1000) {
//				System.out.println("INF");
//				continue;
//			}
//			System.out.println(dist[i]);
//		}
		for (int i = 1; i <= v; i++) {
			if(dist[i] == INF) sb.append("INF\n");
			else sb.append(dist[i]+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
