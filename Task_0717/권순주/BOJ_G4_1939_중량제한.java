import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 한번에 이동해서 옮길 수 있는 중량의 최댓값
 * 문제 유형 : 이분 탐색 + bfs
 * 
 * <피드백>
 * 이분 탐색과 bfs를 같이 사용해서 신기했다. 좋은 문제인듯!!
 * 
 */

public class BOJ_G4_1939_중량제한 {

	static class Node {
		int index;
		int weight;

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
	}

	static int N, M, start, end, maxWeight;
	static boolean[] visited;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		// 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph.get(A).add(new Node(B, C));
			graph.get(B).add(new Node(A, C));
			// 최고 무게값 체크
			maxWeight = Math.max(maxWeight, C);
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		solve();
	}

	// 이분 탐색
	private static void solve() {
		int answer = 0;
		int left = 1;
		int right = maxWeight;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (bfs(mid)) { // 건널 수 있다면
				answer = mid; // 값 저장
				left = mid + 1; // 중량 증가
			} else { // 건널 수 없다면
				right = mid - 1; // 중량 감소
			}
		}

		System.out.println(answer);
	}

	// 확인
	private static boolean bfs(int mid) {
		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[N + 1];
		queue.offer(start); // 시작점 추가
		visited[start] = true; // 방문 체크

		while (!queue.isEmpty()) {
			int now = queue.poll();
			// 건널 수 있다면 true return
			if (now == end) {
				return true;
			}
			// 그래프 탐색
			for (int i = 0; i < graph.get(now).size(); i++) {
				// 다음 node
				Node next = graph.get(now).get(i);
				// 다음 노드가 방문 가능하고 + 물품을 옮길 수 있을 때 (다리의 무게가 mid보다 크거나 같을 때)
				if (!visited[next.index] && mid <= next.weight) {
					// 방문 체크
					visited[next.index] = true;
					// 가능한 node 넣어주기
					queue.offer(next.index);
				}
			}
		}
		
		// 만약 end에 가지 못했다면 불가능 하기 때문에 false를 return 한다
		return false;
	}

}
