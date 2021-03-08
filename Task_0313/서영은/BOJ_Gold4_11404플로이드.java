/**
 * <문제 요약> 
 * 구해야 하는 것: 모든 도시의 쌍 (A,B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값 
 * 제약 사항: 비용은 100,000보다 작거나 같은 자연수 
 * 문제 유형: 플로이드-와샬 알고리즘 
 * 요구 개념: 플로이드-와샬 알고리즘 
 * 
 * <풀이법 요약> 
 * 1. 다익스트라가 하나하나 가장 적은 비용을 하나씩 선택해야 했다면, 플로이드 와샬은 거쳐가는 정점을 기준으로 
 * 2. 다이나믹 프로그래밍! 기법 
 *  
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_Gold4_11404플로이드 {

	static int[][] city;
	static int[][] dist;
	static int n,m;
	static int INF = 1000000000;
	
 	public static void main(String[] args) throws NumberFormatException, IOException {
 		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 		StringTokenizer st;
 		
 		n = Integer.parseInt(in.readLine());
 		m = Integer.parseInt(in.readLine());
 		
 		city = new int[n+1][n+1];
 		dist = new int[n+1][n+1];
 		
 		// 최소를 찾기 위해 무한대로 초기화
 		initCity();
 		
 		for (int i = 0; i < m; i++) {
 			st = new StringTokenizer(in.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if(city[start][end] > weight) {
				city[start][end] = weight;
			}
		}
 		
 		floydWarshall();
 		
 		//촐력 
 		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				if(dist[i][j] == INF) {
					System.out.print(0+" ");
				} else {
					System.out.print(dist[i][j]+" ");
				}
			}
			System.out.println();
		}

	}

	private static void floydWarshall() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dist[i][j] = city[i][j];
			}
		}
		
		//k는 거쳐가는 노드 
		for (int k = 1; k <= n; k++) {
			// 시작 노드 
			for (int i = 1; i <= n; i++) {
				// 도착 노드 
				for (int j = 1; j <= n; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
	}

	private static void initCity() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(i==j) {
					city[i][j] = 0;
				} else {
					city[i][j] = INF;
				}
			}
		}
		
	}

}
