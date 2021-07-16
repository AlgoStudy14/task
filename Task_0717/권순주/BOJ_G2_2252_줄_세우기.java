import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 학생들의 키를 비교한 결과를 가지고 줄 세우기
 * 문제 유형 : 위상 정렬
 * 
 * <피드백>
 * 알고리즘 종류의 끝은 어디인가..
 * 
 */

public class BOJ_G2_2252_줄_세우기 {

	// N : 노드의 개수
	// M : 간선의 개수
	static int N, M;
	// 각 노드에 대한 진입차수
	static int[] indegree;
	// 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트 초기화
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N + 1];

		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// 방향 그래프의 모든 간선 정보를 입력 받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// A -> B
			graph.get(A).add(B);
			// 진입 차수를 1 증가
			indegree[B]++;
		}

		topologySort();
	}

	// 위상 정렬 함수
	static void topologySort() {
		// 수행 결과를 담을 리스트
		ArrayList<Integer> answer = new ArrayList<>();
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
			answer.add(now);
			// 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
			for (int i = 0; i < graph.get(now).size(); i++) {
				int node = graph.get(now).get(i);
				indegree[node]--;
				// 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
				if (indegree[node] == 0) {
					queue.offer(node);
				}
			}
		}

		StringBuffer sb = new StringBuffer();
		// 위상 정렬 결과 출력
		for (Integer node : answer) {
			sb.append(node).append(" ");
		}
		System.out.println(sb.toString());
	}

}
