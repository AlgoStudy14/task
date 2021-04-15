package s0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 자신의 키 번호를 알 수 있는 학생들의 수.
 * 문제 유형 : BFS
 * <풀이법>
 * 1. 모든 노드에 대해서 그래프를 BFS 방식으로 탐색하되, 각 노드에 대한 방문 횟수를 기록하는 배열을 둔다.
 * -> 큐에 노드를 삽입을 한 횟수 만큼 자신의 노드의 값을 증가 시킨다.
 * -> 다른 노드에 의하여 방문된 횟수 만큼 자신의 노드의 값을 증가 시킨다.
 * -> 단, 이때 방문한 노드는 재방문하지 않는다.
 * 2. 최종적으로 배열에 기록된 값이 자신을 제외한 사람 수 이상이라면, 그 학생은 키번호를 알 수 있다.
 */

public class BOJ_G4_2458_키순서_BFS {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	// 해당 노드 방문 횟수
	static int[] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			graph.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
		}
		visited = new int[N + 1];
		ans = 0;

		// 모든 노드에 대해서 BFS 탐색.
		for (int i = 1; i < N + 1; i++) {
			bfs(i);
		}

		// 키 번호를 알 수 있는 사람 검증
		for (int i = 1; i < N + 1; i++) {
			if (visited[i] >= N - 1) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	private static void bfs(int start) {
		boolean[] check = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < graph.get(cur).size(); i++) {
				int nxt = graph.get(cur).get(i);
				if (!check[nxt]) {
					check[nxt] = true;
					q.offer(nxt);
					// 방문된 노드의 방문 횟수를 증가 시킨다
					visited[nxt]++;
					// 시작 정점이 다른 노드를 방문하는 횟수만큼 시작정점의 방문 횟수를 증가 시킨다.
					visited[start]++;
				}
			}
		}
	}
}
