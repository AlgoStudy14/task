package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_Gold4_11404 {
	/*
	 * 다익스트라 알고리즘은 어떠한 정점을 기준으로 잡고 다른 모든 정점으로의 최단 거리를 구하는 알고리즘이고, 
	 * 플로이드 와샬은 모든 정점에서 모든 정점으로의 최단 거리를 구하는 알고리즘
	 * 
	 * 플로이드-와샬 알고리즘(Floyd-Warshall Algorithm)은 그래프에서 모든 꼭짓점 사이의 최단 경로의 거리를 구하는 알고리즘이다.
	 * 
	 * 3가지 반복문을 돌게 됨. 시간복잡도가  O(n&3)
	 * 
	 * for(int i = 1; i <= length; i++){
	 * 		for(int j = 1; j <= length; j++){
	 * 			for(int k = 1; k <= length; k++){
	 * 				distance[j][k] = Math.min(distance[k][i] + distance[i][k], distance[j][k])
	 * 			}
	 * 		}
	 * }
	 */
	//static int INF = Integer.MAX_VALUE;  <= 더해서 값이 넘어가는듯
	static int INF = 1000000000;
	static int map[][];
	static String s ;
	static String[] ar;
	static int a,b,c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		map = new int[n+1][m+1];
		//초기화
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				map[i][j] = INF;
				if(i == j)
					map[i][j] = 0;
			}
		}
		//3째줄부터 값 읽어와서 from, to, 가중치   => 맵에 넣어주기.
		for(int i = 0; i < m; i++) {
			s = br.readLine();
			ar = s.split(" ");
			a = Integer.parseInt(ar[0]);
			b = Integer.parseInt(ar[1]);
			c = Integer.parseInt(ar[2]);
			map[a][b] = Math.min(map[a][b], c);
		}
		//플로이드 알고리즘. 3중 for 문 돌면서 값 갱신
		for(int i = 1; i <=n; i++) {
			for(int j = 1; j <= n; j++) {
				for(int k = 1; k <= n; k++) {
					if(map[j][k] > map[j][i] + map[i][k])
						map[j][k] = map[j][i] + map[i][k];
				}
			}
		}
		//출력. 최소값을 맞추기위해 INF를 넣었지만 출력할땐 0이므로 INF일땐 0출력.
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(map[i][j] == INF)
					System.out.println("0 ");
				else
					System.out.print(map[i][j] + " ");
			}System.out.println();
		}
	}
}
