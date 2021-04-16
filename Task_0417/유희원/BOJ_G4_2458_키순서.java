package algo0417;

import java.util.Scanner;
/*
 * G4
 * N명의 학생과 비교횟수 M이 주어짐
 * M개의 줄에 키를 비교하는 a,b 주어짐 -> a<b임
 * 키 순서가 몇번째인지 정확히 알 수 있는 학생 수 
 */
public class BOJ_2458_키순서 {

	static int N,M;
	static int[][] adj;
	static int[] tallCnt, smallCnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //학생수
		M = sc.nextInt(); //관계수
		adj = new int[N+1][N+1];
		tallCnt = new int[N+1];
		smallCnt = new int[N+1];
		for (int i = 1; i <=M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adj[a][b] = 1; //a<b인 경우
		}
		int res = 0;
		for (int i = 1; i <=N; i++) {
			dfs(i,i,new boolean[N+1]);
		}
		//자신보다 크거나 작은 친구들이 N-1명이면
		for (int i = 1; i <=N; i++) {
			if(tallCnt[i] + smallCnt[i] == N-1) res++;
		}
		System.out.println(res);
	}
	//출발학생보다 큰 학생을 따라 탐색하면서 누구보다 크고 작은지 카운팅
	private static void dfs(int start, int cur, boolean[] visited) {
		 visited[cur] = true; //start<cur
		 for (int i = 1; i <=N; i++) {
			 //나보다 크고 방문하지 않았으면
			if(adj[cur][i]==1&&!visited[i]) { //start<cur<i
				tallCnt[start]++;
				smallCnt[i]++;
				dfs(start,i,visited);
			}
		}
		
	}

}
