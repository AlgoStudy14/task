package algo0412;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 1번 섬에 도달할 수 있는 양의 최대값
 * 문제 유형 : DFS를 사용
 * <풀이법 요약>
 * 0. island: 섬의 정보 저장, adjList: 섬의 연결 정보 저장
 * 1. 1번 섬부터 탐색 시작
 * 2. 끝 노드 부터 return하면서 1번 섬에 갈 수 있는 양의 수를 구함
 *  2-1. 양이라면 amount를 더한 후 return
 *  2-2. 늑대라면 amount를 뺀 후 return
 *  
 * c.f) 처음 코드를 짤 때, 부르트포스로 풀어서 이미 방문했던 곳도 다시 방문하게 코드를 작성했고 시간초과
 * 		dfs를 사용해 그런 경우들을 없앨 수 있음
 * 		노답어려움ㅡㅡ
 */

public class BOJ_G2_16437 {

	static int N;
	static ArrayList<Island> island;
	static ArrayList<Integer>[] adjList;
	
	static class Island {
		boolean isSheep;
		int amount;
		
		public Island(boolean isSheep, int amount) {
			this.isSheep = isSheep;
			this.amount = amount;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		island = new ArrayList<>();
		
		// 인접리스트 초기화
		adjList = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++)
			adjList[i] = new ArrayList<>();
		
		for (int i = 2; i < N + 1; i++) {
			// 섬에 대한 정보 저장
			boolean isSheep = sc.next().equals("S") ? true : false;
			island.add(new Island(isSheep, sc.nextInt()));
			
			// 섬의 연결 정보 저장(인접리스트)
			adjList[sc.nextInt()].add(i);			
		}
		
		System.out.println(dfs(1));
	}
	
	static long dfs(int i) {
		long sum = 0;
		
		// 자식 노드 탐색
		for (int to : adjList[i])
			sum += dfs(to);
		
		if (i == 1) return sum;
		
		Island cur = island.get(i - 2);
		int amount = cur.amount;
		
		// 현재 섬이 양이라면
		if (cur.isSheep) return sum + amount;
		// 현재 섬이 늑대라면
		else return (sum - amount >= 0) ? sum - amount : 0;
	}
}
