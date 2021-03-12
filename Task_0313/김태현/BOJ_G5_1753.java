package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
<문제 요약>
구해야 하는 것 : 시작 정점에서 각 정점 까지의 최단 경로.
문제 유형 : 그래프.
요구 개념 : 최단 경로 알고리즘(다익스트라).  
<풀이법 요약>
다익스트라 알고리즘을 구현하여 출발 노드로부터 도착 노드까지의 최소 비용을 저장하고, 출력한다.
 */

public class BOJ_G5_1753 {
	// 노드와 비용을 포함하는 객체를 미리 만들어준다.
	static public class Node {
		// 연결되는 정점
		int end;
		// 비용
		int cost;

		Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "[" + end + ", " + "" + cost + "]";
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// V = 정점의 개수, E = 간선의 개수.
		int V = sc.nextInt();
		int E = sc.nextInt();
		// 시작점.
		int start = sc.nextInt();

		// 1차원 리스트를 만든다.
		// 인덱스의 번호 = 노드의 번호 이기 때문에, 1차원 리스트의 크기를 임의로 1 늘려서 정의한다(스킬).
		List<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < V + 1; i++) {
			graph.add(new ArrayList<Node>());
		}

		int a;
		int b;
		int cost;
		for (int i = 0; i < E; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			cost = sc.nextInt();

			graph.get(a).add(new Node(b, cost));
//			graph.get(b).add(new Node(a, cost));
		}

		// 다익스트라 알고리즘 수행
		// 각 정점까지의 비용 : 시작점은 초기 비용을 0으로 세팅, 나머지는 최대값을 미리 넣어둔다.
		boolean[] visited = new boolean[V + 1];
		int[] dist = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		PriorityQueue<int[]> min = new PriorityQueue<int[]>((x, y) -> x[1] > y[1] ? 1 : -1);
		min.add(new int[] { start, 0 });

		// 정점의 개수 만큼 방문해야하므로 그 만큼 반복.
		for (int i = 0; i < V; i++) {
			if(min.isEmpty()) {
				break;
			}
			int cur = min.poll()[0];
			while (visited[cur]) {
				if(min.isEmpty()) {
					break;
				}
				cur = min.poll()[0];
			}
//			System.out.println(cur);
			for (int j = 0; j < graph.get(cur).size(); j++) {
				if (dist[graph.get(cur).get(j).end] > graph.get(cur).get(j).cost + dist[cur]) {
					dist[graph.get(cur).get(j).end] = graph.get(cur).get(j).cost + dist[cur];
					min.add(new int[] { graph.get(cur).get(j).end, dist[graph.get(cur).get(j).end] });
				} else {
					min.add(new int[] { graph.get(cur).get(j).end, dist[graph.get(cur).get(j).end] });
				}
			}
			visited[cur] = true;
//			System.out.println(Arrays.toString(visited));
//			System.out.println(Arrays.toString(dist));
//			for (int[] m : min) {
//				System.out.print(Arrays.toString(m) + " ");
//			}
//			System.out.println();
		}
		for (int i = 1; i < V + 1; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}

		sc.close();
	}
}