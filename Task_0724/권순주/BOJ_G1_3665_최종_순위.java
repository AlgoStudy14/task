import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 작년 순위와 상대적인 순위가 바뀐 모든 팀의 목록이 주어졌을 때, 올해 순위를 구하여라
 * 문제 유형 : 위상 정렬
 * 제약 조건 : 내 뒤의 순위는 모두 저장한다
 * 
 * <피드백>
 * 고민의 끝은 구글링..
 * 
 */

public class BOJ_G1_3665_최종_순위 {

	static int T, N, M;
	static int[] indegree, lastRank;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			graph = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			// 초기화
			lastRank = new int[N];
			indegree = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				lastRank[i] = Integer.parseInt(st.nextToken());
			}

			// 작년 순위로 그래프 작성 -> 내 뒤의 순위 모두 저장
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					graph.get(lastRank[i]).add(lastRank[j]);
					indegree[lastRank[j]]++; // 진입차수 증가
				}
			}

			// 그래프 올해 순위로 수정
			M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (graph.get(a).contains(b)) {
					graph.get(a).remove(Integer.valueOf(b));
					graph.get(b).add(a);
					indegree[a]++;
					indegree[b]--;
				} else {
					graph.get(b).remove(Integer.valueOf(a));
					graph.get(a).add(b);
					indegree[b]++;
					indegree[a]--;
				}
			}
			
			System.out.println(topologySort());
		}
	}

	// 위상 정렬 함수
	static String topologySort() {
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		// IMPOSSIBLE 판단을 위한 변수
		int cnt = 0;
		// 처음 시작할 때 진입차수가 0인 노드를 큐에 삽입
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
				cnt++;
			}
		}

		// 큐가 빌 때까지 반복
		while (!queue.isEmpty()) {
			// q의 사이즈가 2일 때, 즉 갈래길이 나올 때 -> 확실한 순위를 알 수 없기 때문에 ?을 return
			if (queue.size() > 1) {
				return "?\n";
			}
			// 큐에서 원소 꺼내서 저장
			int now = queue.poll();
			sb.append(now).append(" ");
			// 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
			for (Integer next : graph.get(now)) {
				indegree[next]--;
				// 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
				if (indegree[next] == 0) {
					queue.offer(next);
					cnt++;
				}
			}
		}

		// 방문한 노드의 수가 N개와 다르다면 순위를 정할 수 없음
		if (N != cnt) {
			return "IMPOSSIBLE";
		} else {
			return sb.toString();
		}
	}

}
