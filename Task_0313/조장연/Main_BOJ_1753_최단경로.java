import java.util.ArrayList;
import java.util.Scanner;

/*
 * <문제 요약>
 * 정점과 간선의 정보, 시작지점이 주어졌을 때 모든 정점에 대해 시작지점에서 갈 수 있는 최단경로 구하기
 * 
 * <풀이법 요약>
 * 다익스트라 알고리즘 활용
 * 간선의 정보를 ArrayList배열을 이용하여 저장
 * 시작지점에서 최소거리 정점을 찾고 (시작지점에서 정점까지의 거리 + 정점에서 다른 정점으로 가는 거리)와 시작지점에서 다른 정점으로 가는 거리를 비교하여 업데이트
 * 
 * <주의!!>
 * 아무생각없이 2차원 배열 map을 구성하여 간선의 정보를 저장했더니 메모리 터짐!
 */


public class Main_BOJ_1753_최단경로 {
	static int V, E, start;
	static int[] startMap;
	static boolean[] check;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		start = sc.nextInt() - 1;
		startMap = new int[V];
		check = new boolean[V];
		check[start] = true;
		ArrayList<int[]> go[] = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			go[i] = new ArrayList<>();
		}

		for (int i = 0; i < V; i++) { // startMap 30000000로 초기화
			startMap[i] = 30000000;
		}

		for (int i = 0; i < E; i++) { // startMap 및 간선의 정보 저장
			int sp = sc.nextInt() - 1;
			int ep = sc.nextInt() - 1;
			int weight = sc.nextInt();

			if (sp == start) {
				if (startMap[ep] > weight)
					startMap[ep] = weight;
			} else {
				int[] arr = { ep, weight };
				go[sp].add(arr);
			}
		}

		int minNode = func();

		while (minNode != -1) { // 업데이트 과정
			check[minNode] = true;
			for (int i = 0; i < go[minNode].size(); i++) {
				if (startMap[minNode] + go[minNode].get(i)[1] < startMap[go[minNode].get(i)[0]])
					startMap[go[minNode].get(i)[0]] = startMap[minNode] + go[minNode].get(i)[1];
			}

			minNode = func();
		}

		for (int i = 0; i < V; i++) {
			if (i == start) {
				System.out.println("0");
				continue;
			}

			if (startMap[i] != 30000000)
				System.out.println(startMap[i]);
			else
				System.out.println("INF");
		}

	}

	private static int func() { // 가지않은 정점 중 최소거리 정점 구하기
		int node = -1;
		int num = 30000000;

		for (int i = 0; i < V; i++) {
			if (startMap[i] < num && check[i] == false) {
				node = i;
				num = startMap[i];
			}
		}
		return node;
	}

}
