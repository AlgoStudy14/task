package s0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 개똥 벌레가 파괴하는 장애물의 최솟값과 총 구간의 개수.
 * 문제 유형 : 누적합.
 * <풀이법>
 * 1. 석순과 종유석에 대해서 따로 고려해준다.
 * -> 각 종유석과 석순의 높이에 따라 카운팅 배열을 만들어 저장해준다.
 * -> 1레벨부터 N레벨까지 차곡차곡 더하며 총 누적합의 개수를 구해준다.
 * -> i레벨 석순(or 종유석)의 개수 = 전체 종유석의 개수 - (i - 1)이하 종유석의 개수.
 * <아이디어>
 * N층 석순의 개수 = 전체 석순의 개수 - (N - 1층 석순의 개수)
 * -> 아래층이 통과하면 위층도 자동으로 다 통과한다는 아이디어
 * -> 즉, N층 석순의 개수는 전체 석순의 개수에서 1층부터 N - 1층까지의 모든 석순들을 제거한 값과 같다.
 */
public class BOJ_G5_3020_개똥벌레 {
	static int N, H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		// 각 종유석과 석순의 높이에 따른 개수
		int[] bot = new int[H + 1];
		int[] top = new int[H + 1];
		for (int i = 0; i < N / 2; i++) {
			bot[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}

		// 각 레벨에 대한 총 종유석의 개수
		int[] prefix_bot = new int[H + 1];
		int[] prefix_top = new int[H + 1];
		for (int i = 1; i <= H; i++) {
			prefix_bot[i] = prefix_bot[i - 1] + bot[i];
			prefix_top[i] = prefix_top[i - 1] + top[i];
		}

		// 각 레벨의 석순의 개수를 계산하면서 최솟값을 갱신한다.
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		for (int i = 1; i <= H; i++) {
			// i레벨 석순의 개수
			int sum = prefix_bot[H] - prefix_bot[i - 1];
			// i레벨 종유석의 개수
			sum += (prefix_top[H] - prefix_top[H - i]);

			if (min > sum) {
				min = sum;
				cnt = 1;
			} else if (sum == min) {
				cnt++;
			}
		}
		System.out.println(min + " " + cnt);
	}
}
