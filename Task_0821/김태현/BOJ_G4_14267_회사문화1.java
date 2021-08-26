package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (미완)
 * <문제 요약>
 * 문제 정의 : 모든 직원이 칭찬을 받은 정도를 구하여라.
 * 문제 유형 : 그래프 탐색
 * <풀이 요약>
 * 1. 조건에 따라 입력을 받아 인접 리스트를 만든다.
 * 2. M번 그래프를 탐색하며 각 직원의 칭찬 정도를 갱신한다.
 * <피드백>
 * dfs로도 해보시오.
 */

public class BOJ_G4_14267_회사문화1 {
	static int n, m;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int senior = Integer.parseInt(st.nextToken());
			if (senior == -1)
				continue;
			graph.get(senior).add(i + 1);
		}

		score = new int[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			// 먼저 수치를 저장한다.
			score[target] += val;
		}
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.offer(1);
		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Integer person : graph.get(cur)) {
				score[person] += score[cur];
				q.offer(person);
			}
		}
		
		for(int i = 1; i <= n; i++) {
			System.out.print(score[i] + " ");
		}
	}
}
