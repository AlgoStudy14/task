package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 작년 순위와 올해 상대적인 등수가 주어질 때, 올해 순위를 작성한다.
 * 문제 유형 : 위상 정렬.
 * 주의 사항 : 정확한 순위를 찾을 수 없는 경우(정렬이 여러가지인 경우)와 순위를 결정할 수 없는 경우도 판별해내야한다.
 * <풀이 요약>
 * 1. 올해 상대 순위를 모두 인접리스트 형태의 그래프로 저장한다(2중 포문).
 * 2. 상대 순위가 역전되는 경우, 해당 방향을 바꿔준다.
 * 3. 위상 정렬을 수행한다.
 * -> 한 사이클에서 진입 차수가 0인 경우가 여러개인 경우(큐에 0인 노드가 여러개 존재하게 되는 경우) ?를 출력한다.
 * -> 순서를 모두 정하기 전에 큐가 비는 경우(사이클이 존재하는 경우) IMPOSSIBLE을 출력한다.
 * -> 그 외의 경우, 모든 순서를 차례로 출력한다.
 * <피드백>
 * 지문 낚시가 쫌 에반데.. 암튼 위상정렬의 순서를 바꾸는 의도는 좋았다. 풀긴 풀었다!
 */

public class BOJ_G1_3665_최종순위 {
	static int T, n, m;
	static int[] last;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] indegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 작년 순위 입력.
			n = Integer.parseInt(br.readLine());
			last = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				last[i] = Integer.parseInt(st.nextToken());
			}
			indegree = new int[n + 1];

			// 작년 순위를 기준으로 우선 그래프를 작성한다.
			graph = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					graph.get(last[i]).add(last[j]);
					// 동시에 진입 차수를 계산한다.
					indegree[last[j]]++;
				}
			}

			// 올해 상대 순위를 바탕으로 작년 그래프를 수정한다.
			boolean flag = true;
			m = Integer.parseInt(br.readLine());
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 작년 상대 순위를 바꾼다.
				if (graph.get(a).contains(b)) {
					graph.get(b).add(a);
					graph.get(a).remove(Integer.valueOf(b));
					indegree[a]++;
					indegree[b]--;
				} else {
					graph.get(a).add(b);
					graph.get(b).remove(Integer.valueOf(a));
					indegree[b]++;
					indegree[a]--;
				}
			}

			// 위상 정렬을 수행한다.
			Queue<Integer> q = new LinkedList<Integer>();
			// 진입 차수가 0인 노드를 넣는다.
			for (int i = 1; i <= n; i++) {
				if (indegree[i] == 0)
					q.offer(i);
			}
			// 큐가 빌 때 까지 반복한다.
			StringBuilder result = new StringBuilder();
			// 현재 정렬된 노드의 개수
			int cnt = 0;
			flag = true;
			while (!q.isEmpty()) {
				// 만일, 큐에 2개 이상의 값이 들어간 경우 ?를 출력하고 종료한다.
				if (q.size() >= 2) {
					flag = false;
					break;
				}
				int cur = q.poll();
				// 현재 정렬된 노드의 개수 + 1
				cnt++;
				// 순서 저장
				result.append(cur + " ");

				for (int i = 0; i < graph.get(cur).size(); i++) {
					int nxt = graph.get(cur).get(i);

					// 진입 차수를 낮추고, 해당 값이 0이라면 다음 큐에 넣는다.
					if (--indegree[nxt] == 0)
						q.offer(nxt);
				}
			}
			if (!flag) {
				System.out.println("?");
				continue;
			}

			// 만일, 정렬된 노드의 개수가 전체 팀의 수와 같지 않다면 IMPOSSIBLE 출력
			if (cnt != n)
				System.out.println("IMPOSSIBLE");
			// 그렇지 않다면 정렬된 순서 출력
			else {
				System.out.println(result);
			}
		}
	}
}
