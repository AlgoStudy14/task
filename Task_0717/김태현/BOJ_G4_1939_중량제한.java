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
 * 문제 정의 : 특정 섬에서 다른 섬까지 옮길 수 있는 최대 중량의 무게.
 * 문제 유형 : 이분 탐색 + BFS + 그래프.
 * 주의 사항 : 중량 제한이 초과되는 다리는 건널 수 없다.
 * <풀이 요약>
 * 1. 인접리스트를 이용하여 그래프를 정의한다.
 * 2. 1 ~ 1,000,000,000의 값 사이에서 이번 탐색에서 정할 한계 중량을 구한다.
 * 3. BFS 탐색을 진행한다.
 * -> 한계 중량 미만인 다리는 탐색을 진행하지 않는다.
 * -> 방문한 노드는 탐색하지 않는다.
 * 4. 만일, 방문이 성공적으로 완료되었다면 한계 중량의 탐색 범위를 넓힌다.
 * 5. 만일, 방문이 실패하였다면 한계 중량의 탐색 범위를 좁힌다.
 * 6. 최종적으로 찾은 최적 중량을 출력한다.
 * <피드백>
 * 1. 슈바.. 아무리 생각해도 틀린게 없는데 입력을 잘못 받음.. 정신 차리자..!
 * 2. 그래프 구조에 익숙해지는데 좋은 문제라고 생각함. 다시 풀어볼 것.
 * 3. 또한, 방문 처리를 이용해 효과적으로 시공간을 줄이는 것도 고민하기 좋음!
 * 4. 사실상 그래프 문제(이분 탐색을 곁들인..).
 * 
 */

public class BOJ_G4_1939_중량제한 {
	static int N, M, start, end;
	static ArrayList<ArrayList<Node>> graph;
	static int max;

	static public class Node {
		int nxt, cost;

		Node(int nxt, int cost) {
			this.nxt = nxt;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 그래프(인접리스트) 정의.
		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Node>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		// 시작, 종료 지점 받기
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		// 중량 한계를 정하는 이분 탐색.
		max = Integer.MIN_VALUE;
		int left = 1, right = 1_000_000_000;
		while (left <= right) {
			int mid = (left + right) / 2;

			// 만일 중량을 실은 상태로 탐색이 성공한 경우
			if (bfs(mid)) {
				// 더 큰 중량에 대하여 탐색 시도
				max = mid;
				left = mid + 1;
			}
			// 만일 중량을 실은 상태로 탐색이 실패한 경우
			else {
				// 더 작은 중량에 대하여 탐색 시도
				right = mid - 1;
			}
		}

		System.out.println(max);
	}

	private static boolean bfs(int mid) {
		// 방문 배열 선언
		boolean[] visited = new boolean[graph.size()];

		// bfs 방식으로 시작 노드부터 방문
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			// 각 연결된 모든 노드 방문
			for (int i = 0; i < graph.get(cur).size(); i++) {
				// 다음 방문 지역이 현재 설정 중량보다 크거나 같은 경우만 방문
				Node nxtNode = graph.get(cur).get(i);
				if (nxtNode.cost >= mid) {
					// 만일 해당 지점이 최종 지점과 같다면 true 리턴
					if (nxtNode.nxt == end)
						return true;
					// 해당 지역을 재방문할 필요가 없으면 큐에 넣지 않기
					if (!visited[nxtNode.nxt]) {
						// 이 부분이 중요! 이거 안하면 메모리 초과 난다리~
						visited[nxtNode.nxt] = true;
						q.offer(nxtNode.nxt);
					}
				}
			}
		}
		// 해당 지점까지 도달하지 못했다면 false 리턴.
		return false;
	}
}
