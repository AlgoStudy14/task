package algo0424;

import java.util.Arrays;

/*
 * 지점개수 n
 * 출발지점 s
 * A도착점 a
 * B도착점 b
 * 지점사이의 예상 택시요금 fares
 * 두사람이 s에서 출발해서 각각의 도착지점까지 갈때 최저 택시요금
 * ->플로이드와샬: 모든 정점에서 모든 정점으로의 최단경로
 */
public class PM_2021KAKAOBLIND_합승택시요금 {

	public static void main(String[] args) {
		int n = 6, s = 4, a = 6, b = 2;
		int[][] fares = {
				{4, 1, 10},{3, 5, 24},{5, 6, 2},
				{3, 1, 41},{5, 1, 24},{4, 6, 50},
				{2, 4, 66},{2, 3, 22},{1, 6, 25}
		};
		System.out.println(solution(n, s, a, b, fares));
	}
	public static int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] adj = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
        					//  2147483647(아무 생각 없이 Integer.MAX_VALUE했다가 망)
			Arrays.fill(adj[i], 20000001); //200*100000+1. 덧셈연산 있을 예정이라 n*f보다 하나 크게만 해줌
			adj[i][i] = 0;
		}
        for (int i = 0; i < fares.length; i++) {
        	//무향그래프
        	adj[fares[i][1]][fares[i][0]] = adj[fares[i][0]][fares[i][1]] = fares[i][2];
		}
        for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					//i->j vs i->k->j
					if(adj[i][j]>adj[i][k]+adj[k][j]) adj[i][j]=adj[i][k]+adj[k][j];
				}
			}
		}
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <=n; i++) {
        	//i까지 같이 가고 각각 집갈때 최소 비용.
			min = Math.min(min, adj[s][i]+adj[i][a]+adj[i][b]);
		}
        return min;
    }

}
