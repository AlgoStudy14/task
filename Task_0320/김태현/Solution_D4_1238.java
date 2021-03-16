package lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 가장 나중에 전화를 받는 사람 중 번호가 큰 사람.
 * 문제 유형 : 그래프, BFS.
 * 요구 개념 : 인접 리스트 그래프 표현, BFS.
 * <풀이법 요약>
 * 1. 인접 리스트를 이용하여 주어진 그래프를 표현한다.
 * 2. BFS 탐색을 진행한다.
 * -> 시작점을 큐에 넣는다.
 * -> 방문하지 않은 지점에 대하여 방문표시(숫자로 값을 증가)를하고, 해당점을 큐에 넣는다.
 * 3. 방문된 지점 중 가장 큰 값을 갖는 정점의 번호를 선택한다.
 */

public class Solution_D4_1238 {
	static int N, start;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 1; t <= 10; t++) {
			// 초기화.
			N = sc.nextInt();
			start = sc.nextInt();
			graph = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < 101; i++) {
				graph.add(new ArrayList<Integer>());
			}
			visited = new int[101];

			// 인접 리스트 초기화.
			for (int i = 0; i < N / 2; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				graph.get(a).add(b);
			}

			// BFS 탐색
			Queue<Integer> q = new LinkedList<Integer>();
			// 초기 지점을 큐에 넣는다.
			q.add(start);
			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int i = 0; i < graph.get(cur).size(); i++) {
					int next = graph.get(cur).get(i);
					if (visited[next] == 0) {
						visited[next] = visited[cur] + 1;
						q.add(next);
					}
				}
			}

			// 방문 지점의 최대값과 해동 노드 번호를 구한다.
			int max = Integer.MIN_VALUE;
			int res = 0;
			for (int i = 1; i < 101; i++) {
				if (max <= visited[i]) {
					max = visited[i];
					res = i;
				}
			}
			// 결과 출력.
			System.out.printf("#%d %d\n", t, res);
		}
		sc.close();
	}
}
