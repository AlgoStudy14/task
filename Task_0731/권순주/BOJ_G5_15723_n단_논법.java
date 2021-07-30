import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * <문제 요약>
 * 문제 정의 : n개의 전제가 있을 때, m개의 결론을 각각 구한다
 * 문제 유형 : 플로이드 와샬
 * 유의 사항 : 최대값 : 25
 * 
 */

public class BOJ_G5_15723_n단_논법 {

	static final int INF = 30;
	static int N, M;
	static int[][] graph = new int[26][26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 최단 거리 테이블 초기화
		for (int i = 0; i < 26; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 자기 자신에서 자기 자신으로 가는 비용 0으로 초기화
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (i == j) {
					graph[i][j] = 0;
				}
			}
		}

		// 입력
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			int start = line.charAt(0) - 'a';
			int end = line.charAt(line.length() - 1) - 'a';
			graph[start][end] = 1;
		}

		// 플로이드 와샬 알고리즘 수행
		for (int k = 0; k < 26; k++) {
			for (int a = 0; a < 26; a++) {
				for (int b = 0; b < 26; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}

		// 결과값 계산
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			int start = line.charAt(0) - 'a';
			int end = line.charAt(line.length() - 1) - 'a';
			if (graph[start][end] != INF) { // 값이 존재하면 -> T
				System.out.println("T");
			} else { // 값이 존재하지 않으면 -> F
				System.out.println("F");
			}
		}
	}

}
