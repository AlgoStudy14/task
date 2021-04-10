package s0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 1번 섬에 도착할 수 있는 양의 수.
 * 문제 유형 : 그래프, DFS.
 * <풀이법>
 * 1 - 1. 주어진 입력의 역 방향으로 그래프를 만든다(Ex: 2 -> 1은 1 -> 2와 같이).
 * -> 그래프에는 다음 노드의 번호 정보만을 기록한다.
 * 1 - 2. 그래프를 만드는 동시에 각 노드에 현재 존재하는 양, 늑대의 수를 초기화한다.
 * 2. 그래프를 따라 다음 노드를 탐색하고, 노드의 끝 부분에 다다르면 아래와 같은 작업을 수행한다.
 * -> 현재 노드의 양의  수 - 늑대의 수를 리턴해준다.
 * -> 단, 그 값이 양수인 경우에만 값을 리턴해준다(즉, 양의 수만 리턴 해준다).
 * 3. 최종 리턴값이 정답.
 * <참고 사항>
 * 답의 범위가 int범위를 벗어 날 수 있는 문제였다. 문제의 조건에 대해서 조금 더 주의 깊게 생각하도록 하자.
 * (문제에서 양이나 늑대의 수는 1 ~ 1,000,000,000 범위 내에서 주어진다. int 형은 최대 양, 음으로 21억까지밖에 표현을 못한다.
 * 이 사실 들로 부터 long형을 써야겠다는 생각이 들었어야만 한다).
 */

public class BOJ_G2_16437_양구출작전 {
	static int N;
	static ArrayList<ArrayList<Integer>> graph;
	static long[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		arr = new long[N][2];
		// 그래프 초기화
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 양인 경우 : 0번 인덱스를 채운다.
			if (st.nextToken().equals("S")) {
				arr[i + 1][0] = Integer.parseInt(st.nextToken());
			}
			// 늑대인 경우 : 1번 인덱스를 채운다.
			else {
				arr[i + 1][1] = Integer.parseInt(st.nextToken());
			}
			// 주어진 입력의 역 방향으로 그래프를 만든다(1번 노드부터 탐색하기 위함).
			graph.get(Integer.parseInt(st.nextToken()) - 1).add(i + 1);
		}

		// dfs 탐색 및 결과
		System.out.println(dfs(0));
	}

	private static long dfs(int idx) {
		// 다음 노드를 탐색한다.
		for (int i = 0; i < graph.get(idx).size(); i++) {
			// 넘겨 받은 양의 수.
			arr[idx][0] += dfs(graph.get(idx).get(i));
		}
		arr[idx][0] = arr[idx][0] - arr[idx][1];

		// 양의 수가 양수라면 그대로, 아니라면 0을 리턴한다.
		if (arr[idx][0] > 0) {
			return arr[idx][0];
		} else {
			return 0;
		}
	}
}
