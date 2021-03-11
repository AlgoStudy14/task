import java.util.Scanner;

/*
 * <문제 요약>
 * 정점과 간선의 정보가 주어졌을 때 모든 도시의 쌍에 대해 갈 수 있는 최단경로 구하기
 * 
 * <풀이법 요약>
 * 플로이드 알고리즘 활용
 * 
 * <플로이드 알고리즘>
 * 모든 정점에서 모든 정점으로의 최단경로를 구하는 방법
 * 거쳐가는 정점을 기준으로 알고리즘을 수행(정점의 갯수만큼 for문을 돌림)
 * 
 * 플로이드 VS 다익스트라
 * 플로이드가 더 간결하다.
 * 간선의 수가 많은 경우 속도 : 플로이드 > 다익스트라
 * 시작점이 주어지고 정점까지의 최단거리만 구하면 될때는 속도 : 다익스트라 >> 플로이드
 * 그래프에 음의 가중치가 있는경우 플로이드만 가능!!(다익스트라는 사용못함!!!!)
 */

public class Main_BOJ_11404_플로이드 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					map[i][j] = 0;
				else
					map[i][j] = 100000000;
			}
		}

		for (int i = 0; i < M; i++) {
			int sp = sc.nextInt() - 1;
			int ep = sc.nextInt() - 1;
			int weight = sc.nextInt();
			if (map[sp][ep] > weight)
				map[sp][ep] = weight;
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 100000000)
					System.out.print("0 ");
				else
					System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
