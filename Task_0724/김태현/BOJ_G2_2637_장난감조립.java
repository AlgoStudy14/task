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
 * 문제 정의 : 완제품을 만드는데 필요한 기본 부품의 소요 개수.
 * 문제 유형 : 위상 정렬.
 * <풀이 요약>
 * 1. 그래프를 입력 받으며 진입 차수를 초기화한다.
 * 2. 각 장난감별로 필요한 각 부품의 개수를 2차원 배열로 선언한다.
 * 3. 위상 정렬을 이용하여 완제품이 완성되기 위한 순서를 구한다.
 * -> 이때, 초기 위상 정렬에 들어가는 값들이 기본 부품이다. 해당 부품을 따로 저장해둔다.
 * -> 해당 부품이 큐에서 나오는 순간, 다음 방문 부품에 필요한 기본 부품의 개수를 올린다.
 * -> 다음으로 위상 정렬에 들어가는 값들은 중간 부품이다. 
 * -> 해당 부품이 큐에서 나오는 순간, 다음 방문 부품에 필요한 중간 부품 안의 모든 기본 부품의 개수를 올린다.
 * 4. 완성품에 필요한 기본 부품의 개수를 차례로 출력한다.
 * <피드백>
 * 위상 정렬을 곁들인 "구현" 문제 아닐까?
 */

public class BOJ_G2_2637_장난감조립 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] indegree;
	static int[][] table;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		indegree = new int[N + 1];

		// 그래프 입력을 받으며 진입 차수를 초기화한다.
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			for (int j = 0; j < c; j++) {
				graph.get(b).add(a);
				indegree[a]++;
			}
		}

		table = new int[N + 1][N + 1];
		ArrayList<Integer> base = new ArrayList<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		// 초기에 진입 차수가 0인 경우는 기본 부품에 해당한다.
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
				base.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < graph.get(cur).size(); i++) {
				int nxt = graph.get(cur).get(i);

				// 만일 현재 부품이 기본 부품인 경우
				if (base.contains(cur)) {
					// 중간 부품의 기본 부품의 개수를 늘린다.
					table[nxt][cur]++;
				}
				// 만일 현재 부품이 중간 부품인 경우
				else {
					for (Integer temp : base) {
						table[nxt][temp] += table[cur][temp];
					}
				}
				
				// 진입 차수를 낮춘다.
				indegree[nxt]--;
				// 진입 차수가 0이 된 경우 큐에 넣는다.
				if(indegree[nxt] == 0) {
					q.offer(nxt);
				}
			}
		}
		
		for(Integer res : base) {
			System.out.printf("%d %d\n" , res, table[N][res]);
		}
	}
}
