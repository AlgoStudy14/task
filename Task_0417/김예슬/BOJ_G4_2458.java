package algo0415;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * <문제 요약>
 * 구해야 하는 것 : 자신이 키가 몇 번째인지 알 수 있는 학생의 수 출력
 * 문제 유형 : dfs 사용
 * <풀이법 요약>
 * 0. 인접리스트에 키에 대한 정보 저장
 * 1. 1~N까지 하나씩 방문
 *  1-1. 자신보다 키가 큰 사람의 수
 *  1-2. 자신보다 키가 작은 사람의 수
 *  1-3. 이미 방문했던 경우, 카운팅하지 않기 위해 visited 사용
 * 2. 만약 키작 + 키큰 = N - 1이면 자신의 키가 몇 번째인지 알 수 있음
 */

import java.util.Scanner;

public class BOJ_G4_2458 {

	static int N, M;
	static int[] up, down;
	static boolean[] visited;
	static ArrayList<Integer>[] adjList; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		up = new int[N + 1];
		down = new int[N + 1];
		visited = new boolean[N + 1];
		
		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++)
			adjList[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++)
			adjList[sc.nextInt()].add(sc.nextInt());
		
		for (int i = 1; i < N + 1; i++) {
			dfs(i, i);
			Arrays.fill(visited, false);
		}
		
		System.out.println(count());
	}

	static void dfs(int start, int cur) {
		visited[cur] = true;
		
		for (int next : adjList[cur]) {
			if (visited[next]) continue;
			
			++down[next];
			++up[start];
			dfs(start, next);
		}
	}
	

	static int count() {
		int cnt = 0;
		for (int i = 1; i < N + 1; i++) {
			if (up[i] + down[i] == N - 1) ++cnt;
		}
		return cnt;
	}
}
