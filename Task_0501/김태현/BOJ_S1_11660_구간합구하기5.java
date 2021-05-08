package s0501;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 2차원 배열상의 구간합.
 * 문제 유형 : prefix Sum 구현.
 * <풀이법>
 * 1. 각 행별로 누적합을 구한다.
 * 2. 해당 누적합을 바탕으로 주어진 구간의 구간합을 구한다.
 * <주의사항>
 * 시간이 고려되는 문제는, 혹은 빌더등으로 효율적인 출력이 가능하다면 최대한 그 메서드를 사용하도록 하자.
 */

public class BOJ_S1_11660_구간합구하기5 {
	static int N, M;
	static int[][] prefix;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 인덱스가 0인 지점도 계산하기 위해여 열 + 1.
		prefix = new int[N][N + 1];
		// 누적합 구하기.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				prefix[i][j] = prefix[i][j - 1] + Integer.parseInt(st.nextToken());
			}
		}

		// 구간 합 판단하기(인덱스 주의)
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken());
			int sum = 0;
			for (int x = x1; x <= x2; x++) {
				sum += (prefix[x][y2] - prefix[x][y1 - 1]);
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb);
	}
}
