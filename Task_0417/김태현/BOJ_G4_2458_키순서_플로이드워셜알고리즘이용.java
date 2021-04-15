package s0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 자신의 키 번호를 알 수 있는 학생들의 수.
 * 문제 유형 : 플로이드 워셜 알고리즘.
 * <풀이법>
 * 1. 모든 간선의 길이를 1이라고 가정하면, 각 노드에서 다른 노드로 가는 최단 경로는 다음을 의미한다.
 * -> i번(행) 사람에 대한 j번(열) 사람이 얼마나 큰가.
 * 2. 최종 배열에서, 각 사람의 행, 열방향에 INF가 아닌 숫자의 개수가 총 사람수와 같다면 그 사람은 키번호를 알 수 있는 사람이다.
 */

public class BOJ_G4_2458_키순서_플로이드워셜알고리즘이용 {
	static int N, M;
	static int[][] student;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 플로이드 배열 초기화
		student = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					student[i][j] = 0;
					continue;
				}
				student[i][j] = 100_000_000;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			student[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}

		// 플로이드 워셜 알고리즘
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (student[i][j] > student[i][k] + student[k][j]) {
						student[i][j] = student[i][k] + student[k][j];
					}
				}
			}
		}
		
		// 키번호를 알 수 있는 사람 검증.
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if(student[i][j] != 100_000_000) {
					cnt++;
				}
				if(student[j][i] != 100_000_000) {
					cnt++;
				}
			}
			// 자기 자신은 두 번 셌으므로 제외.
			cnt--;
			if(cnt >= N) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
