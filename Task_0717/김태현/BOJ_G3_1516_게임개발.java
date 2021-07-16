import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : N개의 건물이 완성되기까지 걸리는 최소 시간.
 * 문제 유형 : 위상 정렬 + DP.
 * 주의 사항 : 건물을 짓기 위한 선행 건물이 지어져야 지을 수 있는 건물도 존재한다.
 * <풀이 요약>
 * 1. 주어진 입력을 바탕으로 인접 리스트를 활용하여 그래프를 선언한다.
 * -> 동시에 진입 차수와, 각 건물의 기본 소요 시간을 저장해둔다.
 * 2. 위상 정렬을 활용하여 건물을 순서대로 꺼낸다.
 * -> 다음 건물의 진입 차수를 낮춘다.
 * -> (DP) 모든 건물의 최대 빌드 시간을 저장할 배열을 만든다(진입 차수와 함께 선언한다).
 * -> 초기화 단계에서 진입 차수가 0인 경우, 최대 빌드 시간(dp 배열)을 기본 빌드 시간으로 초기화한다.
 * -> 진입 차수를 낮추는 동시에 현재 건물의 기록된 최대 빌드 시간과 (현재 건물의 기본 빌드 시간 + 이전 건물의 최대 빌드 시간)중 최대를 고른다.
 * -> 왜냐하면, 가장 오래 걸린 시간이 모든 조건을 충족하고 빌드를 완성한 시간이기 때문이다.
 * 3. 최종적인 빌드 시간을 순서대로 출력한다.
 * <피드백>
 * 1. 흠.. 위상 정렬의 구조는 이해하기 쉽지만 어떻게 응용할지가 중요한 문제.
 * 2. DP가 섞여 있어서 생각보다 어렵다. 구조는 이해하였지만 자료를 어떻게 사용할지 다시 고민하는 연습을 해보자!
 */

public class BOJ_G3_1516_게임개발 {
	static int N;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] baseTime;
	static int[][] indegree;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		// 기본 빌드 시간과 각 결과 빌드 시간을 저장할 배열들
		baseTime = new int[N + 1];
		indegree = new int[N + 1][2];

		// 그래프의 입력을 받으며 진입 차수와 기본 건물 빌드 시간을 동시에 초기화 시킨다.
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cur = i + 1;
			// 기본 빌드 시간 초기화.
			baseTime[cur] = Integer.parseInt(st.nextToken());

			// -1이 나올 때 까지 입력을 반복한다.
			while (true) {
				// 선행 건물
				int pre = Integer.parseInt(st.nextToken());
				// -1이 나오면 반복문 탈출.
				if (pre == -1)
					break;
				// 그래프 연결. 단, 중복은 제거한다.
				graph.get(pre).add(cur);
				// 진입 차수 높이기.
				indegree[cur][0]++;
			}
		}

		// 위상 정렬
		// 진입 차수가 0인 대상을 먼저 큐에 넣는다.
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i][0] == 0) {
				q.offer(i);
				// 이때, 해당 진입 차수는 0이므로, 최대 빌드 시간(dp 배열)을 기본 빌드 시간으로 초기화한다.
				indegree[i][1] = baseTime[i];
			}
		}

		// 큐가 빌 때까지 위상 정렬을 반복한다.
		while (!q.isEmpty()) {
			int cur = q.poll();

			// 다음 노드를 방문한다.
			for (int i = 0; i < graph.get(cur).size(); i++) {
				int nxt = graph.get(cur).get(i);

				// 다음 노드의 진입 차수를 줄인다.
				indegree[nxt][0]--;
				// 다음 노드의 최대 빌드 시간 vs 현재 노드의 최대 빌드 시간 + 다음 노드의 기본 빌드 시간.
				indegree[nxt][1] = Math.max(indegree[nxt][1], indegree[cur][1] + baseTime[nxt]);
				// 진입 차수가 0이라면 큐에 넣는다.
				if (indegree[nxt][0] == 0)
					q.offer(nxt);
			}
		}

		// 최종 빌드 시간을 출력한다.
		for (int i = 1; i <= N; i++) {
			System.out.println(indegree[i][1]);
		}
	}
}
