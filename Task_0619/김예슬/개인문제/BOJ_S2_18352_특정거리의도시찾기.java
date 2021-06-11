package algo0611;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 문제 정의 : 최단 거리가 K인 도시들의 번호를 오름차순으로 출력(존재하지 않을 경우 -1)
 * 문제 유형 : 다익스트라
 * 주의 사항 : 정점 30만, 간선 100만
 * <풀이 요약>
 * 출발 도시로 부터 도달할 수 있는 곳의 거리를 구해야하기 때문에 다익스트라 사용
 */

public class BOJ_S2_18352_특정거리의도시찾기 {

	static int N, M, K, X, INF = 300001;
	static int[] distance;
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		X = sc.nextInt();
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();
		
		for (int i = 1; i <= M; i++)
			adjList[sc.nextInt()].add(sc.nextInt());
		
		dijkstra();
		print();
	}

	static void dijkstra() {
		distance = new int[N + 1];
		Arrays.fill(distance, INF);
		
		PriorityQueue<Integer> PQ = new PriorityQueue<>();
		PQ.add(X);
		distance[X] = 0;
		
		while (!PQ.isEmpty()) {
			int from = PQ.poll();
			
			for (Integer to : adjList[from]) {
				if (distance[to] > distance[from] + 1) {
					distance[to] = distance[from] + 1;
					PQ.add(to);
				}
			}
		}
	}
	
	static void print() {
		boolean isNotExist = true;
		for (int i = 1; i <= N; i++) {
			if (distance[i] == K) {
				isNotExist = false;
				System.out.println(i);
			}
		}
		if (isNotExist) System.out.println(-1);
	}
}
