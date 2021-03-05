package Greedy;

import java.util.Arrays;

/***
 * <문제 요약>
 * 구해야 하는 것 : 모든 섬을 연결하는 최소 비용(최소 비용 신장 트리를 구해야 한다).
 * 문제 유형 : 그리디.
 * 요구 개념 : 크루스칼 알고리즘(union-find 알고리즘).
 * <풀이법 요약>
 * 크루스칼 알고리즘 : 최소 비용 신장 트리를 구하는 알고리즘
 * 그리디 접근법 : 간선의 비용이 적은 순서대로 포함 시킨다(단, 사이클이 생기는 경우 제외 시킨다).
 */

public class PM_L3_섬연결하기 {
	public static void main(String[] args) {
		PM_L3_섬연결하기 doit = new PM_L3_섬연결하기();
		int n = 4;
		int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		doit.solution(n, costs);
	}

	public int solution(int n, int[][] costs) {
		int answer = 0;

		// 사이클 확인을 위한 parent배열
		int[] parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		// 간선 비용이 적은 순서(오름차순)대로 정렬한다.
		Arrays.sort(costs, (x, y) -> Integer.compare(x[2], y[2]));

		// 간선의 비용이 적은 순서대로 선택한다(단, 사이클이 존재한다면 선택하지 않는다).
		for (int i = 0; i < costs.length; i++) {
			// 해당 부모가 같은지 확인한다(다르다면, 사이클이 없는 것).
			if (!findParent(parent, costs[i][0], costs[i][1])) {
				// 사이클이 존재하지 않는다면 두 집합을 union하고 선택한 비용을 증가시킨다.
				unionParent(parent, costs[i][0], costs[i][1]);
				answer += costs[i][2];
			}
		}

		System.out.println(answer);
		return answer;
	}

	// 해당 원소의 부모를 찾는 함수.
	private int getParent(int[] parent, int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = getParent(parent, parent[x]);
	}

	// 부모를 같게 만드는 함수.
	void unionParent(int parent[], int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	// 두 원소의 부모가 같은지 확인하는 함수.
	boolean findParent(int parent[], int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		if (a == b)
			return true;
		else
			return false;
	}
}
