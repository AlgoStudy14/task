package algo0730;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 전제가 주어졌을 때, m개의 줄에 걸쳐 각 결론이 참인지 거짓인지 출력
 * 문제 유형 : 플로이드-워샬
 * <풀이 요약>
 * 모든 정점에서 다른 정점으로 갈 수 있는지 체크 해줘야 하므로 플로이드-워샬 사용
 */

public class BOJ_G5_15723_n단논법 {

	static int N, M;
	static boolean[][] adj;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adj = new boolean[26][26];
		
		for (int i = 0; i < N; i++) {
			int from = sc.next().charAt(0) - 'a';
			sc.next();
			int to = sc.next().charAt(0) - 'a';
			adj[from][to] = true;
		}
		
		floydWarshall();
		
		M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int from = sc.next().charAt(0) - 'a';
			sc.next();
			int to = sc.next().charAt(0) - 'a';
			System.out.println(adj[from][to] ? 'T' : 'F');
		}
	}

	static void floydWarshall() {
		for (int k = 0; k < 26; k++) {
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					if (!adj[i][j] && adj[i][k] && adj[k][j])
						adj[i][j] = true;
				}
			}
		}
	}
}
