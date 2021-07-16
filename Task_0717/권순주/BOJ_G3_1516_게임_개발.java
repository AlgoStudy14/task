import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * <문제 요약>
 * 문제 정의 : N개의 각 건물이 완성되기까지 걸리는 최소 시간
 * 문제 유형 : 위상 정렬	
 * 
 */

public class BOJ_G3_1516_게임_개발 {

	static int N;
	static int[] indegree, timeTable, answer;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		indegree = new int[N + 1];
		timeTable = new int[N + 1];
		answer = new int[N + 1];

		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// 방향 그래프의 모든 간선 정보를 입력 받기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			timeTable[i] = time; // 시간 저장
			answer[i] = time; // 정답을 위한 시간 저장
			while (true) {
				int node = Integer.parseInt(st.nextToken());
				// -1일 때까지 반복
				if (node == -1) {
					break;
				}
				// node -> 1
				graph.get(node).add(i);
				// 진입 차수를 1 증가
				indegree[i]++;
			}
		}

		topologySort();
	}

	// 위상 정렬 함수
	static void topologySort() {
		Queue<Integer> queue = new LinkedList<>();

		// 처음 시작할 때 진입차수가 0인 노드를 큐에 삽입
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			// 큐에서 원소 꺼내기
			int now = queue.poll();
			// 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
			for (int i = 0; i < graph.get(now).size(); i++) {
				int node = graph.get(now).get(i);
				indegree[node]--;
				// 자신의 건물을 짓기전, 이전에 가장 오랜 시간 걸린 값을 찾기
				answer[node] = Math.max(answer[node], answer[now] + timeTable[node]);
				// 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
				if (indegree[node] == 0) {
					queue.offer(node);
				}
			}
		}

		// 위상 정렬 결과 출력
		for (int i = 1; i <= N; i++) {
			System.out.println(answer[i]);
		}
	}

}
